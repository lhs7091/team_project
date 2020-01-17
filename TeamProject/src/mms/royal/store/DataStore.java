package mms.royal.store;

import java.util.ArrayList;

import mms.royal.vo.MemberDTO;

public class DataStore {
	
	public static ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();

	public MemberDTO showMember(int index) {
			return list.get(index);
	}
	
	 //저장공간에 저장된 하나의 객체를 제거하는 작업을 수행하는 메서드 - 이름 서창우
    public boolean delete(MemberDTO deleteMember,String name) {
		boolean check=false;
		for(int i=0;i<list.size();i++) {
			if(name.equals(list.get(i).getName())){
				check=true;
				list.remove(i);   
		}
	}
      return check; 
   }
    
    //저장공간에 저장된 하나의 객체를 리턴시켜주는 메서드- 이름 서창우
  
   public MemberDTO select(String name) {
      MemberDTO dto=null;
      for(int i=0;i<list.size();i++) {
         if(list.get(i).getName().equals(name)) {      
            dto=list.get(i);
         }
      }
      return dto;    
   }
   
   //민진

   public boolean insert(MemberDTO newMember) {
      boolean check = true;
      try {
         list.add(newMember);
      }catch(Exception e) {
         check = false;
      }
      return check;
   }
   
   // 비밀번호 변경전 입력받은 id 와 email 일치여부 확인
	public boolean checkInformation(MemberDTO dto, int index) {
		boolean result = false;
		if(dto.getId().equals(list.get(index).getId())) {
			if(dto.getEmail().equals(list.get(index).getEmail())) {
				result=true;
			}else {
				System.out.println("입력하신 email이 잘못되었습니다.");
			}
		}else {
			System.out.println("입력하신 id가 존재하지 않습니다.");
		}
		return result;
	}
	//일치할 경우 변경처리
	public void modifyPassword(String password, int index) {
		list.get(index).setPassword(password);
		System.out.println("변경이 완료되었습니다.");
	}
}
