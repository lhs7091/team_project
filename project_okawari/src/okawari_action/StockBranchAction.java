package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.StockBranchDTO;
import okawari_model.UserDTO;
import okawari_svc.StockBranchService;

public class StockBranchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		request.setCharacterEncoding("utf-8");
		StockBranchService listService = new StockBranchService();
		ArrayList<StockBranchDTO> stockList = null;

		HttpSession session = request.getSession();

		// login에서 session에 dto를 저장했기때문에 DTO객체를 불러 캐스팅
		UserDTO dto = (UserDTO) session.getAttribute("session");

		// 불러온 DTO객체에서 현재 로그인되어있는 아이디를 불러와서 파라미터로 보냄
		stockList = listService.ShowStockBranch(dto.getUser_id());

		if (stockList != null) {
			request.setAttribute("list", stockList);
			af = new ActionForward("showBranchStock.jsp", false);

		} else {
			System.out.println("null");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('주문내역이 존재하지 않습니다.注文リストがございません。');");
			out.print("</script>");
		}

		return af;
	}

}