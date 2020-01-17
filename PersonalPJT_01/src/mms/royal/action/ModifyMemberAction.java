package mms.royal.action;

import java.util.Scanner;

import mms.royal.svc.ModifyMemberService;
import mms.royal.svc.ShowMemberService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class ModifyMemberAction implements Action {

	@Override
	public void execute(Scanner sc, MemberDTO dto) {
		// step 1. 회원정보 확인
		ConsoleUtils cu = new ConsoleUtils();
		MemberDTO checkdto = cu.checkInformation(sc);
		
		// db에서 해당 정보를 있는지 확인
		ModifyMemberService mms = new ModifyMemberService();
		boolean check = mms.checkMember(checkdto);
		
		if(check) {
			//회원정보가 있을 경우
			String password = cu.modifyPassword(sc);
			boolean result = mms.modifyMember(password, dto);
			if(result) {
				cu.modifyPasswordSuccess();
				return;
			}else {
				System.out.println("실패");
			}
		}else {
			//없을경우
			System.out.println("입력하신 정보와 일치하는 정보가 없습니다.");
		}

	}

}
