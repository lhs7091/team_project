package mms.royal.ui;

import java.util.Scanner;


import mms.royal.action.Action;
import mms.royal.action.AddMemberAction;
import mms.royal.controller.FrontController;

public class MainProcessor{

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int menu;
		boolean isStop = true;
		
		/* 프로그램 전체 흐름을 관리할 Controller 객체 생성 */
		FrontController fc = new FrontController();
		
		do {
			/* 단순 계행의 의미 */
			System.out.println();
			
			/* 메뉴 출력문 */
			System.out.println("== Royal Game ==");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 종료");
			System.out.println("=================");
			
			/* 사용자의 선택을 받아내는 작업 */
			System.out.print("menu = ");
			menu = Integer.parseInt(sc.nextLine());
			
			/* Action 클래스를 관리(다형성적인 표현)하고 제어할 목적의 인터페이스 객체 선언 */
			Action ac = null;
			
			/* 사용자의 요청에 따른 동작을 수행하는 문장을 기술 */
			switch(menu) {
			case 1://회원가입
				ac = new AddMemberAction(); //회원가입을 위한 Action클래스로 이동
				break;
			case 2:{//로그인 후 진행 LoginProcessor로 이동
				LoginProcessor lp = new LoginProcessor();
				lp.menu(sc);
				break;
			}
			case 3://종료
				System.out.println("Royal Game을 종료합니다.");
				isStop = false;
				System.exit(0);
				break;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
				break;
			}
			
			if(ac != null) { //실제 수행할 Action을 넘겨주는 역할
				fc.processRequest(ac, sc, null);
			}
			
		}while(isStop);
	}

}
