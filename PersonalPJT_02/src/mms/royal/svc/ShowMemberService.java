package mms.royal.svc;


import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class ShowMemberService {
	
	public MemberDTO showMemberSvc(MemberDTO dto){
		MemberDTO select = null;
		
		MemberDAO dao = new MemberDAO();
		select = dao.selectMember(dto);
		
		return select;
		
	}

}
