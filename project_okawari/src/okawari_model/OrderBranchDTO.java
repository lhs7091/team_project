package okawari_model;

public class OrderBranchDTO {

	private int order_num;
	private String order_branch_id;
	private String order_date;
	private String order_time;
	private int order_menu_num;
	private int order_count;
	private int order_perprice;
	private int totprice;
	private String order_complete_branch;
	private String order_complete_branch_date;
	private String order_complete_branch_time;
	private String order_complete_head;
	private String order_complete_date;
	private String order_complete_time;
	private String order_expired_date;

	public OrderBranchDTO() {
		super();
	}

	public OrderBranchDTO(int order_num, String order_branch_id, String order_date, String order_time, int order_count,
			int order_perprice, int totprice) {
		super();
		this.order_num = order_num;
		this.order_branch_id = order_branch_id;
		this.order_date = order_date;
		this.order_time = order_time;
		this.order_count = order_count;
		this.order_perprice = order_perprice;
		this.totprice = totprice;
	}

	public OrderBranchDTO(int order_num, String order_branch_id, String order_date, String order_time, int order_count,
			int order_perprice, int totprice, String order_complete_branch, String order_complete_branch_date,
			String order_complete_branch_time, String order_complete_head) {
		super();
		this.order_num = order_num;
		this.order_branch_id = order_branch_id;
		this.order_date = order_date;
		this.order_time = order_time;
		this.order_count = order_count;
		this.order_perprice = order_perprice;
		this.totprice = totprice;
		this.order_complete_branch = order_complete_branch;
		this.order_complete_branch_date = order_complete_branch_date;
		this.order_complete_branch_time = order_complete_branch_time;
		this.order_complete_head = order_complete_head;
	}

	public OrderBranchDTO(int order_num, String order_branch_id, String order_date, String order_time,
			int order_menu_num, int order_count, int order_perprice, int totprice, String order_complete_branch,
			String order_complete_branch_date, String order_complete_branch_time, String order_complete_head,
			String order_complete_date, String order_complete_time, String order_expired_date) {
		super();
		this.order_num = order_num;
		this.order_branch_id = order_branch_id;
		this.order_date = order_date;
		this.order_time = order_time;
		this.order_menu_num = order_menu_num;
		this.order_count = order_count;
		this.order_perprice = order_perprice;
		this.totprice = totprice;
		this.order_complete_branch = order_complete_branch;
		this.order_complete_branch_date = order_complete_branch_date;
		this.order_complete_branch_time = order_complete_branch_time;
		this.order_complete_head = order_complete_head;
		this.order_complete_date = order_complete_date;
		this.order_complete_time = order_complete_time;
		this.order_expired_date = order_expired_date;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public int getOrder_menu_num() {
		return order_menu_num;
	}

	public void setOrder_menu_num(int order_menu_num) {
		this.order_menu_num = order_menu_num;
	}

	public String getOrder_branch_id() {
		return order_branch_id;
	}

	public void setOrder_branch_id(String order_branch_id) {
		this.order_branch_id = order_branch_id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public int getOrder_count() {
		return order_count;
	}

	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}

	public int getOrder_perprice() {
		return order_perprice;
	}

	public void setOrder_perprice(int order_perprice) {
		this.order_perprice = order_perprice;
	}

	public int getTotprice() {
		return totprice;
	}

	public void setTotprice(int totprice) {
		this.totprice = totprice;
	}

	public String getOrder_complete_branch() {
		return order_complete_branch;
	}

	public void setOrder_complete_branch(String order_complete_branch) {
		this.order_complete_branch = order_complete_branch;
	}

	public String getOrder_complete_branch_date() {
		return order_complete_branch_date;
	}

	public void setOrder_complete_branch_date(String order_complete_branch_date) {
		this.order_complete_branch_date = order_complete_branch_date;
	}

	public String getOrder_complete_branch_time() {
		return order_complete_branch_time;
	}

	public void setOrder_complete_branch_time(String order_complete_branch_time) {
		this.order_complete_branch_time = order_complete_branch_time;
	}

	public String getOrder_complete_head() {
		return order_complete_head;
	}

	public void setOrder_complete_head(String order_complete_head) {
		this.order_complete_head = order_complete_head;
	}

	public String getOrder_complete_date() {
		return order_complete_date;
	}

	public void setOrder_complete_date(String order_complete_date) {
		this.order_complete_date = order_complete_date;
	}

	public String getOrder_complete_time() {
		return order_complete_time;
	}

	public void setOrder_complete_time(String order_complete_time) {
		this.order_complete_time = order_complete_time;
	}

	public String getOrder_expired_date() {
		return order_expired_date;
	}

	public void setOrder_expired_date(String order_expired_date) {
		this.order_expired_date = order_expired_date;
	}

}
