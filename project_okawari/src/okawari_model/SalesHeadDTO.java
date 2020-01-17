package okawari_model;

import java.sql.Date;

public class SalesHeadDTO {
	private String sales_branch_id;	// 판매지점
	private Date sales_date;	// 판매일	
	private int sales_total;	// 판매총액
	private int sales_cost;		// 판매원가
	private int sales_junk;		// 폐기원가
	private int sales_commission;	// 로얄티
	private int sales_origin;		// 순이익

	public SalesHeadDTO(String sales_branch_id, Date sales_date, int sales_total, int sales_cost, int sales_junk, int sales_commission, int sales_origin) {
		super();
		this.sales_branch_id = sales_branch_id;	
		this.sales_date = sales_date;	
		this.sales_total = sales_total;
		this.sales_cost = sales_cost;
		this.sales_junk = sales_junk;
		this.sales_commission = sales_commission;
		this.sales_origin = sales_origin;
	}
	
	public SalesHeadDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getSales_branch_id() {
		return sales_branch_id;
	}

	public void setSales_branch_id(String sales_branch_id) {
		this.sales_branch_id = sales_branch_id;
	}

	public Date getSales_date() {
		return sales_date;
	}

	public void setSales_date(Date date) {
		this.sales_date = date;
	}

	public int getSales_total() {
		return sales_total;
	}

	public void setSales_total(int sales_total) {
		this.sales_total = sales_total;
	}

	public int getSales_cost() {
		return sales_cost;
	}

	public void setSales_cost(int sales_cost) {
		this.sales_cost = sales_cost;
	}

	public int getSales_junk() {
		return sales_junk;
	}

	public void setSales_junk(int sales_junk) {
		this.sales_junk = sales_junk;
	}

	public int getSales_commission() {
		return sales_commission;
	}

	public void setSales_commission(int sales_commission) {
		this.sales_commission = sales_commission;
	}

	public int getSales_origin() {
		return sales_origin;
	}

	public void setSales_origin(int sales_origin) {
		this.sales_origin = sales_origin;
	}

}
