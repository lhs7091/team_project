package mms.royal.action;

import java.util.Scanner;

import mms.royal.svc.CheckMemberService;
import mms.royal.svc.RemoveMemberService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class RemoveMemberAction implements Action {

	@Override
	public void execute(Scanner sc, MemberDTO dto) {
		//회원의 이름을 입력받아 해당 객체를 기억공간에서 제거하는 작업
        //step1. 삭제할 회원의 id와 비밀번호를 입력받는다
        ConsoleUtils cu = new ConsoleUtils();
        dto = cu.checkMember("회원삭제", sc);
        
        //step2. 입력받은 dto객체가 db에 해당정보를 삭제한다.
        //회원정보가 존재할 경우 삭제되어 true가 return, 없을 경우 false return
        RemoveMemberService rms = new RemoveMemberService();
        boolean check = rms.removeMemberSvc(dto);

        if(check) {
           cu.removeSuccess();
        }
        else {
           cu.removeFail();
        }
        return;
	}
}
