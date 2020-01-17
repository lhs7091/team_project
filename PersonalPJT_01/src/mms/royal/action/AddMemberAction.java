package mms.royal.action;

import java.util.Scanner;

import mms.royal.svc.AddMemberService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class AddMemberAction implements Action {

	@Override
	public void execute(Scanner sc, MemberDTO dto) {
		ConsoleUtils cu = new ConsoleUtils();
		//MemberDTO getNewMember
		MemberDTO newMember = cu.getNewMember(sc);
		AddMemberService ams = new AddMemberService();
		boolean isCheck = ams.addMemberSvc(newMember);
		
		if(isCheck) {
		   cu.showSuccess();
		}else {
		   cu.showFail();
		}
	}
}
