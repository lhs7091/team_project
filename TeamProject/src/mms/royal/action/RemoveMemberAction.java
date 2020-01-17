package mms.royal.action;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class RemoveMemberAction implements Action {

	@Override
	public void execute(Scanner sc, int index) {
		//회원의 이름을 입력받아 해당 객체를 기억공간에서 제거하는 작업
        //step1. 삭제할 회원의 이름을 입력받는다
        ConsoleUtils cu = new ConsoleUtils();
        String name = cu.getName("삭제", sc);
        //step2. 해당 이름을 이용하여 저장공간에서 해당 객체를 찾아낸다
        DataStore ds = new DataStore();
        MemberDTO deleteMember = ds.select(name);
        if(deleteMember == null) {
           System.out.println(name+"회원이 존재하지 않습니다");        
        }
        //step3. 제거한다
        boolean check = ds.delete(deleteMember, name);
        if(check) {
           cu.removeSuccess();
        }
        else {
           cu.removeFail();
        }
	}
}
