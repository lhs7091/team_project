package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_model.UserDTO;
import okawari_svc.UserService;

public class UserModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		
		String user_id = request.getParameter("user_id");
		String user_pass = request.getParameter("user_pass");
		int user_auth = Integer.parseInt(request.getParameter("user_auth"));
		String user_name = request.getParameter("user_name");
		String user_manager = request.getParameter("user_manager");
		String user_addr = request.getParameter("user_addr");
		String user_phone = request.getParameter("user_phone");
		String user_businum = request.getParameter("user_businum");
		int user_commission = Integer.parseInt(request.getParameter("user_commission"));
		String user_opendate = request.getParameter("user_opendate");
		
		// dto 객체로 만들고
		UserDTO dto = new UserDTO(user_id, user_pass, user_auth, user_name, 
				user_manager, user_addr, user_phone, user_businum, user_commission , user_opendate);
		
		UserService us = new UserService();
		boolean result = us.modifyUser(dto);
		
		if(result) {
			af = new ActionForward("userList.okawari", false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원수정에 실패하였습니다.支店情報修正に失敗しました。');");
			out.print("history.back();");
			out.print("</script>");
			out.flush();
		}
		
		return af;
	}

}
