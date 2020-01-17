package okawari_action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;

public class MainPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 메인 페이지로 이동하기
		HttpSession session = (HttpSession) request.getAttribute("session");
		request.setAttribute("session", session);
		return new ActionForward("mainPage.jsp", false);
	}

}
