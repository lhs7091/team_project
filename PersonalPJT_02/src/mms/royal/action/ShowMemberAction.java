package mms.royal.action;

import java.util.Scanner;

import mms.royal.svc.ShowMemberService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class ShowMemberAction implements Action {

	@Override
	public void execute(Scanner sc, MemberDTO dto) {
		// Step 1. 회원정보를 확인한다. -> 콘솔에 확인 메서드 사용
		ConsoleUtils cu = new ConsoleUtils();
		dto = cu.checkMember("회원조회", sc);
		
		// Step 2. 해당정보가 있는지 svc로 보냄
		ShowMemberService sms = new ShowMemberService();
		dto = sms.showMemberSvc(dto);
		if(dto!=null) { 
			cu.showMember(dto);
			return;
		}else {//해당회원이 존재
			cu.showMemberFail();
			return;
		}
	}
}
