package okawari_model;

public class MenuDTO {
	private int menu_num;
	private String menu_name;
	private String menu_image;
	private int menu_price;
	private String menu_check;
	private int menu_count;

	// 0907
	private int menu_cost;

	public int getMenu_cost() {
		return menu_cost;
	}

	public void setMenu_cost(int menu_cost) {
		this.menu_cost = menu_cost;
	}

	public MenuDTO() {
		super();
	}

	public MenuDTO(int menu_num, String menu_name, int menu_price, String menu_image, String menu_check) {
		super();
		this.menu_num = menu_num;
		this.menu_name = menu_name;
		this.menu_price = menu_price;
		this.menu_image = menu_image;
		this.menu_check = menu_check;
	}

	// 추가한것
	public MenuDTO(int menu_num, String menu_name, int menu_price, int menu_count) {
		super();
		this.menu_num = menu_num;
		this.menu_name = menu_name;
		this.menu_price = menu_price;
		this.menu_count = menu_count;
	}

	public MenuDTO(int menu_num, String menu_name, String menu_image, int menu_price, String menu_check,
			int menu_count) {
		super();
		this.menu_num = menu_num;
		this.menu_name = menu_name;
		this.menu_image = menu_image;
		this.menu_price = menu_price;
		this.menu_check = menu_check;
		this.menu_count = menu_count;
	}

	public int getMenu_num() {
		return menu_num;
	}

	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
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

	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}

	public String getMenu_check() {
		return menu_check;
	}

	public void setMenu_check(String menu_check) {
		this.menu_check = menu_check;
	}

	public void setMenu_count(int menu_count) {
		this.menu_count = menu_count;
	}

	public int getMenu_count() {
		return menu_count;
	}

	public String getMenu_image() {
		return menu_image;
	}

	public void setMenu_image(String menu_image) {
		this.menu_image = menu_image;
	}

}