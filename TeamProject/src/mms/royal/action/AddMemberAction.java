package mms.royal.action;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class AddMemberAction implements Action {

	@Override
	public void execute(Scanner sc, int index) {
		ConsoleUtils cu = new ConsoleUtils();
		//MemberDTO getNewMember
		MemberDTO newMember = cu.getNewMember(sc);
		DataStore ds = new DataStore();
		boolean isCheck = ds.insert(newMember);
		if(isCheck) {
		   cu.showSuccess();
		}else {
		   cu.showFail();
		}
	}
}
