package mms.royal.gameplay;

import java.util.Random;
import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;

public class RPSgame implements GamePlay {
	
	@Override
	public void execute(Scanner sc, int index) {
		DataStore ds = new DataStore();
		ConsoleUtils cu = new ConsoleUtils();
		System.out.println(index);
		System.out.println(ds.list.get(index).getGameMoney());
		System.out.print("얼마를 걸겠나? = ");
		int p = Integer.parseInt(sc.nextLine());
		
	
		if(p>ds.list.get(index).getGameMoney()) {
			System.out.println("입력값이 잘못되었습니다.\n의도적인 공격으로 간주하여 시스템을 종료합니다.");
			System.exit(0);
			
		}else if(p<=ds.list.get(index).getGameMoney()) {
			int gameMoney = ds.list.get(index).getGameMoney() - p;
	        ds.list.get(index).setGameMoney(gameMoney);
			System.out.println(p+"원을 걸고 게임을 시작합니다.");
		}
		
		int user;
		int com;
		Random r = new Random();
		System.out.println("가위바위보 대결에 온 걸 환영한다.");
		com = (r.nextInt() >>>1) % 3 ;
		System.out.print("가위[0], 바위[1], 보[2] 중 하나를 입력하세요.\n(정수만 입력받고 잘못된 입력을 하게되면 패배처리됩니다.) \n입력 : ");
		user = Integer.parseInt(sc.nextLine());
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("봇 : " + com);
		System.out.println("나 : " + user);
		
		if(user == com) {
			System.out.println("크.. 비겼구나");
			int gameMoney = ds.list.get(index).getGameMoney() + p;
	        ds.list.get(index).setGameMoney(gameMoney);
			System.out.println("배팅한" +p+"원을 반환받습니다.");
			
		}else if ((user == 0 && com == 2) || (user == 1 && com == 0) || (user == 2 && com ==1)) {
			
			System.out.println("강력하구나 애송이");
			
			p=(int)(p*1.3);
			int gameMoney = ds.list.get(index).getGameMoney() + p;
	        ds.list.get(index).setGameMoney(gameMoney);
			
		}
		else {
			System.out.println("ㅋㅋㅋㅋ 다음에 또 하자 빙구");
			System.out.println(p+"원을 잃었습니다.");
		}
	}
}
