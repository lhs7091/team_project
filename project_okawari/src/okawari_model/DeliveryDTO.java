package okawari_model;

public class DeliveryDTO {
	private int order_num;
	private String stock_branch_id;
	private int stock_menu_num;
	private int stock_count;
	private int stock_cost;
	private int stock_price;
	private String stock_expiredate;
	
	public DeliveryDTO() {}
	public DeliveryDTO(String stock_branch_id, int stock_menu_num, int stock_count, int stock_cost, int stock_price,
			String stock_expiredate) {
		super();
		this.stock_branch_id = stock_branch_id;
		this.stock_menu_num = stock_menu_num;
		this.stock_count = stock_count;
		this.stock_cost = stock_cost;
		this.stock_price = stock_price;
		this.stock_expiredate = stock_expiredate;
	}
	public DeliveryDTO(int order_num, String stock_branch_id, int stock_menu_num, int stock_count, int stock_cost, int stock_price,
			String stock_expiredate) {
		super();
		this.order_num = order_num;
		this.stock_branch_id = stock_branch_id;
		this.stock_menu_num = stock_menu_num;
		this.stock_count = stock_count;
		this.stock_cost = stock_cost;
		this.stock_price = stock_price;
		this.stock_expiredate = stock_expiredate;
	}
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
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

	
}
