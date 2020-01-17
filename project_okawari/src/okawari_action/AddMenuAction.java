package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import okawari_model.ActionForward;
import okawari_model.MenuDTO;
import okawari_svc.AddMenuService;
import okawari_svc.GetNextMenuNumService;

public class AddMenuAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 메뉴를 추가할 수 있도록 해봅시다...
		// step1. 요청 파라미터 조회
		String realFolder = ""; // 파일이 업로드 될 경우 서버상의 물리적인 경로
		String saveFolder = "/images";
		ServletContext ctx = request.getServletContext();
		realFolder = ctx.getRealPath(saveFolder);
		int maxSize = 5 * 1024 * 1024; // 5MB, 한번에 업로드할 수 있는 파일의 크기
		String encType = "utf-8";

		// step2. 비즈니스 로직
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType,
				new DefaultFileRenamePolicy());

		String image = multi.getFilesystemName(URLEncoder.encode("menu_image", "utf-8")); // 파일명이 한글이 혼용될 경우 encoding
																							// 시키기
		GetNextMenuNumService num = new GetNextMenuNumService();
		int menu_num = num.getNextMenuNumService() + 1;
		MenuDTO dto = new MenuDTO(menu_num, multi.getParameter("menu_name"),
				Integer.parseInt(multi.getParameter("menu_price")), image, "y");

		AddMenuService service = new AddMenuService();
		boolean registerSuccess = service.addMenu(dto);

		// step3. 응답처리
		ActionForward af = null;

		if (registerSuccess) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('新しいメニューの登録成功しました。');");
			out.print("</script>");
			af = new ActionForward("mainPage.jsp", false);
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('メニュー登録に失敗しました。');");
			out.print("history.back();");
			out.print("</script>");
		}

		return af;

	}

}