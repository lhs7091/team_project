package okawari_action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okawari_dao.GetDateTime;
import okawari_model.ActionForward;
import okawari_model.DeliveryDTO;
import okawari_model.OrderBranchDTO;
import okawari_model.SalesHeadDTO;
import okawari_model.StockBranchDTO;
import okawari_model.StockHeadDTO;
import okawari_model.UserDTO;
import okawari_svc.DeliveryService;
import okawari_svc.FindMenuPriceService;
import okawari_svc.OrderBranchService;
import okawari_svc.SalesHeadService;
import okawari_svc.StockBranchService;
import okawari_svc.StockHeadService;

public class OrderApproveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 본사와 각 지점은 발주를 완료하거나 수취를 하면 승인을 눌러줍니다.
		// 이때 각 해당 부분을 n -> y로 바꾸어 주어야 해요..
		// 해당 id는 session이 유지되어 있으니, 세션으로 부터 받아옵니다.
		HttpSession session = request.getSession();
		UserDTO dto = (UserDTO) session.getAttribute("session");

		// jsp로 부터 넘어온 값을 받구요..

		int order_num = 0;
		int order_menu_num = 0;
		int order_count = 0;
		try {
			order_num = Integer.parseInt(request.getParameter("order_num"));
			order_menu_num = Integer.parseInt(request.getParameter("order_menu_num"));
			order_count = Integer.parseInt(request.getParameter("order_count"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String order_branch_id = (String) request.getParameter("order_branch_id");

		// 판매가격 갖고오기
		FindMenuPriceService fmps = new FindMenuPriceService();
		int menu_price = fmps.selectMenuPrice(order_menu_num);

		// 서비스로 접근하기 위해 객체를 생성해요..
		OrderBranchService svc = new OrderBranchService();

		// 그래서 각 유저 권한에 따라 업데이트를 해주어요..
		// boolean result = false;
		OrderBranchDTO obdto = null;
		ArrayList<StockHeadDTO> stockHeadlist = new ArrayList<StockHeadDTO>(); // 본사재고리스트를 저장할 list

		if (dto.getUser_auth() == 1) { // 본사일 경우
			// 1. 재고가 있는지 확인.. 부족하면 에러 메시지를 띄워주고 다시 뒤로 간다..
			StockHeadService shsvc = new StockHeadService();
			int totalStock = shsvc.checkTotalStock(order_menu_num);
			if (totalStock < order_count) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('재고가 부족합니다.在庫が足りません。');");
				out.print("history.back();");
				out.print("</script>");
				out.flush();
			}

			// 2. 유통기한이 빠른것부터 소진.
			stockHeadlist = shsvc.stockList(order_menu_num);
			for (int i = 0; i < stockHeadlist.size(); i++) {
				if (stockHeadlist.get(i).getStock_count() - order_count > 0) {
					// 수량이 충분하니까... db에 수량만 감소시키고 빠져나온다.
					shsvc.reduceStock(stockHeadlist.get(i).getStock_menu_num(),
							stockHeadlist.get(i).getStock_count() - order_count,
							stockHeadlist.get(i).getStock_expiredate());

					// 임시 delivery table에 저장합니다.
					DeliveryService ds = new DeliveryService();
					ds.addDeliveryTable(new DeliveryDTO(order_num, order_branch_id, order_menu_num, order_count,
							stockHeadlist.get(i).getStock_price(), menu_price,
							stockHeadlist.get(i).getStock_expiredate()));

					break;

				} else if (stockHeadlist.get(i).getStock_count() - order_count == 0) {
					// 재고란에 삭제하고.. 빠져나온다.
					shsvc.deleteStock(stockHeadlist.get(i).getStock_menu_num(),
							stockHeadlist.get(i).getStock_expiredate());

					// 임시 delivery table에 저장합니다.
					DeliveryService ds = new DeliveryService();
					ds.addDeliveryTable(new DeliveryDTO(order_num, order_branch_id, order_menu_num, order_count,
							stockHeadlist.get(i).getStock_price(), menu_price,
							stockHeadlist.get(i).getStock_expiredate()));

					break;

				} else {
					// 재고란에 삭제하고.. 위로 다시 반복한다.
					shsvc.deleteStock(stockHeadlist.get(i).getStock_menu_num(),
							stockHeadlist.get(i).getStock_expiredate());

					// 임시 delivery table에 저장합니다.
					DeliveryService ds = new DeliveryService();
					ds.addDeliveryTable(new DeliveryDTO(order_num, order_branch_id, order_menu_num,
							stockHeadlist.get(i).getStock_count(), stockHeadlist.get(i).getStock_price(), menu_price,
							stockHeadlist.get(i).getStock_expiredate()));

					order_count -= stockHeadlist.get(i).getStock_count();
				}
			}

			svc.approveOrderHead(dto.getUser_id(), order_num, stockHeadlist.get(0).getStock_expiredate());
			// delivery table 로 이동했으니,, 매출부분에 올려줍니다.
			// 먼저 order_num에 해당하는 내용을 모두 갖고옵니다.
			DeliveryService ds = new DeliveryService();
			ArrayList<DeliveryDTO> list = ds.showDeliveryList(order_num);

			// 매출 테이블에 삽입합니다.
			// 해당 날짜로 매출 데이터를 초기화 시킵니다.
			SalesHeadService shs = new SalesHeadService();
			GetDateTime getDateTime = new GetDateTime();
			String date = getDateTime.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
			Date sqlDate = null;
			try {
				sqlDate = new Date(sdf.parse(date).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 먼저 지점이 동일한 날짜에 매출이 있을경우 update만 실행하고, 없는경우에 먼저 0으로 만들어 준다.
			boolean initCheck = shs.checkSalesHead(order_branch_id, sqlDate);
			if (initCheck) {
				shs.addSalesHead(order_branch_id, date);
			}
			// delivery테이블에 있는 내용을 update 해준다.
			SalesHeadDTO shd = null;
			for (int i = 0; i < list.size(); i++) {
				shd = new SalesHeadDTO();
				shd.setSales_branch_id(order_branch_id);
				shd.setSales_date(sqlDate);
				shd.setSales_total(list.get(i).getStock_price() * list.get(i).getStock_count());
				shd.setSales_cost(list.get(i).getStock_cost() * list.get(i).getStock_count());
				shs.updateSalesHead(shd);
			}

		} else if (dto.getUser_auth() == 2) { // 각 지점일 경우
			// 입고여부 승인처리
			svc.approveOrderBranch(dto.getUser_id(), order_num);

			// delivery table에 있는 해당 주문번호 list를 갖고온다.
			DeliveryService ds = new DeliveryService();
			ArrayList<DeliveryDTO> list = ds.showDeliveryList(order_num);

			// 지점의 재고테이블로 추가해준다.
			StockBranchService sbsvc = new StockBranchService();

			// 만약 메뉴번호하고, 유통기한하고 같으면 동일한 곳에 추가해준다.
			for (int i = 0; i < list.size(); i++) {

				boolean check = sbsvc.addStockBranch(list.get(i));
				if (check) { // 동일한게 없어서.. insert 해주어야 함.
					sbsvc.insertStockBranch(list.get(i));
				}
			}

			// delibery table에 있는 해당 주문번호는 삭제한다.
			ds.removeDeliveryList(order_num);

		}

		// 다 끝났으면 다시 리스트로 돌아갑니다...
		return new ActionForward("orderBranchList.okawari", false);
	}

}
