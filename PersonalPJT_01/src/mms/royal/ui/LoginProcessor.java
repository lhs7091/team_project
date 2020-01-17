package mms.royal.ui;


import java.util.*;

import mms.royal.action.Action;
import mms.royal.action.ModifyMemberAction;
import mms.royal.action.PointChargeAction;
import mms.royal.action.PointReturnAction;
import mms.royal.action.RemoveMemberAction;
import mms.royal.action.ShowGameResAction;
import mms.royal.action.ShowMemberAction;
import mms.royal.controller.FrontController;
import mms.royal.vo.MemberDTO;

public class LoginProcessor{
	//멤버필드
	
	//생성자메서드
	
	//멤버메서드
	public void menu(Scanner sc) {
		int menu; //메뉴
		boolean isStop = true; //do ~ while 문 반복여부 확인
		MemberDTO dto = null;

		/* 프로그램 전체 흐름을 관리할 Controller 객체 생성 */
		FrontController fc = new FrontController();
		
		/* 로그인 하기 위한 절차*/
		LoginCheck lc = new LoginCheck();
		dto = lc.login(sc);
		
		if(dto!=null) {
			System.out.println("로그인 성공");
			
			do {
				/* 메뉴 출력문 */
				System.out.println();
				System.out.println("== Royal Game ==");
				System.out.println("1. 게임하기");
				System.out.println("2. 회원정보조회");
				System.out.println("3. 회원게임전적조회");
				System.out.println("4. 포인트충전");
				System.out.println("5. 포인트환급");
				System.out.println("6. 회원탈퇴");
				System.out.println("7. 비밀번호변경");
				System.out.println("8. 로그아웃");
				System.out.println("=================");
				
				/* 사용자의 선택을 받아내는 작업 */
				System.out.print("menu = ");
				menu = Integer.parseInt(sc.nextLine());
				
				/* Action 클래스를 관리(다형성적인 표현)하고 제어할 목적의 인터페이스 객체 선언 */
				Action ac = null;
				
				switch(menu) {
				case 1: {//게임하기
					GameProcessor gp = new GameProcessor();
					gp.menu(sc, dto);
					break;
				}
				case 2: //회원조회
					ac = new ShowMemberAction();
					break;
				case 3: //게임전적조회
					ac = new ShowGameResAction();
					break;
				case 4: //포인트충전
					ac = new PointChargeAction();
					break;
				case 5: //포인트환급
					ac = new PointReturnAction();
					break;
				case 6: //회원탈퇴
					ac = new RemoveMemberAction();
					isStop=false;
					break;
				case 7: //비밀번호변경
					ac = new ModifyMemberAction();
					break;
				case 8: //로그아웃
					System.out.println("로그아웃합니다.");
					return;
				default :
					System.out.println("메뉴를 잘못 입력하셨습니다.");
					break;
				}
				
				if(ac != null) { //실제 수행할 Action을 넘겨주는 역할
					fc.processRequest(ac, sc, dto);
				}
				
			}while(isStop);
			
		}else {
			System.out.println("ID 또는 Password가 잘못 되었습니다.");
		}
		return;
		
	}
	
}
