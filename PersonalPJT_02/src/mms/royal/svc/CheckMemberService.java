package mms.royal.svc;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class CheckMemberService {

	public boolean checkMemberSvc(MemberDTO dto) {
		
		MemberDAO dao = new MemberDAO();
		boolean check = dao.checkMember(dto);
		
		return check;
	}

}
