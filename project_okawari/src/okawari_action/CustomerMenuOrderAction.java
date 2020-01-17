package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.CartDTO;
import okawari_model.MenuDTO;
import okawari_model.OrderDTO;
import okawari_model.UserDTO;
import okawari_svc.CustomerOrderService;

public class CustomerMenuOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 구매자가 구매한 물건
		CustomerOrderService customerOrderInsert = new CustomerOrderService();

		request.setCharacterEncoding("utf-8");
		List<OrderDTO> customerOrderList = new ArrayList<OrderDTO>();
		ActionForward af = null;
		HttpSession cartSession = request.getSession();
		ArrayList<CartDTO> cartList = (ArrayList<CartDTO>) cartSession.getAttribute("cartList");

		HttpSession session = request.getSession();
		UserDTO dto = (UserDTO) session.getAttribute("session");
		String stock_branch_id = dto.getUser_id();

		// 자료 담긴
		for (int i = 0; i < cartList.size(); i++) {
			OrderDTO customerOrder = new OrderDTO();
			customerOrder.setOrder_branch_id(dto.getUser_id());
			customerOrder.setOrder_menu_num(cartList.get(i).getMenu_num());
			customerOrder.setOrder_menu_name(cartList.get(i).getMenu_name());
			System.out.println("cartList : "+cartList.get(i).getMenu_name());
			customerOrder.setOrder_count(cartList.get(i).getQty());
			customerOrder.setOrder_perprice(cartList.get(i).getMenu_price());
			int price = cartList.get(i).getMenu_price();
			int qty = cartList.get(i).getQty();
			customerOrder.setOrder_totprice(price * qty);
			customerOrderList.add(i, customerOrder);
		}

		List<MenuDTO> stockList = null;
		stockList = customerOrderInsert.stockCheck(stock_branch_id, customerOrderList);

		// 주문 된것 원가 기입
		for (int i = 0; i < customerOrderList.size(); i++) {
			for (int j = 0; j < stockList.size(); j++) {
				if (customerOrderList.get(i).getOrder_menu_num() == stockList.get(j).getMenu_num())
					;
				customerOrderList.get(i).setOrder_oriprice(stockList.get(j).getMenu_cost());

			}
		}

		if (stockList.size() > 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('申し訳ございません。ご注文の商品");
			for (int i = 0; i < stockList.size(); i++) {
				if (stockList.get(i).getMenu_count() == 0) {
					out.print(stockList.get(i).getMenu_name() + "は在庫がございません。");
				} else if (stockList.size() == i) {
					// 주문을 140 개 했는데 창고에는 100개만 있을경우 40개의 재고가 부족하다고 뜨는것.
					out.print(
							stockList.get(i).getMenu_name() + "の " + stockList.get(i).getMenu_count() + " 個、注文できます。  ");
				} else {
					out.print(stockList.get(i).getMenu_name() + "の " + stockList.get(i).getMenu_count() + " 個、足りません。");
				}
			}
			out.print("改めてご注文お願い申し上げます。');location.href='customerPage.okawari';");
			out.print("</script>");
			out.flush();

		} else {
			// 주문 가능한 상태에서 원가 집어넣기
			List<OrderDTO> customerOrderList2 = customerOrderInsert.oriOrderCheckInsert(stock_branch_id,
					customerOrderList);
			
			// 주문 가능한 상태일시 시작
			boolean decCheck = false; // stock 재고 차감
			decCheck = customerOrderInsert.decStock(stock_branch_id, customerOrderList2);

			if (decCheck) {// DB 작업 (고객 주문내역 추가, 추가되었으면 고객번호 불러오기)
				int orderNumber = -1;
				orderNumber = customerOrderInsert.insertMenuOrderCustomer(customerOrderList2);
				af = new ActionForward("/customerPage.okawari",false);
				// DB에 담긴 내용을 토대로, 메시지 출력
				if (orderNumber != -1) {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					cartSession.removeAttribute("cartList");
					out.print("<script>");
					out.print("alert('注文番号 " + orderNumber
							+ " でご注文できました。<br>ご利用ありがとうございます。');location.href='customerPage.okawari';");
					out.print("</script>");
					out.flush();

				} else {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('申し訳ございません。只今、" + orderNumber
							+ "준비가 되어이지 않습니다. は準備中です。');location.href='customerPage.okawari';");
					out.print("</script>");
					out.flush();
				}
			}
		}
		return null;
	}

}