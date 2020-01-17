package mms.royal.svc;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class RemoveMemberService {

	public boolean removeMemberSvc(MemberDTO dto) {
		
		boolean check = false;
		
		MemberDAO dao = new MemberDAO();
		check = dao.removeMember(dto);
		
		return check;
	}

}
