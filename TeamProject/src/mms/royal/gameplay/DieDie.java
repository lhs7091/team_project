package mms.royal.gameplay;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.view.ConsoleUtils;

public class DieDie implements GamePlay {
   int outPoint = 0, rewardPoint = 0; // 게임에 건 포인트 / 이겼을경우 얻는 포인트
   int playerHP = 100, comHP = 100; // 플레이어 HP / comHP
   String player = "player", com = "computer";
   //ds.list.index.getPoint
      
   @Override
   public void execute(Scanner sc, int index) {
      ConsoleUtils cu = new ConsoleUtils();
      DataStore ds = new DataStore();
         
      do {
         System.out.print("몇 포인트를 거시겠습니까? = ");
         outPoint = Integer.parseInt(sc.nextLine());
         
         if(outPoint <= ds.list.get(index).getGameMoney()) {
            int Point = ds.list.get(index).getGameMoney() - outPoint;
            break;
         }else {
            System.out.println("가지고있는 포인트가 모자랍니다 다시 입력해주세요.");
         }
      }while(true);
        
      rewardPoint = outPoint * 2;
        
      while (playerHP > 0 && comHP > 0) {
         // 1/2 확률로 공격자 선정
         int attacker = (int)(Math.random() * 2); //0이면 player가 공격, 1이면 computer가 공격
          
         // 대상을 공격
         switch(attacker) {
         case 0 : {
            comHP -= 10;
            System.out.println("[player 공격]");
            System.out.println("computer의 HP : " + comHP);
         }
         break;
         case 1 : {
            playerHP -= 10;
            System.out.println("[computer 공격]");
            System.out.println("player의 HP : " + playerHP);
         }
         }
          
          // 딜레이 1초
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            return;
         }
      }
           
      if(playerHP == 0) {
         System.out.println("player가 졌습니다.");
         return;
      }
      else {
         int Point = ds.list.get(index).getGameMoney() + rewardPoint;
         ds.list.get(index).setGameMoney(Point);
         System.out.println("player가 이겼습니다.");
         System.out.println("얻으신 Point는 " + rewardPoint + " 입니다.");
         System.out.println();
         System.out.println("player의 현재 포인트는 : " + Point);
         return;
      }
      
   }

}