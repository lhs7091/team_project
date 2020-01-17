package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.OrderBranchDTO;
import okawari_model.UserDTO;
import okawari_svc.ShowAllOrderBranchService;

public class OrderBranchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 주문목록을 불러와야 됨.
		// 그럼 먼저 주문목록 테이블을 조회하자..
		// 그리고 여러가지가 있으니까. ArrayList로 넘기자..
		// 그런데 만약 본사일경우는 전체목록,, 지점일 경우 자기 지점만 갖고 와야 된다...

		ActionForward af = null;
		ArrayList<OrderBranchDTO> list = new ArrayList<OrderBranchDTO>();
		HttpSession session = request.getSession();
		UserDTO dto = (UserDTO) session.getAttribute("session");

		if (dto.getUser_auth() == 1) { // 본사일 경우 리스트
			ShowAllOrderBranchService svc = new ShowAllOrderBranchService();
			list = svc.showAllOrderBranch();
		} else if (dto.getUser_auth() == 2) { // 각 지점의 지점장일경우
			ShowAllOrderBranchService svc = new ShowAllOrderBranchService();
			list = svc.showEachOrderBranch(dto.getUser_id());
		}

		// jsp로 전송할 수 있도록 담아라...
		request.setAttribute("list", list);

		// 그리고 넘겨라
		if (list != null) {
			af = new ActionForward("orderBranchList.jsp", false);
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('주문목록이 존재하지 않습니다.注文リストがございません。');");
			out.print("history.back();");
			out.print("</script>");
		}

		return af;
	}

}
