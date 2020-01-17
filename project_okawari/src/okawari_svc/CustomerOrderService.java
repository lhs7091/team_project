package okawari_svc;

import java.util.ArrayList;
import java.util.List;

import okawari_dao.OkawariDAO;
import okawari_model.MenuDTO;
import okawari_model.OrderDTO;
import okawari_model.StockBranchDTO;

public class CustomerOrderService {
	static private OkawariDAO dao = new OkawariDAO();

	public List<MenuDTO> menuListCustomer(String user_id) {
		List<MenuDTO> menuList = null;
		// y는 현재 판매 가능중인 물건 check = y
		menuList = dao.menuList("y");

		return menuList;
	}

	public int insertMenuOrderCustomer(List<OrderDTO> customerOrderList) {
		int orderNumberCall = -1;

		orderNumberCall = dao.insertOrderCustomer(customerOrderList);

		return orderNumberCall;
	}

	public boolean deleteCustomerOrderMenu(String user_id, String day, int orderNumber) {
		boolean check = false;

		String day6 = day.substring(2).replace("-", "/");
		check = dao.cancleOrderCustomer(user_id, day6, orderNumber);

		return check;
	}

	public ArrayList<MenuDTO> stockCheck(String stock_branch_id, List<OrderDTO> customerOrderList) {
		ArrayList<MenuDTO> stockList = new ArrayList<MenuDTO>();
		List<StockBranchDTO> stockCheckList = null;
		stockCheckList = dao.StockBranchList(stock_branch_id); // 잘 작동

		for (int i = 0; i < customerOrderList.size(); i++) {
			int stockSum = 0;
			MenuDTO dto = new MenuDTO();
			for (int j = 0; j < stockCheckList.size(); j++) {
				// 먼저 주문한 메뉴이름과 지점에 있는 내용이 같을 경우 stockSum 에 재고를 합산한다.
				if (customerOrderList.get(i).getOrder_menu_num() == stockCheckList.get(j).getStock_menu_num()) {
					stockSum += stockCheckList.get(j).getStock_count();
				}
			} // for j

			if (customerOrderList.get(i).getOrder_count() > stockSum) {
				// -몇개 재고 부족 메시지 출력시
				dto.setMenu_name(customerOrderList.get(i).getOrder_menu_name());
				dto.setMenu_num(customerOrderList.get(i).getOrder_num());
				dto.setMenu_count(stockSum);

				stockList.add(dto);

			}

			if (stockList.size() > 0 && customerOrderList.get(i).getOrder_count() <= stockSum) {
				// 이후 총괄 합산시에도 날짜별로 따로따로 계산되므로 잔여물이 남을 수 있기에 지워주는 로직
				try {
					stockList.remove(i);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} // for i

		return stockList;
	}

	public List<OrderDTO> oriOrderCheckInsert(String stock_branch_id, List<OrderDTO> customerOrderList) {
		List<OrderDTO> oriList = customerOrderList;
		List<StockBranchDTO> stockCheckList = null;
		stockCheckList = dao.StockBranchList(stock_branch_id); // 잘 작동

		for (int i = 0; i < oriList.size(); i++) {
			int stockSum = 0;
			int customerQty = oriList.get(i).getOrder_count();
			int oriMoney = 0;
			for (int j = 0; j < stockCheckList.size(); j++) {
				if (stockCheckList.get(j).getStock_menu_num() == oriList.get(i).getOrder_menu_num()
						&& stockCheckList.get(j).getStock_count() > 0) {
					if (stockCheckList.get(j).getStock_count() >= customerQty) {
						oriMoney = stockCheckList.get(j).getStock_cost() * customerQty;
						oriList.get(i).setOrder_oriprice(oriList.get(i).getOrder_oriprice() + oriMoney);
						System.out.println(stockCheckList.get(j).getStock_cost());
						break;
					} else if (stockCheckList.get(j).getStock_count() < customerQty) {
						customerQty = customerQty - stockCheckList.get(j).getStock_count();
						oriMoney = stockCheckList.get(j).getStock_cost() * stockCheckList.get(j).getStock_count();
						oriList.get(i).setOrder_oriprice(oriList.get(i).getOrder_oriprice() + oriMoney);
					}
					if (customerQty == 0) {
						break;
					}

				} // for j
			}

		}
		return oriList;
	}

	public boolean decStock(String stock_branch_id, List<OrderDTO> customerOrderList) {
		boolean decCheck = false;
		ArrayList<MenuDTO> stockList = new ArrayList<MenuDTO>();
		List<StockBranchDTO> stockCheckList = null;
		stockCheckList = dao.StockBranchList(stock_branch_id); // 잘 작동

		for (int i = 0; i < customerOrderList.size(); i++) {
			int qty = customerOrderList.get(i).getOrder_count();

			for (int j = 0; j < stockCheckList.size(); j++) {

				// stock에 담긴 제품이 갯수가 0개가 아니고 메뉴 번호가 같으면 실행
				if (stockCheckList.get(j).getStock_count() != 0
						&& customerOrderList.get(i).getOrder_menu_num() == stockCheckList.get(j).getStock_menu_num()) {

					// customerOrderList qty 제품의 갯수보다 높거나, 같으면 실행
					if (qty <= stockCheckList.get(j).getStock_count()) { // 나 15 < 지점 20
						// scl(20) - qty(15) = 그 값을 scl.set 카운트값 갱신
						stockCheckList.get(j).setStock_count(stockCheckList.get(j).getStock_count() - qty);
						qty = 0;
						continue;
						// qty가 scl보다 낮으면
					} else if (qty == 0) {
						continue;
					} else { // 나 15 > 지점 5
						qty = qty - stockCheckList.get(j).getStock_count();
						// 뺴준 값이 전부이기에 0으로 값 갱신
						stockCheckList.get(j).setStock_count(0);
					}

				}
			} // for j
		} // for i

		decCheck = dao.decOrderStockBranch(stock_branch_id, stockCheckList);

		return decCheck;
	}

	public ArrayList<OrderDTO> customerOrderSelect(String user_id, String day, int orderNumber) {
		// 지점 매출 정보 추려오기
		OrderDTO dto = null;
		ArrayList<OrderDTO> list = null;

		String day6 = day.substring(2).replace("-", "/");
		list = dao.findCustomerOrder(user_id, day6, orderNumber);

		return list;
	}

	public OrderDTO submitCustomerOrder(List<OrderDTO> customerOrderList) {
		OrderDTO dto = new OrderDTO();
		for (int i = 0; i < customerOrderList.size(); i++) {
			dto.setOrder_date(customerOrderList.get(i).getOrder_date());
			dto.setOrder_time(customerOrderList.get(i).getOrder_time());
			// 갯수 * 원가 + 이전 내역
			dto.setOrder_oriprice(dto.getOrder_oriprice()
					+ (customerOrderList.get(i).getOrder_count() * customerOrderList.get(i).getOrder_oriprice()));
			dto.setOrder_totprice(dto.getOrder_totprice() + customerOrderList.get(i).getOrder_totprice());
			dto.setOrder_count(dto.getOrder_count() + customerOrderList.get(i).getOrder_count());
			dto.setOrder_branch_id(customerOrderList.get(i).getOrder_branch_id());
		}

		return dto;
	}

}