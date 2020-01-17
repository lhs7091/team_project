package okawari_model;

public class UserDTO {
	private String user_id;
	private String user_pass;
	private int user_auth;
	private String user_name;
	private String user_manager;
	private String user_addr;
	private String user_phone;
	private String user_businum;
	private int user_commission;
	private String user_opendate;

	public UserDTO() {
	}

	public UserDTO(String user_id, String user_pass, int user_auth, String user_name, String user_manager,
			String user_addr, String user_phone, String user_businum, int user_commission, String user_opendate) {
		super();
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.user_auth = user_auth;
		this.user_name = user_name;
		this.user_manager = user_manager;
		this.user_addr = user_addr;
		this.user_phone = user_phone;
		this.user_businum = user_businum;
		this.user_commission = user_commission;
		this.user_opendate = user_opendate;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public int getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(int user_auth) {
		this.user_auth = user_auth;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_manager() {
		return user_manager;
	}

	public void setUser_manager(String user_manager) {
		this.user_manager = user_manager;
	}

	public String getUser_addr() {
		return user_addr;
	}

	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_businum() {
		return user_businum;
	}

	public void setUser_businum(String user_businum) {
		this.user_businum = user_businum;
	}

	public int getUser_commission() {
		return user_commission;
	}

	public void setUser_commission(int user_commission) {
		this.user_commission = user_commission;
	}

	public String getUser_opendate() {
		return user_opendate;
	}

	public void setUser_opendate(String user_opendate) {
		this.user_opendate = user_opendate;
	}

}
