package mms.royal.action;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class ModifyMemberAction implements Action {

	@Override
	public void execute(Scanner sc, int index) {
		// step 1. 회원정보 확인
		ConsoleUtils cu = new ConsoleUtils();
		MemberDTO dto = cu.checkInformation(sc);
		
		// ds에서 해당 정보가 일치하는지 확인
		DataStore ds = new DataStore();
		boolean result = ds.checkInformation(dto,index);
		if(result) {
			String password = cu.modifyPassword(sc);
			ds.modifyPassword(password,index);
		}

	}

}
