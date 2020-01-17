package okawari_model;

public class OrderDTO {
   private int order_num;
   private String order_date;
   private String order_time;
   private int order_menu_num;
   private String order_menu_name;
   private int order_count;
   private int order_perprice;
   private int order_totprice;

   ///추가된것 0909
   private int order_oriprice;
   private String order_branch_id;
   public String getOrder_branch_id() {
      return order_branch_id;
   }

   public void setOrder_branch_id(String order_branch_id) {
      this.order_branch_id = order_branch_id;
   }

   public int getOrder_oriprice() {
      return order_oriprice;
   }

   public void setOrder_oriprice(int order_oriprice) {
      this.order_oriprice = order_oriprice;
   }

   public OrderDTO() {
      super();
   }

   public OrderDTO(int order_num, String order_date, String order_time, int order_menu_num, String order_menu_name,
         int order_count, int order_perprice, int order_totprice) {
      super();
      this.order_num = order_num;
      this.order_date = order_date;
      this.order_time = order_time;
      this.order_menu_num = order_menu_num;
      this.order_menu_name = order_menu_name;
      this.order_count = order_count;
      this.order_perprice = order_perprice;
      this.order_totprice = order_totprice;
   }

   public int getOrder_num() {
      return order_num;
   }

   public void setOrder_num(int order_num) {
      this.order_num = order_num;
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

   public int getOrder_menu_num() {
      return order_menu_num;
   }

   public void setOrder_menu_num(int order_menu_num) {
      this.order_menu_num = order_menu_num;
   }

   public String getOrder_menu_name() {
      return order_menu_name;
   }

   public void setOrder_menu_name(String order_menu_name) {
      this.order_menu_name = order_menu_name;
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

   public int getOrder_totprice() {
      return order_totprice;
   }

   public void setOrder_totprice(int order_totprice) {
      this.order_totprice = order_totprice;
   }

}