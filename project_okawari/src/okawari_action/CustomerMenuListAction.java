package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.CartDTO;
import okawari_model.MenuDTO;
import okawari_model.UserDTO;
import okawari_svc.AddCartService;
import okawari_svc.MenuListService;

public class CustomerMenuListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		// 리스트를 불러올 작업
		request.setCharacterEncoding("utf-8");
		MenuListService listService = new MenuListService();
		List<MenuDTO> menuList = null;
		ArrayList<CartDTO> cartList = null;

		HttpSession session = request.getSession();
		UserDTO stock_branch_id = (UserDTO) session.getAttribute("session");
		
//		stock_branch_id.setUser_id(stock_branch_id.getUser_id().substring(0, stock_branch_id.getUser_id().length()-1));
		
//		System.out.println(stock_branch_id.getUser_id());
		
		menuList = listService.branchMenuListCustomersv(stock_branch_id.getUser_id(), "y");

		// 주문리스트 전체 지우기 - 날리기
		if (request.getParameter("cancle_all") != null) {
			AddCartService cancleService = new AddCartService();
			cancleService.clearCart(request);
		}

		// 주문한 리스트에서 차감 및 삭제 기능
		// 이름 클릭 1개씩 줄어들고, 0이되면 삭제
		int menu_num = 0;
		if (request.getParameter("menu_num_dec") != null) {
			menu_num = Integer.parseInt(request.getParameter("menu_num_dec"));

			// 입력받은 제품의 정보를 dto로 생성해보아요..
			AddCartService decService = new AddCartService();
			CartDTO dto = decService.getCartMenu(menu_num);
			cartList = decService.decCart(dto, request);
		}

		// 메뉴 버튼을 누르면 일로 와용...
		if (request.getParameter("menu_num_add") != null) {
			menu_num = Integer.parseInt(request.getParameter("menu_num_add"));

			// 입력받은 제품의 정보를 dto로 생성해보아요..
			AddCartService addService = new AddCartService();
			CartDTO dto = addService.getCartMenu(menu_num);
			cartList = addService.addCart(dto, request);
		}

		if (menuList.size() > 0) {
			request.setAttribute("menuList", menuList);
			if (cartList != null) {
				request.setAttribute("cartList", cartList);
				int totalPrice = 0;
				for (int i = 0; i < cartList.size(); i++) {
					totalPrice += cartList.get(i).getMenu_price() * cartList.get(i).getQty();
				}
				request.setAttribute("totalPrice", totalPrice);
			}
			af = new ActionForward("customerPage.jsp", false);
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('売れる商品がありません。');location.href='customerPage.jsp';");
			out.print("</script>");
		}

		return af;

	}

}
