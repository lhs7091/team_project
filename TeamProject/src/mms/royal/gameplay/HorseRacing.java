package mms.royal.gameplay;

import java.util.*;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;


public class HorseRacing implements GamePlay {
	int horseCount = 8;
	double[] horsePercent = new double[horseCount]; //각 말에 확률 부여
	int[] rank = new int[horseCount]; // 나오는 확률에 따른 말 랭킹 저장
	double[] batting = new double[horseCount]; // 확률에 따른 우승시 배당금 책정
	int outPoint = 0;
	int rewardPoint = 0;
	
	@Override
	public void execute(Scanner sc, int index) {
		ConsoleUtils cu = new ConsoleUtils();
		System.out.println("== 경마 시스템 ==");
		System.out.println("선택한 말 우승시 배당율*배팅금액");
		System.out.println("2등일 경우 : 배당율*50%*배팅금액");
		System.out.println("3등일 경우 : 배당율*30%*배팅금액");
		System.out.println();
		
		percent();
		showRank();

		System.out.println();
		System.out.print("말 선택 = ");
		int horse = Integer.parseInt(sc.nextLine());
		System.out.print("배팅금액 = ");
		outPoint = Integer.parseInt(sc.nextLine());
		boolean check = cu.noPoint(outPoint, index);
		if(check) {
			return;
		}
		
		System.out.println("경마가 시작 됩니다.");
		try {
			System.out.println("과연~~ 과연~~~ 과연~~");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			return;
		}
		resultGame(horse, outPoint, index);
		return;
		
	}
	
	public void percent() {

		for(int i = 0; i<1000; i++) { //count
			int rd = (int)(Math.random()*80)/10;
			horsePercent[rd]=horsePercent[rd]+1.0;
		}
		
		for(int i = 0; i<horsePercent.length; i++) { //각 번호 말의 우승 확률 구하기 완료 
			horsePercent[i]/=1000.0;
		}
		// 각 말 랭킹
		for(int i=0; i<horsePercent.length; i++) {
			rank[i]=1;
			for(int j=0; j<horsePercent.length;j++) {
				if(horsePercent[i]<horsePercent[j]) {
					rank[i]++;
				}
		    }
		}
		
		// 각 말 배당율
		for(int i = 0; i<batting.length; i++) {
			batting[i]=1.0-horsePercent[i]+2.0;
		}
		
	}
	
	//말 랭킹정보/배당율 출력
	public void showRank() {
		for(int i = 0; i<horsePercent.length; i++) {
			System.out.println((i+1)+"번 말 : 우승확률 "+horsePercent[i]+" / 랭킹"+rank[i]+"위 / 배당율 "+batting[i]);
		}
	}
	
	public void resultGame(int horse, int outPoint, int index) {
		int first;
		int second;
		int third;
		DataStore ds = new DataStore();
		
		while(true) {
			first = (int)(Math.random()*80)/10;
			second = (int)(Math.random()*80)/10; 
			third = (int)(Math.random()*80)/10;
			
			if(first != second && first != third && second != third) {
				break;
			}
		}
		
		ds.list.get(index).setGameMoney(ds.list.get(index).getGameMoney()-outPoint);
		
		if(horse == first) {
			System.out.println("1등");
			rewardPoint = (int)(outPoint*batting[horse-1]);
			System.out.println("획득포인트 : "+rewardPoint);
			ds.list.get(index).setGameMoney(ds.list.get(index).getGameMoney()+rewardPoint);
		}else if(horse == second){
			System.out.println("2등");
			rewardPoint = (int)(outPoint*batting[horse-1]*0.5);
			System.out.println("획득포인트 : "+rewardPoint);
			ds.list.get(index).setGameMoney(ds.list.get(index).getGameMoney()+rewardPoint);
		}else if(horse == third) {
			System.out.println("3등");
			rewardPoint = (int)(outPoint*batting[horse-1]*0.3);
			System.out.println("획득포인트 : "+rewardPoint);
			ds.list.get(index).setGameMoney(ds.list.get(index).getGameMoney()+rewardPoint);
		}else {
			ds.list.get(index).setGameMoney(ds.list.get(index).getGameMoney()-outPoint);
			System.out.println("아~~~ 아쉽게.... 탈락");
		}
	}
}
