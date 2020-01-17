package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_svc.UserService;

public class UserRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;

		String user_id = request.getParameter("user_id");

		// 삭제하러 갑시다..
		// 권한을 4번으로 합니다. 죽을 사..
		UserService us = new UserService();
		boolean result = us.removeUser(user_id);

		if (result) {
			af = new ActionForward("userList.okawari", false);
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원삭제가 완료되지 않았습니다.ご選択の支店情報消去に失敗しました。');");
			out.print("history.back();");
			out.print("</script>");
			out.flush();
		}

		return af;
	}

}
