package okawari_action;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.SalesHeadDTO;
import okawari_model.UserDTO;
import okawari_svc.SalesHeadService;
import okawari_svc.UserService;

public class ShowHeadSalesAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		// 본사의 매출을 조회합니다.
		// 매출을 조회할때 본사 뿐만아니라, 지점의 매출도 조회해야 합니다.
		// 일단 기본적으로 일단위로 출력하는데, 날짜를 입력받아야 되요..
		HttpSession session = request.getSession();
		// login에서 session에 dto를 저장했기때문에 DTO객체를 불러 캐스팅
		UserDTO dto = (UserDTO) session.getAttribute("session");
		String session_id = dto.getUser_id();
		String user_id = request.getParameter("user_id");
		String startDate = request.getParameter("start");
		String endDate = request.getParameter("end");

		// 조회할 user 정보를 넘겨야 합니다.
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		UserService us = new UserService();
		userList = us.showUserList();
		request.setAttribute("userList", userList);

		// 일단위로 저장된 값을 불러와야 되는데요...
		// 전체를 다 갖고 오고,,, 날짜가 같은것을 더할까요?
		ArrayList<SalesHeadDTO> salesList = new ArrayList<SalesHeadDTO>();

		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start = null;
			Date end = null;
			try {
				start = new Date(sdf.parse(request.getParameter("start")).getTime());
				end = new Date(sdf.parse(request.getParameter("end")).getTime());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 합계를 하는데 날짜가 동일한것끼리로 해보자..
			// 본사가 조회하는 경우
			if (session_id.equals("headoffice") && user_id.equals("headoffice")) {
				SalesHeadService shs = new SalesHeadService();
				salesList = shs.showSalesHead(start, end);
			} else { // 지사 내용을 조회했을때.. 각 지사 내용만 표시..
				SalesHeadService shs = new SalesHeadService();
				salesList = shs.showSalesBranch(start, end, user_id);

			}
			UserDTO user = new UserDTO();
			user = us.showOneUser(user_id);
			request.setAttribute("dto", user);
			request.setAttribute("salesList", salesList);
		}

		af = new ActionForward("showHeadSales.jsp", false);

		return af;
	}

}
