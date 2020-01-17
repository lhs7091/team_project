package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.StockBranchDTO;
import okawari_model.StockHeadDTO;
import okawari_model.UserDTO;
import okawari_svc.StockBranchService;
import okawari_svc.StockHeadService;

public class StockHeadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		request.setCharacterEncoding("utf-8");
		StockHeadService listService = new StockHeadService();
		StockBranchService listService2 = new StockBranchService();

		ArrayList<StockHeadDTO> stockList = null;
		ArrayList<StockBranchDTO> stockList2 = null;
		ArrayList<UserDTO> userList = null;

		HttpSession session = request.getSession();

		String id = request.getParameter("stockCheck");

		UserDTO dto = (UserDTO) session.getAttribute("session");
		dto.setUser_addr(URLEncoder.encode(dto.getUser_addr(),"utf-8"));
		userList = listService.showAllUser(dto);
		stockList = listService.showStockHead(dto);
		stockList2 = listService2.ShowStockBranch(id);

		String selectId = listService.selectId(id);

		request.setAttribute("user", userList);

		if (stockList != null || stockList2 != null) {
			request.setAttribute("stockId", id);
			request.setAttribute("selectId", selectId);
			request.setAttribute("list", stockList);
			request.setAttribute("list2", stockList2);
			af = new ActionForward("showHeadStock.jsp", false);

		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('주문내역이 존재하지 않습니다.注文リストがございません。');");
			out.print("</script>");
		}

		return af;
	}

}