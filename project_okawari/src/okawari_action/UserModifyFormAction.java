package okawari_action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_model.UserDTO;
import okawari_svc.UserService;

public class UserModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 조회할 user_id를 갖고옵니다.
		String user_id = request.getParameter("user_id");

		// 해당 id의 user 정보를 불러옵니다.
		UserService us = new UserService();
		UserDTO dto = us.showOneUser(user_id);

		request.setAttribute("dto", dto);
		return new ActionForward("userModifyForm.jsp", false);
	}

}
