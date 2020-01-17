package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_model.StockHeadDTO;
import okawari_svc.StockHeadService;

public class AddStockHead implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ActionForward af = null;
		// 본사 재고를 추가하여 봅시다.

		// 먼저 script에서 추가할 품목의 품번과, 수량, 유통기한을 입력받습니다.
		// 원가도 입력받고, 각 지사에 판매할 금액도 함께 입력을 받습니다.
		String stock_head_id = "headoffice";
		int stock_menu_num = Integer.parseInt(request.getParameter("stock_menu_num"));
		int stock_count = Integer.parseInt(request.getParameter("stock_count"));
		int stock_cost = Integer.parseInt(request.getParameter("stock_cost"));
		int stock_price = Integer.parseInt(request.getParameter("stock_price"));
		String stock_expiredate = request.getParameter("stock_expiredate");
		String stock_junk = "n";

		ArrayList<StockHeadDTO> list = new ArrayList<StockHeadDTO>();

		// 그럼 내용을 DB 저장해야합니다.
		// 이때 만약 동일한 유통기한과, 메뉴번호가 있으면 그곳에 +를 해주어야 하고,
		// 만약 없으면 새로 추가를 해주어야 해요..
		// 그래서
		// 1. 일단 DB에 저장된 목록을 갖고 온다..
		StockHeadService shs = new StockHeadService();
		list = shs.showStockHeadList();

		// 2. 갖고온 리스트에서 메뉴번호하고, 유통기한이 일치하는 항목이 있는지 확인을 한다.
		boolean result = false;
		boolean insertResult = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStock_menu_num() == stock_menu_num
					&& list.get(i).getStock_expiredate().equals(stock_expiredate)) {
				result = true;
				break;
			}
		}

		if (result) {
			// 리스트의 메뉴번호와 유통기한이 일치하네요..
			// 그럼 수량을 증가시켜주어야겠죵..update로 갑니다.
			insertResult = shs.updateStock(new StockHeadDTO(stock_head_id, stock_menu_num, stock_count, stock_cost,
					stock_price, stock_expiredate, stock_junk));
		} else {
			// 리스트의 메뉴번호와 유통기한이 일치하지 않네요..
			// 그럼 해당 수량을 insert 해주면 끝납니다.
			insertResult = shs.insertStock(new StockHeadDTO(stock_head_id, stock_menu_num, stock_count, stock_cost,
					stock_price, stock_expiredate, stock_junk));
		}

		if (insertResult) {
			af = new ActionForward("addStockHeadForm.okawari", false);
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('注文ができていません。');");
			out.print("history.back();");
			out.print("</script>");
			out.flush();
		}

		return af;
	}

}
