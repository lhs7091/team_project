package mms.royal.action;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class ShowMemberAction implements Action {

	@Override
	public void execute(Scanner sc, int index) {
		// Step 1. 회원정보를 확인한다. -> 콘솔에 확인 메서드 사용
		ConsoleUtils cu = new ConsoleUtils();
		boolean check = cu.checkMember("회원조회", sc, index);
		// Step 2. 회원정보가 있으면 DataStore에서 해당 정보 호출하고, 콘솔로 전달
		if(check) { 
			DataStore ds = new DataStore();
			MemberDTO dto = ds.showMember(index);
			cu.showMember(dto);
			return;
		}else {//해당회원이 존재
			cu.showMemberFail();
			return;
		}
	}
}
