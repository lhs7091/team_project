package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.OrderDTO;
import okawari_model.UserDTO;
import okawari_svc.CustomerOrderService;

public class CustomerOrderCancle implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 고객 주문 취소
		request.setCharacterEncoding("utf-8");
		ActionForward af = null;
		HttpSession session = request.getSession();

		// 구매자가 구매한 물건 빼오기
		CustomerOrderService CustomerOrder = new CustomerOrderService();
		UserDTO dto = (UserDTO) session.getAttribute("session");

		// 고객 주문 취소 날짜와 주문번호 입력받으면 이쪽으로 옵니다.
		if (request.getParameter("day") != null && request.getParameter("orderNumber") != null
				&& request.getParameter("day") != "" && request.getParameter("orderNumber") != "") {
			String day = request.getParameter("day");
			int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));

			List<OrderDTO> customerOrderList = null;
			// 주문내역 가져오기
			customerOrderList = CustomerOrder.customerOrderSelect(dto.getUser_id(), day, orderNumber);
			// 주문내역 가격 종합해오기
			OrderDTO customerOrderTot = CustomerOrder.submitCustomerOrder(customerOrderList);

			if (customerOrderList.size() > 0) {

				request.setAttribute("customerOrderTot", customerOrderTot);
				request.setAttribute("customerOrderList", customerOrderList);
				request.setAttribute("totprice",
						customerOrderList.get(customerOrderList.size() - 1).getOrder_totprice());
				// 취소할때 세션 날려버림
				session.setAttribute("day", day);
				session.setAttribute("orderNumber", orderNumber);

			} else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('주문번호를 다시 확인해주세요.注文番号を確認して下さい。');location.href='customerOrderCancle.okawari';");
				out.print("</script>");
				out.flush();
			}
		}

		// 주문 취소 넘기기

		if (request.getParameter("cancle") != null) {
			String day = (String) session.getAttribute("day");
			int orderNumber = (int) session.getAttribute("orderNumber");
			boolean check = false;
			check = CustomerOrder.deleteCustomerOrderMenu(dto.getUser_id(), day, orderNumber);

			System.out.println(check);

			if (check) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				// 세션 날리기
				request.removeAttribute("day");
				request.removeAttribute("orderNumber");
				out.print("<script>");
				out.print("alert('주문번호 注文番号 " + orderNumber
						+ " 으로 취소되었습니다. はキャンセルできました。');location.href='customerOrderCancle.okawari';");
				out.print("</script>");
				out.flush();

			} else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				request.removeAttribute("day");
				request.removeAttribute("orderNumber");
				out.print("<script>");
				out.print(
						"alert('주문번호를 다시 확인해주세요. 申し訳ございません。注文番号を確認して下さい。');location.href='customerOrderCancle.okawari';");
				out.print("</script>");
				out.flush();
			}
		}

		return af = new ActionForward("customerOrderCancle.jsp", false);
	}

}