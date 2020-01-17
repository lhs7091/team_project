package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_model.UserDTO;
import okawari_svc.UserService;

public class RegisterBranchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;

		// jsp에 내용을 받아옵니다.
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

		// 받은 내용을 담을 객체를 생성합니다.
		UserDTO dto = new UserDTO(user_id, user_pass, user_auth, user_name, user_manager, user_addr, user_phone,
				user_businum, user_commission, user_opendate);
		// 지점에서 사용할 고객전용 아이디 생성
		UserDTO dtoc = new UserDTO(String.valueOf(user_id + "c"), String.valueOf(user_pass + "c"), user_auth, user_name,
				user_manager, user_addr, user_phone, user_businum, user_commission, user_opendate);
		// 위에 생성한 객체로 user테이블에 추가합니다.
		UserService us = new UserService();
		boolean result = us.registerBranch(dto);
		boolean resultc = us.registerBranch(dtoc);
		// 만약 참이면... 지점 리스트를 보도록 이동합니다.
		if (result && resultc) {
			af = new ActionForward("userList.okawari", false);
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원등록이 완료되지 않았습니다.<br> 다시 등록바랍니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.flush();
		}

		return af;
	}

}