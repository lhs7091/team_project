package mms.royal.gameplay;

import java.util.Scanner;

import mms.royal.dao.GameResultDAO;
import mms.royal.svc.GamePointUpdateService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class Game_H implements GamePlay{

   @Override
   public void execute(Scanner sc, MemberDTO dto) {
      
         int win=0; // 이긴 횟수 
         boolean result = false;
         int outPoint = 0, rewardPoint = 0, count = 0;
         int money = 0; // DB에 업데이트할 최종 변경 금액
         GameResultDAO dao = new GameResultDAO();
         //dao.firstConnection(dto, "홀짝");
         do {
        	 	System.out.print("몇 포인트를 거시겠습니까? = ");
        	 	outPoint = Integer.parseInt(sc.nextLine());
             
			if(dto.getGameMoney()>outPoint) {
			   money = dto.getGameMoney()-outPoint;
			   break;
			}else {
				ConsoleUtils cu = new ConsoleUtils();
				cu.noPoint(money, dto);
			}
       }while(true);
         
         System.out.println("게임은 총 10번 진행됩니다. 중도 포기는 불가능합니다.");
         
         while (count <= 10)  {
        	 	System.out.println("*******************");
        	 	System.out.println("*    홀 짝 게 임    *");
        	 	System.out.println("*  1.    홀        *");
        	 	System.out.println("*  2.    짝        *");
        	 	System.out.println("*******************");
        	 	System.out.print("메 뉴 선 택   = ");
        	 	int select = Integer.parseInt(sc.nextLine());
        	 	//System.out.println("게임은 총 10번 진행됩니다. 중도 포기는 불가능합니다.");
           
             switch(select){
                 case 1:
                    int randomNumber=(int) ((Math.random()*100) + 1);
                    result=randomNumber%2!=0;
                     System.out.println("Player가 홀을 선택했습니다.");  
                        count++;
                     System.out.println("홀? 짝?: "+ randomNumber);
                     if(result){
                        System.out.println("You win");
                         win++;
                     }else {
                        System.out.println("You lose");
                     }
                     if(count == 10) { System.out.println("상위메뉴로 돌아갑니다.");
                     System.out.println("총게임횟수 : "+ count);
                     System.out.println("이긴횟수 : " + win);
                     
                     if(win >= 5) {//5승 이상이면 2배의 포인트 5승 이하면 없음
                     rewardPoint = outPoint*2;
                     //System.out.println("얻으신 Point는 " + rewardPoint + " 입니다.");
                     money = money + rewardPoint;
                     //dao.gameWin(dto, outPoint, "홀짝");
                     System.out.println("현재 보유 포인트 : " +  money);
                     }
                     System.out.println();
                     return;
                     }
                      break;
                 case 2:
                    int randomNumber1=(int) ((Math.random()*100) + 1);
                    result=randomNumber1%2==0;
                     System.out.println("Player가 짝을 선택했습니다.."); 
                        count++;
                     System.out.println("홀? 짝?: "+ randomNumber1);
                      if(result){
                         System.out.println("You win");
                         win++;
                     }else {
                        System.out.println("You lose");
                     }
                      if(count == 10) { System.out.println("상위메뉴로 돌아갑니다.");
	                      System.out.println("총게임횟수 : "+ count);
	                      System.out.println("이긴횟수 : " + win);
                      
	                      if(win >= 5) {//5승 이상이면 2배의 포인트 5승 이하면 없음
		                      rewardPoint = outPoint*2;
		                      System.out.println("얻으신 Point는 " + rewardPoint + " 입니다.");
		                      money += rewardPoint;
		                      //ds.list.get(index).setGameMoney(Point);
		                      System.out.println("현재 보유 포인트 : " +  money);
	                      }
	                      System.out.println();
	                      return;
                      }
                 break;
                 }
             
             } 
         	
         	GamePointUpdateService gpu = new GamePointUpdateService();
         	gpu.gamePoint(money, dto);
         
         } 

     } 

   