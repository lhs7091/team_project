package mms.royal.vo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//회원가입
public class MemberDTO {
	//멤버필드
	private String id; //고객 ID
	private String password; // 고객 Password
	private String name; //고객이름
	private String phone; //고객연락처
	private String account; //고객계좌정보
	private String email; //고객이메일
	private int money; //입출금 금액
	private int gameMoney; //게임머니
	
	//생성자메서드
	public MemberDTO() {}
	public MemberDTO(String id, String email) {
		this.id=id;
		this.email=email;
	}
	public MemberDTO(String id, String password, String name, String phone, String account, String email) {
		this.id = id;
		this.password = hashSHA256(password);
		this.name = name;
		this.phone = phone;
		this.account = account;
		this.email = email;
		this.gameMoney = 0;
	}
	
	//멤버메서드
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = hashSHA256(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getGameMoney() {
		return gameMoney;
	}

	public void setGameMoney(int gameMoney) {
		this.gameMoney = gameMoney;
	}
	
	//필요정보 출력
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = "+id+"\n"+
			   "Password = "+password+"\n"+
			   "name = "+name+"\n"+
		       "phone = "+phone+"\n"+
			   "account = "+account+"\n"+
		       "email = "+email+"\n"+
			   "포인트 = "+gameMoney;
	}
	
	public String hashSHA256(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
		
}
