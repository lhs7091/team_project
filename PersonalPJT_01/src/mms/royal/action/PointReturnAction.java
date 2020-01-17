package mms.royal.action;

import java.util.Scanner;
import mms.royal.svc.PointReturnService;
import mms.royal.svc.ShowMemberService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class PointReturnAction implements Action {

   @Override
   public void execute(Scanner sc, MemberDTO Odto) {
	   
	   ConsoleUtils cu = new ConsoleUtils();
	   
	   //현재잔액 표시 및 출금 잔액을 입력하기 위한 ConsoleUtils 처리
	   ShowMemberService sms = new ShowMemberService();
	   MemberDTO dto = sms.showMemberSvc(Odto);
	   int money = cu.pointReturn(sc, dto); //출금 가능한 금액
	   
	   //DB에서 출금 금액을 차감 처리
	   PointReturnService prs = new PointReturnService();
	   boolean check = prs.pointReturn(money, Odto);
	   
	   if(check) {
		   dto = sms.showMemberSvc(Odto);
		   cu.pointReturnSuccess(money, dto);
	   }else {
		   cu.pointReturnFail();
	   }
	   
   }
}

