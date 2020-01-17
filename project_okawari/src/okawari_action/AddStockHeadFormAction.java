package okawari_action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okawari_model.ActionForward;
import okawari_model.MenuDTO;
import okawari_model.StockHeadDTO;
import okawari_svc.MenuListService;
import okawari_svc.StockHeadService;

public class AddStockHeadFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 메뉴 리스트를 저장할 arraylist가 필요해요..
		List<MenuDTO> menulist = null;
		List<StockHeadDTO> stocklist = null;
		ActionForward af = null;
		// 그 객체정보 안에 이름과 메뉴명을 자동으로 선택이 되도록 해야해요..
		MenuListService svc = new MenuListService();
		menulist = svc.menuListCustomersv();

		// 금액정보도 같이 넘겨야 되요..
		// 이때 금액은 본사가 각 지점으로 판매할 금액이에요..
		// okwr_stock_head 테이블에 판매가를 가져와야 되요..

		// 메뉴리스트와 금액정보를 받았으면, jsp 페이지로 넘겨야 되요..
		request.setAttribute("menuList", menulist);

		// 현재 보유중이 재고리스트를 갖고옵니다.
		StockHeadService shs = new StockHeadService();
		stocklist = shs.showStockHeadList();

		// 보유중인 재고 리스트를 jsp로 넘겨욥
		request.setAttribute("stockList", stocklist);

		// 최종 띄울 페이지는 orderBranch.jsp에요..

		return new ActionForward("addStockHeadForm.jsp", false);
	}

}
