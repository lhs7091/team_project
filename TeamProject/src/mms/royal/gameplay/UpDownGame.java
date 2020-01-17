package mms.royal.gameplay;

import java.util.Random;
import java.util.Scanner;

import mms.royal.store.DataStore;

public class UpDownGame implements GamePlay {

   @Override
   public void execute(Scanner sc, int index) {
      // TODO Auto-generated method stub
      int me;  //내가 입력한 숫자
        String check = null;
        int outpoint = 0;
        int count;
        int point =0;
        
        DataStore ds = new DataStore();
         
      do {
         System.out.print("몇 포인트를 거시겠습니까? = ");
         outpoint = Integer.parseInt(sc.nextLine());
   
         if(outpoint <= ds.list.get(index).getGameMoney()) {
            point = ds.list.get(index).getGameMoney() - outpoint;
            ds.list.get(index).setGameMoney(point);
            break;
         }else {
            System.out.println("가지고있는 포인트가 모자랍니다 다시 입력해주세요.");
         }
      }while(true);
         
      do {
              Random rd = new Random();
              int com = rd.nextInt(100);  //컴터 숫자  0~99까지숫자
            count = 0;
              //System.out.println(com); //컴터가 숫자 결정
              System.out.println("컴퓨터가 숫자를 결정했습니다.");
              System.out.println("0~99까지의 숫자중 하나를 입력하세요.");
              while(true) {
                 me = Integer.parseInt(sc.nextLine());
                 count++;
                 //check = sc.nextLine();
                 if(me > com) {
                    System.out.println("Down plz^^");  // 나중에 큰지 작은지 다시 고쳐줄것
                 }else if(me < com) {
                    System.out.println("Up plz^^");
                 }else if(me == com) {  //적거나 크지도 않은경우 = 같은경우
                    System.out.println("that`s right!!");
                    if(count < 8) {
                       //count++;
                       System.out.println(count + "번 만에 맞췄습니다.");
                       //point = outpoint*2;
                       point = ds.list.get(index).getGameMoney()+(outpoint*2); //현재 잔액+건돈*2
                       ds.list.get(index).setGameMoney(point);//위에 합계금액을 다시 잔액으로 넣음
                       System.out.println(outpoint*2 + "원 추가되었습니다.");
                    }else {
                       //count++;
                       System.out.println(count + "번 만에 맞췄습니다.");
                       //point = outpoint*2;
                       System.out.println(outpoint + "원 잃으셨습니다.");
                    }
                    System.out.println("play again?? press the button 'y'");
                    System.out.println("stop?? press anything");
                    check = sc.nextLine();
                    break;
                 }//if문끝
              }//while
      }while(check.equals("y"));
   }
}