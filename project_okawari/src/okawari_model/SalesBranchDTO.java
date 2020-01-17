package okawari_model;

import java.sql.Date;

public class SalesBranchDTO {
   private String sales_branch_id;
   private Date sales_date;   
   private int sales_total;
   private int sales_cost;
   private int sales_commission;
   private int sales_origin;


   public SalesBranchDTO(String sales_branch_id, Date sales_date, int sales_total, int sales_cost,
         int sales_commission, int sales_origin) {
      super();
      this.sales_branch_id = sales_branch_id;
      this.sales_date = sales_date;
      this.sales_total = sales_total;
      this.sales_cost = sales_cost;
      this.sales_commission = sales_commission;
      this.sales_origin = sales_origin;
   }


   public SalesBranchDTO() {
   }

   
   public Date getSales_date() {
      return sales_date;
   }

   public void setSales_date(Date sales_date) {
      this.sales_date = sales_date;
   }

   public String getSales_branch_id() {
      return sales_branch_id;
   }

   public void setSales_branch_id(String sales_branch_id) {
      this.sales_branch_id = sales_branch_id;
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