package mms.royal.svc;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class ModifyMemberService {
	
	//id와 email유효성 확인
	public boolean checkMember(MemberDTO dto) {
		
		MemberDAO dao = new MemberDAO();
		boolean check = dao.checkMemberEmail(dto);
		return check;
	}
	
	//변경한 비밀번호 적용
	public boolean modifyMember(String password, MemberDTO dto) {
		MemberDAO dao = new MemberDAO();
		boolean check = dao.modifyMember(password, dto);
		return check;
	}

}

