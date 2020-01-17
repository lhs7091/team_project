package okawari_model;

public class CartDTO {
	private int menu_num;
	private String menu_image; // 상품이미지
	private String menu_name; // 상품명(품종)
	private int menu_price; // 상품가격
	private int qty; // 수량

	public CartDTO(int menu_num, String menu_image, String menu_name, int menu_price, int qty) {
		super();
		this.menu_num = menu_num;
		this.menu_image = menu_image;
		this.menu_name = menu_name;
		this.menu_price = menu_price;
		this.qty = qty;
	}

	public int getMenu_num() {
		return menu_num;
	}

	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}

	public String getMenu_image() {
		return menu_image;
	}

	public void setMenu_image(String menu_image) {
		this.menu_image = menu_image;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getMenu_price() {
		return menu_price;
	}

	public void setMenu_perprice(int menu_price) {
		this.menu_price = menu_price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
