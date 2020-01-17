package mms.royal.action;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;

public class PointChargeAction implements Action {

	@Override
	public void execute(Scanner sc, int index) {
		DataStore ds = new DataStore();
		ConsoleUtils cu = new ConsoleUtils();
		System.out.print("충전할 포인트를 입력해라 = ");
		int money = Integer.parseInt(sc.nextLine());
		if(money > 0) {
		cu.chargeSuccese(money, index);
		}
		
		else{
			cu.chargeFail();
		}

	}

}
