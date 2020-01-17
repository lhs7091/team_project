package mms.royal.action;

import java.util.Scanner;

import mms.royal.svc.PointChargeService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class PointChargeAction implements Action {

	@Override
	public void execute(Scanner sc, MemberDTO dto) {
		
		/* 포인트 입력을 위한 콘솔유틸 객체생성 */
		ConsoleUtils cu = new ConsoleUtils();
		int money = cu.pointCharge(sc);
		
		/* 입력한 금액을 해당 id에 충전 */
		PointChargeService pcs = new PointChargeService();
		boolean check = pcs.pointCharge(money, dto);
		
		if(check) {
			cu.chargeSuccese(money);
		}
		
		else{
			cu.chargeFail();
		}

	}

}
