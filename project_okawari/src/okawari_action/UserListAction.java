package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_model.UserDTO;
import okawari_svc.UserService;

public class UserListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		request.setCharacterEncoding("utf-8");
		// userTable list를 보고 싶어용..
		// 먼저 table에 내용을 모두 arraylist에 불러 옵니다.
		UserService us = new UserService();
		ArrayList<UserDTO> userList = us.showUserList();

		// 만약 userList에 내용이 담겨있다면
		if (userList != null) {
			// 담아온 userList를 forwarding 해주어야 합니다
			request.setAttribute("userList", userList);

			// 그리고 다음 이동할 곳으로 지정해주고요..
			af = new ActionForward("userList.jsp", false);
		} else {
			// 담아온 내용이 없네요..
			// 그럼 뒤로 돌아가야죠..
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('지점들이 존재하지 않습니다.支店が登録されていません。');");
			out.print("history.back();");
			out.print("</script>");
			out.flush();
		}

		return af;
	}

}
