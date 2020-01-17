package mms.royal.view;

import java.util.Scanner;

import mms.royal.dao.MemberDAO;
import mms.royal.vo.MemberDTO;
import mms.royal.vo.RegularExpress;

public class ConsoleUtils {
	
	// 회원 존재 확인
	public MemberDTO checkMember(String work, Scanner sc) {
		System.out.println();
		System.out.println("== "+work+" ==");
		System.out.print("id = ");
		String id = sc.nextLine();
		System.out.print("password = ");
		String password =sc.nextLine();
		
		return new MemberDTO(id, password, null);	
	}

	public void showMember(MemberDTO dto) {
		System.out.println(dto.toString());
	}

	public void showMemberFail() {
		System.out.println("해당 회원이 존재하지 않습니다.");
	}
	
	//포인트 잔액 부족
	public boolean noPoint(int outPoint, MemberDTO dto) {
		if(outPoint>dto.getGameMoney()) {
			System.out.println("포인트가 부족합니다.");
			System.out.println("GamePoint를 충전해주세요.");
			return true;
		}else {
			return false;
		}
	}
	
	//태정
//	public void returnSuccese(int money, int index) {
//       
//		System.out.println("고객님의 계좌로" + money + "원 이체되었습니다.");
//        int gameMoney = ds.list.get(index).getGameMoney() - money;
//        ds.list.get(index).setGameMoney(gameMoney);
//	}   
  
	public void returnFail(){
		System.out.println("포인트가 10000 이하이거나 금액이모자랍니다. (10000 포인트 이상부터 환급 가능)");
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
    
    //회원 삭제
    public void removeSuccess() { 
       System.out.println("회원 정보 삭제 성공!!");
    }

    public void removeFail() {
       System.out.println("회원 정보 삭제 실패!!");
    }
    
    ////////////////////////////////////////////////////////////////
    /*회원 가입*/
	public MemberDTO getNewMember(Scanner sc) {
		MemberDAO dao = new MemberDAO();
		RegularExpress re = new RegularExpress(); //정규표현식 확인 클래스
		boolean daocheck=false; //id 중복여부 확인
		boolean spacecheck = false; //id 공백문자포함 확인
		boolean pwcheck = false; //password 유효성 검사
		boolean emailcheck = false; //email 유효성 검사
		System.out.println("등록할 회원의 정보를 입력해주세요.");
		//id 입력
		String id;
		do {
			daocheck=false;
			spacecheck = false;
			System.out.print("아이디 = ");
			id = sc.nextLine();
			//유효성 체크(id 중복여부 확인)
			daocheck = dao.checkId(id);
			if(daocheck) System.out.println("입력하신 id가 이미 존재합니다.");
			
			//유효성 체크(공백포함여부 확인)
			if(id.contains(" ")) {
				System.out.println("입력하신 id의 공백이 존재합니다.");
				spacecheck = true;
			}
		}while(daocheck || spacecheck);
		
		//password 입력(대문자 1개이상, 총 7자 이상, 특수문자 1개 이상)
		String password;
		do {
			System.out.print("비밀번호(대문자와 특수문자(!@#$%^&)1개이상 포함, 최소7자리) = ");
			password = sc.nextLine();
			pwcheck = re.checkPassword(password);
		}while(!pwcheck);
		
		System.out.print("이름(영문명) = ");
		String name = sc.nextLine();
		System.out.print("연락처 = ");
		String phone = sc.nextLine();
		System.out.print("고객 계좌 정보 = ");
		String account = sc.nextLine();
		
		String email;
		//이메일 입력 및 유효성 검사
		do {
			System.out.print("이메일 = ");
			email = sc.nextLine();
			emailcheck = re.checkEmail(email);
		}while(!emailcheck);
		
		//String id, String password, String name, String phone, String account, String email
		return new MemberDTO(id, password, name, phone, account, email);
	}
	
	
	public void showFail() {
		System.out.println("회원등록 실패!!");
	}
	
	public void showSuccess() {
		System.out.println("회원등록 성공!!"); 
	}
     //////////////////////////////////////////////////////////////
	/*비밀번호 변경*/
    // 회원비밀번호 변경전 id / 이메일 확인
    public MemberDTO checkInformation(Scanner sc) {
    		System.out.println("비밀번호를 변경할 회원정보를 확인하겠습니다.");
    	 	System.out.print("id = ");
    	 	String id = sc.nextLine();
    	 	System.out.print("email = ");
    	 	String email = sc.nextLine();
    	 	
    	 	return new MemberDTO(id, "A", email);
    }
     
    // 회원 비밀번호 변경
    public String modifyPassword(Scanner sc) {
    	 	System.out.println("변경할 비밀번호를 입력해주세요.");
    	 	String password;
    	 	//유효성 검사
    	 	RegularExpress re = new RegularExpress();
    	 	while(true) {
    	 		System.out.print("password = ");
        	 	password = sc.nextLine();
    	 		if(re.checkPassword(password)) break;
    	 	}
    	 	MemberDTO dto = new MemberDTO();
    	 	return dto.hashSHA256(password);
    }
     
    public void modifyPasswordSuccess() {
 		System.out.println("비밀번호 변경이 완료되었습니다.");	
 	}
     
     
     /////////////////////////////////////////////////////////////////
     /* 포인트 충전 및 성공 실패여부 출력 */
     
     public int pointCharge(Scanner sc) {
    	 	int money=0;
    	 	System.out.print("충전할 포인트를 입력하세요 = ");
 		money = Integer.parseInt(sc.nextLine());
    	 	
 		return money;
     }
 	
     public void chargeSuccese(int money) {
    	 	System.out.println(money + "원이 충전 완료");
 	}   
   
     public void chargeFail(){
    	 	System.out.println("충전 실패");
     }
     
     ///////////////////////////////////////////////////////////////
     /*포인트 환급*/
	public int pointReturn(Scanner sc, MemberDTO dto) {
		System.out.println("현재잔액 = "+dto.getGameMoney());
		System.out.println("출금하실 금액을 입력해주세요.");
		do {
			System.out.print("출금금액 = ");
			int money = Integer.parseInt(sc.nextLine());
			if(money<=dto.getGameMoney()) {
				return money;
			}else {
				System.out.println("잔액이 부족합니다.");
			}
		}while(true);

	}

	public void pointReturnSuccess(int money, MemberDTO dto) {
		System.out.println(dto.getName()+"님의 "+dto.getAccount()+"계좌로 "+money+"원 출금 완료 하였습니다.");
		System.out.println("잔액 = "+dto.getGameMoney());
	}

	public void pointReturnFail() {
		System.out.println("출금이 실패하였습니다.");
	}

	
     
}


	

