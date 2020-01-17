package mms.royal.svc;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;

public class GamePointUpdateService {
	
	/*게임 결과에 따라 포인트 updqte하기 위한 service*/
	public void gamePoint(int money, MemberDTO dto) {
	
		MemberDAO dao = new MemberDAO();
		dao.gamePoint(money, dto);
		
	}

}
