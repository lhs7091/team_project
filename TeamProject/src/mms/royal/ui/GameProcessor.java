package mms.royal.ui;

import java.util.Scanner;

import mms.royal.action.Action;
import mms.royal.action.AddMemberAction;
import mms.royal.controller.FrontController;
import mms.royal.gameplay.DieDie;
import mms.royal.gameplay.GamePlay;
import mms.royal.gameplay.Game_H;
import mms.royal.gameplay.HorseRacing;
import mms.royal.gameplay.RPSgame;
import mms.royal.gameplay.UpDownGame;
import mms.royal.store.DataStore;

public class GameProcessor { //게임하기 메뉴 선택시 진행
	
	//멤버필드
	
	//생성자메서드
	
	//멤버메서드
	public void menu(Scanner sc, int index) {
		int menu;
		boolean isStop = true;
		
		/* 프로그램 전체 흐름을 관리할 Controller 객체 생성 */
		FrontController fc = new FrontController();
		
		/*게임머니가 없을경우 자동으로 나가기*/
		DataStore ds = new DataStore();
		if(ds.list.get(index).getGameMoney()==0) {
			System.out.println("포인트가 부족합니다!! 충전해주세요.");
			return;
		}else {
			System.out.println("현재잔액 : "+ds.list.get(index).getGameMoney()+"원");
		}
		
		do {
			/* 단순 계행의 의미 */
			System.out.println();
			
			/* 메뉴 출력문 */
			System.out.println("== Royal Game ==");
			System.out.println("1. 경마");
			System.out.println("2. DieDie");
			System.out.println("3. Up&Down");
			System.out.println("4. RPSgame");
			System.out.println("5. 홀/짝");
			System.out.println("6. 종료");
			System.out.println("=================");
			
			/* 사용자의 선택을 받아내는 작업 */
			System.out.print("menu = ");
			menu = Integer.parseInt(sc.nextLine());
			
			/* GamePlay 클래스를 관리(다형성적인 표현)하고 제어할 목적의 인터페이스 객체 선언 */
			GamePlay gp = null;
			
			/* 사용자의 요청에 따른 동작을 수행하는 문장을 기술 */
			switch(menu) {
			case 1: // 1번 게임 이동
				gp = new HorseRacing(); //1번게임 클래스로 이동
				break;
			case 2: //2번 게임 이동
				gp = new DieDie();// 2번게임
				break;
			case 3:
				gp = new UpDownGame();
				break;
			case 4:
				gp = new RPSgame();
				break;
			case 5:
				gp = new Game_H();
				break;
			case 6://종료
				System.out.println("GamePlay를 종료합니다.");
				isStop = false;
				return;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
				break;
			}
			
			if(gp != null) { //실제 수행할 Game을 넘겨주는 역할
				fc.gamePlayRequest(gp, sc, index);
			}
			
		}while(isStop);
		
		return;
		
	}
	
	//중첩클래스

}
