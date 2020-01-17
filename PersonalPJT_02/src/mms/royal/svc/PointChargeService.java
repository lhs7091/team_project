package mms.royal.svc;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class PointChargeService {

	public boolean pointCharge(int money, MemberDTO dto) {
		
		MemberDAO dao = new MemberDAO();
		boolean check = dao.pointCharge(money, dto);
		
		return check;
	}

}
