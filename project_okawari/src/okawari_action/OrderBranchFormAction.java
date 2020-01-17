package okawari_action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_model.MenuDTO;
import okawari_svc.MenuListService;

public class OrderBranchFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 메뉴 리스트를 저장할 arraylist가 필요해요..
		List<MenuDTO> list = null;
		ActionForward af = null;
		// 그 객체정보 안에 이름과 메뉴명을 자동으로 선택이 되도록 해야해요..
		MenuListService svc = new MenuListService();
		list = svc.menuListCustomersv();

		// 금액정보도 같이 넘겨야 되요..
		// 이때 금액은 본사가 각 지점으로 판매할 금액이에요..
		// // okwr_stock_head 테이블에 판매가를 가져와야 되요..
		// FindMenuPriceService fmps = new FindMenuPriceService();
		// fmps.selectStockHeadPrice();

		// 메뉴리스트와 금액정보를 받았으면, jsp 페이지로 넘겨야 되요..
		request.setAttribute("menuList", list);
		// 최종 띄울 페이지는 orderBranch.jsp에요..

		return new ActionForward("orderBranchForm.jsp", false);
	}

}
