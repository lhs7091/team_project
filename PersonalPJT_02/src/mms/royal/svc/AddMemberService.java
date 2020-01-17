package mms.royal.svc;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class AddMemberService {

	public boolean addMemberSvc(MemberDTO newMember) {
		MemberDAO dao = new MemberDAO();
		boolean check = dao.insertMember(newMember);
		
		return check;
	}

}
