package mms.royal.gameplay;

import java.util.Random;
import java.util.Scanner;

import mms.royal.dao.GameResultDAO;
import mms.royal.svc.GamePointUpdateService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class UpDownGame implements GamePlay {

   @Override
   public void execute(Scanner sc, MemberDTO dto) {
	   	// TODO Auto-generated method stub
	   	int me;  //내가 입력한 숫자
		String check = null;
		int outpoint = 0;
		int count;
		int money =0;
		GameResultDAO dao = new GameResultDAO();
        dao.firstConnection(dto, "UpDown");
      do {
    	  	System.out.print("몇 포인트를 거시겠습니까? = ");
        outpoint = Integer.parseInt(sc.nextLine());
        if(dto.getGameMoney()>outpoint) {
        		money = dto.getGameMoney()-outpoint;
        		break;
        	}else {
        		ConsoleUtils cu = new ConsoleUtils();
        		cu.noPoint(money, dto);
        	}
      }while(true);
         
    do {
    	  	Random rd = new Random();
        int com = rd.nextInt(100);  //컴터 숫자  0~99까지숫자
        count = 0;
		System.out.println("컴퓨터가 숫자를 결정했습니다.");
		System.out.println("0~99까지의 숫자중 하나를 입력하세요.");
		while(true) {
			me = Integer.parseInt(sc.nextLine());
			count++;
			//check = sc.nextLine();
			if(me > com) {
				System.out.println("Down plz^^");  // 나중에 큰지 작은지 다시 고쳐줄것
			}else if(me < com) {
				System.out.println("Up plz^^");
			}else if(me == com) {  //적거나 크지도 않은경우 = 같은경우
				System.out.println("that`s right!!");
				if(count < 8) {				
					System.out.println(count + "번 만에 맞췄습니다.");
					money += (outpoint*2); //현재 잔액+건돈*2ds.list.get(index).setGameMoney(point);//위에 합계금액을 다시 잔액으로 넣음
					System.out.println(outpoint*2 + "원 추가되었습니다.");
					dao.gameWin(dto, outpoint, "UpDown");
				}else {
					System.out.println(count + "번 만에 맞췄습니다.");
					System.out.println(outpoint + "원 잃으셨습니다.");
					dao.gameLoose(dto, outpoint, "UpDown");
				}
				System.out.println("play again?? press the button 'y'");
				System.out.println("stop?? press anything");
				check = sc.nextLine();
				break;
			}//if문끝
		}//while
		GamePointUpdateService gpu = new GamePointUpdateService();
		gpu.gamePoint(money, dto);
		}while(check.equals("y"));
	}
}