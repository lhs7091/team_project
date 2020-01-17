package mms.royal.vo;

public class RegularExpress {
	//멤버필드
	//정규식으로 적용할 문자
	String upperChar = "^[A-Z*]*$"; //대문자
	String specialChar = "^[!@#$%^&*]*$"; //특수문자
	String spaceChar = " "; //공백문자
	String emailChar = "^\\S+@\\S+.(com|co.kr|net|or.kr)$"; //email 패턴
	//\S : 공백문자가 아닌 모든 문자 // + : 앞문자가 하나이상
	//^:문자열의 시작 // $:문자열의 종료 // * :  앞 문자가 없을수도 무한정 많을수도 있음

	//password 유효성 검사
	//password 입력(대문자 1개이상, 총 7자 이상, 특수문자 1개 이상)
	public boolean checkPassword(String password) {	

		int upper=0; //대문자수
		int special=0; //특수문자
		int space=0; //공백문자
		boolean check = false;
		
		//아스키코드값으로 각각 비교
		for(int i = 0; i<password.length(); i++) {
			String chr = String.valueOf(password.charAt(i));
			//대문자인경우
			if(chr.matches(upperChar)) upper++;
			//특수문자인경우
			else if(chr.matches(specialChar)) special++;
			//공백문자인경우
			else if(chr.matches(spaceChar)) space++;
		}
		
		if(upper == 0) {
			System.out.println("대문자가 없습니다.");
		}
		if(special == 0) {
			System.out.println("특수문자가 없습니다.");
		}
		if(password.length()<7) {
			System.out.println("글자수가 적습니다.");
		}
		if(password.contains(spaceChar)) {
			System.out.println("공백이 포함되어 있습니다.");
		}
		
		if(upper != 0 && special != 0 && password.length()>=7 && space == 0) {
			check = true;
		}
		
		return check;
	}

	public boolean checkEmail(String email) {
		if(email.matches(emailChar)) {
			return true;
		}else {
			System.out.println("email 입력형식이 올바르지 않습니다.");
			return false;
		}
	}

}
