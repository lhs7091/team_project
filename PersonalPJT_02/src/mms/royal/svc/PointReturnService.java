package mms.royal.svc;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class PointReturnService {

	public boolean pointReturn(int money, MemberDTO dto) {
		
		MemberDAO dao = new MemberDAO();
		boolean check = dao.pointReturn(money, dto);
		
		return check;
	}

}
