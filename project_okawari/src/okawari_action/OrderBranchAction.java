package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.OrderBranchDTO;
import okawari_model.UserDTO;
import okawari_svc.FindMenuPriceService;
import okawari_svc.NextOrderNumService;
import okawari_svc.OrderBranchService;

public class OrderBranchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 요청 조회

		int order_menu_num = Integer.parseInt(request.getParameter("order_menu_num")); // jsp페이지에서 입력한 값을 갖고오는것
		int order_count = Integer.parseInt(request.getParameter("order_count")); // jsp페이지에서 입력한 값을 갖고오는것

		// 필요한 내용을 변수로 받기
		// 1. 주문 번호 받기
		NextOrderNumService nons = new NextOrderNumService();
		int order_num = nons.selectLastOrderNum() + 1;

		// 2. 주문지점 id >> user_id하고 order_branch_id하고 중복됨
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("session");

		// 3. 주문날짜 및 시간
		// dao 클래스 사용합니다...

		// 4. 메뉴 번호 >> 위에 요청 조회

		// 5. 개당 가격조회
		FindMenuPriceService fmps = new FindMenuPriceService();
		int order_perprice = fmps.selectMenuPrice(order_menu_num);

		// 6 전체 가격
		int order_topprice = order_count * order_perprice;

		// 7. 저장할 정보의 객체를 만들어 내본다.
		OrderBranchDTO dto = new OrderBranchDTO();

		// 8. 입력할 정보이 객체를 생성한다.
		dto.setOrder_num(order_num);
		dto.setOrder_branch_id(userdto.getUser_id());
		dto.setOrder_menu_num(order_menu_num);
		dto.setOrder_count(order_count);
		dto.setOrder_perprice(order_perprice);
		dto.setTotprice(order_topprice);

		// 8. 주문테이블에 추가 할 것들
		OrderBranchService obs = new OrderBranchService();
		boolean insertResult = obs.insertOrderBranch(dto);

		// ActionForward 객체 생성 후
		ActionForward af = null;
		// insertResult에 따라서 return 형태를 설정

		// insertResult에 따라서 return 형태를 설정한다.
		if (insertResult) { // 입력 완료되었으니 목록으로 간다.
			af = new ActionForward("orderBranchList.okawari", false);
		} else { // 입력에 문제가 발생하였으니, 뒤로간다.
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('주문이 실패하였습니다.注文に失敗しました。');");
			out.print("history.back();");
			out.print("</script>");
		}

		return af;
	}

}
