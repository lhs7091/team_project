package okawari_model;

public class StockBranchDTO {

	private String stock_branch_id; // 지점 아이디
	private int stock_menu_num; // 메뉴번호
	private int stock_count; // 수량
	private int stock_cost; // 원가
	private int stock_price; // 판매가
	private String stock_expiredate; // 유통기한
	private String stock_junk; // 폐기

	public StockBranchDTO() {

	}

	public StockBranchDTO(String stock_branch_id, int stock_menu_num, int stock_count, int stock_cost, int stock_price,
			String stock_expiredate, String stock_junk) {
		super();
		this.stock_branch_id = stock_branch_id;
		this.stock_menu_num = stock_menu_num;
		this.stock_count = stock_count;
		this.stock_cost = stock_cost;
		this.stock_price = stock_price;
		this.stock_expiredate = stock_expiredate;
		this.stock_junk = stock_junk;
	}

	public String getStock_branch_id() {
		return stock_branch_id;
	}

	public void setStock_branch_id(String stock_branch_id) {
		this.stock_branch_id = stock_branch_id;
	}

	public int getStock_menu_num() {
		return stock_menu_num;
	}

	public void setStock_menu_num(int stock_menu_num) {
		this.stock_menu_num = stock_menu_num;
	}

	public int getStock_count() {
		return stock_count;
	}

	public void setStock_count(int stock_count) {
		this.stock_count = stock_count;
	}

	public int getStock_cost() {
		return stock_cost;
	}

	public void setStock_cost(int stock_cost) {
		this.stock_cost = stock_cost;
	}

	public int getStock_price() {
		return stock_price;
	}

	public void setStock_price(int stock_price) {
		this.stock_price = stock_price;
	}

	public String getStock_expiredate() {
		return stock_expiredate;
	}

	public void setStock_expiredate(String stock_expiredate) {
		this.stock_expiredate = stock_expiredate;
	}

	public String getStock_junk() {
		return stock_junk;
	}

	public void setStock_junk(String stock_junk) {
		this.stock_junk = stock_junk;
	}

}
