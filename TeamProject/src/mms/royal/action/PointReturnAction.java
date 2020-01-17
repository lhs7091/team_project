package mms.royal.action;

import java.util.Scanner;
import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;

public class PointReturnAction implements Action {

   @Override
   public void execute(Scanner sc , int index) {
	   DataStore ds = new DataStore();
	   ConsoleUtils cu = new ConsoleUtils();
	   do{
		   System.out.print("환급하실 포인트 = ");
		   int money = Integer.parseInt(sc.nextLine());
		   if(ds.list.get(index).getGameMoney() >= 10000 && money <= ds.list.get(index).getGameMoney()) {
			   cu.returnSuccese(money, index);
			   break;
		   }else
			   cu.returnFail();
	   }while(true);
   }
}