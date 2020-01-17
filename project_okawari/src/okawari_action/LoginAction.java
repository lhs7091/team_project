package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_model.ActionForward;
import okawari_model.UserDTO;
import okawari_svc.LoginService;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		// 아이디와 비밀번호를 입력받아 DB에 해당 객체가 존재하는지 파악한다.
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String user_pass = request.getParameter("user_pass");

		// 세션 생성을 위함
		HttpSession session = request.getSession();

		// 서버 있는지 확인
		LoginService ls = new LoginService();
		UserDTO dto = ls.loginUser(user_id, user_pass);

		// 서버있다면, 세선생성
		// 세션을 뭘로 할건지?? 담을 내용...

		if (dto != null) {
			session.setAttribute("session", dto);
			if (dto.getUser_auth() == 1) { // 본사관리자
				af = new ActionForward("mainPage.okawari", false);
			} else if (dto.getUser_auth() == 2) { // 지점 관리자
				af = new ActionForward("mainPage.okawari", false);
			} else if (dto.getUser_auth() == 3) {
				af = new ActionForward("customerPage.okawari", false);

			}
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원정보가 존재하지 않습니다.ご入力のIDおよび、パスワードを確認して下さい。');");
			out.print("history.back();");
			out.print("</script>");
		}

		return af;

	}

}
