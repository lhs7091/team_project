package mms.royal.gameplay;

import java.util.Scanner;

import mms.royal.dao.GameResultDAO;
import mms.royal.svc.GamePointUpdateService;
import mms.royal.view.ConsoleUtils;
import mms.royal.vo.MemberDTO;

public class DieDie implements GamePlay {
   int outPoint = 0, rewardPoint = 0; // 게임에 건 포인트 / 이겼을경우 얻는 포인트
   int playerHP = 100, comHP = 100; // 플레이어 HP / comHP
   String player = "player", com = "computer";
   GameResultDAO dao = new GameResultDAO();
   
   @Override
   public void execute(Scanner sc, MemberDTO dto) {
      ConsoleUtils cu = new ConsoleUtils();
      int money = 0;
      dao.firstConnection(dto, "죽이기");
      do {
         System.out.print("몇 포인트를 거시겠습니까? = ");
         outPoint = Integer.parseInt(sc.nextLine());
         
         if(dto.getGameMoney()>outPoint) {
            money = dto.getGameMoney()-outPoint;
        	 	break;
         }else {
            cu.noPoint(money, dto);
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
         dao.gameLoose(dto, outPoint, "죽이기");
         return;
      }
      else {
         money += rewardPoint;
         GamePointUpdateService gps = new GamePointUpdateService();
         gps.gamePoint(money, dto);
         System.out.println("player가 이겼습니다.");
         dao.gameWin(dto, outPoint, "죽이기");
         System.out.println();
         System.out.println("player의 현재 포인트는 : " + money);
         return;
      }
      
   }

}