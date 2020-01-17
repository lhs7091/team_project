package mms.royal.gameplay;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;

public class Game_H implements GamePlay{

   @Override
   public void execute(Scanner sc, int index) {
      
         int win=0; // 이긴 횟수 
         boolean result = false;
         int num;
         int outPoint = 0, rewardPoint = 0, count = 0;
         
         DataStore ds = new DataStore();
         
         do {
          System.out.print("몇 포인트를 거시겠습니까? = ");
          outPoint = Integer.parseInt(sc.nextLine());
          
          if(outPoint <= ds.list.get(index).getGameMoney()) {
             int Point = ds.list.get(index).getGameMoney() - outPoint;
             ds.list.get(index).setGameMoney(Point);
             break;
          }else {
             System.out.println("가지고있는 포인트가 모자랍니다 다시 입력해주세요.");
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
                     System.out.println("얻으신 Point는 " + rewardPoint + " 입니다.");
                     int Point = ds.list.get(index).getGameMoney() + rewardPoint;
                     ds.list.get(index).setGameMoney(Point);
                     System.out.println("현재 보유 포인트 : " +  ds.list.get(index).getGameMoney());
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
                      int Point = ds.list.get(index).getGameMoney() + rewardPoint;
                      ds.list.get(index).setGameMoney(Point);
                      System.out.println("현재 보유 포인트 : " +  ds.list.get(index).getGameMoney());
                      }
                      System.out.println();
                      return;
                      }
                      break;
                      }
             } 
         
             
         } 
         
         
     } 

   