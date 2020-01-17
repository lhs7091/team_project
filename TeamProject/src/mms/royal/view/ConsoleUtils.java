package mms.royal.view;

import java.util.Scanner;

import mms.royal.store.DataStore;
import mms.royal.vo.MemberDTO;

public class ConsoleUtils {
	
	// 회원 존재 확인
	public boolean checkMember(String work, Scanner sc, int index) {
		System.out.println();
		System.out.println("== "+work+" ==");
		System.out.print("id = ");
		String id = sc.nextLine();
		System.out.print("password = ");
		String password =sc.nextLine();
		
		MemberDTO dto = new MemberDTO();
		DataStore ds = new DataStore();
		if(ds.list.get(index).getId().equals(id) && ds.list.get(index).getPassword().equals(dto.hashSHA256(password))) {
			System.out.println(ds.list.get(index).getId()+"님 회원 정보");
			return true;
		}
		
		return false;	
	}

	public void showMember(MemberDTO dto) {
		System.out.println(dto.toString());
	}

	public void showMemberFail() {
		System.out.println("해당 회원이 존재하지 않습니다.");
	}
	
	//포인트 잔액 부족
	public boolean noPoint(int outPoint, int index) {
		DataStore ds = new DataStore();
		if(outPoint>ds.list.get(index).getGameMoney()) {
			System.out.println("포인트가 부족합니다.");
			System.out.println("GamePoint를 충전해주세요.");
			return true;
		}else {
			return false;
		}
	}
	
	//태정
	public void returnSuccese(int money, int index) {
        DataStore ds = new DataStore();
		System.out.println("고객님의 계좌로" + money + "원 이체되었습니다.");
        int gameMoney = ds.list.get(index).getGameMoney() - money;
        ds.list.get(index).setGameMoney(gameMoney);
	}   
  
	public void returnFail(){
		System.out.println("포인트가 10000 이하이거나 금액이모자랍니다. (10000 포인트 이상부터 환급 가능)");
	}
	
	//훈섭
	public void chargeSuccese(int money, int index) {
        DataStore ds = new DataStore();
		System.out.println(money + "원이 충전 되었다.");
        int gameMoney = ds.list.get(index).getGameMoney() + money;
        ds.list.get(index).setGameMoney(gameMoney);
	}   
  
	public void chargeFail(){
		System.out.println("입력한 값이 잘못 되었다.");
	}
	
	//이름을 입력받아 리턴시켜주는 메서드 - 수정 or 삭제 서창우
    public String getName(String kind, Scanner sc) {
       System.out.println(kind+"할 이름을 입력해주세요");
       System.out.print("이름=");
       return sc.nextLine();   
    }

    public void nameFail(String name) {
       System.out.println(name+" 회원은 존재하지 않습니다.");
    }
    
    //서창우
    public void removeSuccess() { 
       System.out.println("회원 정보 삭제 성공!!");
    }

    
    public void removeFail() {
       System.out.println("회원 정보 삭제 실패!!");
    }
    
    //민진
    public void showFail() {
        System.out.println("회원등록 실패!!");
     }

     public void showSuccess() {
        System.out.println("회원등록 성공!!"); 
     }

     public MemberDTO getNewMember(Scanner sc) {
        System.out.println("등록할 회원의 정보를 입력해주세요.");
        System.out.print("아이디 = ");
        String id = sc.nextLine();
        System.out.print("비밀번호 = ");
        String password = sc.nextLine();
        System.out.print("이름 = ");
        String name = sc.nextLine();
        System.out.print("연락처 = ");
        String phone = sc.nextLine();
        System.out.print("고객 계좌 정보 = ");
        String account = sc.nextLine();
        System.out.print("이메일 = ");
        String email = sc.nextLine();
        
        //String id, String password, String name, String phone, String account, String email
        return new MemberDTO(id, password, name, phone, account, email);
     }
     
     // 회원비밀번호 변경전 id / 이메일 확인
     public MemberDTO checkInformation(Scanner sc) {
    	 	System.out.println("비밀번호를 변경할 회원정보를 확인하겠습니다.");
    	 	System.out.print("id = ");
    	 	String id = sc.nextLine();
    	 	System.out.print("email = ");
    	 	String email = sc.nextLine();
    	 	
    	 	return new MemberDTO(id,email);
     }
     // 회원 비밀번호 변경
     public String modifyPassword(Scanner sc) {
    	 	System.out.println("변경할 비밀번호를 입력해주세요.");
    	 	System.out.print("password = ");
    	 	String password = sc.nextLine();
    	 	MemberDTO dto = new MemberDTO();
    	 	dto.hashSHA256(password);
    	 	return password;
     }
     
}


	

