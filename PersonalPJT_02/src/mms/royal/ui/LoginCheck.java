package mms.royal.ui;

import java.util.*;

import mms.royal.svc.CheckMemberService;
import mms.royal.vo.MemberDTO;

public class LoginCheck {

	public MemberDTO login(Scanner sc) {
		boolean check = false;
		/* 사용자의 id와 비밀번호를 입력 받기 */
		System.out.println();
		System.out.println("사용자의 id와 비밀번호를 입력하세요");
		System.out.print("id = ");
		String id = sc.nextLine();
		System.out.print("password = ");
		String password = sc.nextLine();
		
		/* 입력받은 id와 비밀번호를 객체생성 */
		MemberDTO dto = new MemberDTO(id,password,null);
		
		/* dto 객체가 DB에 존재하는지 확인 */
		CheckMemberService cms = new CheckMemberService();
		check = cms.checkMemberSvc(dto);
		
		if(check) {
			return dto;
		}
		
		return null;
	}
	
	

}
