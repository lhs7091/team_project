package okawari_svc;

import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.StockHeadDTO;
import okawari_model.UserDTO;

public class StockHeadService {

	public ArrayList<StockHeadDTO> showStockHead(UserDTO dto) {
		ArrayList<StockHeadDTO> stockList = null;
		OkawariDAO dao = new OkawariDAO();

		boolean authCheck = dao.authorityCheck(dto.getUser_auth());

		if (authCheck == true) {
			stockList = dao.StockHeadList(dto.getUser_id());
		}
		return stockList;
	}

	public ArrayList<UserDTO> showAllUser(UserDTO dto) {
		ArrayList<UserDTO> userList = null;
		OkawariDAO dao = new OkawariDAO();

		userList = dao.showAllUser();

		return userList;
	}

	public String selectId(String id) {
		String selectId = null;
		OkawariDAO dao = new OkawariDAO();

		selectId = dao.selectStockId(id);

		return selectId;
	}

	public int checkTotalStock(int order_menu_num) {
		OkawariDAO dao = new OkawariDAO();
		int totalStock = dao.selectTotalStock(order_menu_num);
		return totalStock;
	}

	public ArrayList<StockHeadDTO> stockList(int order_menu_num) {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<StockHeadDTO> list = new ArrayList<StockHeadDTO>();
		list = dao.StockHeadList(order_menu_num);
		return list;
	}

	public void reduceStock(int stock_menu_num, int stock_count, String stock_expiredate) {
		OkawariDAO dao = new OkawariDAO();
		dao.updateStockCountHead(stock_menu_num, stock_count, stock_expiredate);
	}

	public void deleteStock(int stock_menu_num, String stock_expiredate) {
		OkawariDAO dao = new OkawariDAO();
		dao.deleteStockHead(stock_menu_num, stock_expiredate);

	}

	// 전체 재고 조회
	public ArrayList<StockHeadDTO> showStockHeadList() {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<StockHeadDTO> list = dao.selectAllStockHead();
		return list;
	}

	// 동일한 재고가 있을 경우 수량 증가시키기
	public boolean updateStock(StockHeadDTO stockHeadDTO) {
		OkawariDAO dao = new OkawariDAO();
		int result = dao.updateAddStockHead(stockHeadDTO);
		if (result > 0) {
			return true;
		}
		return false;

	}

	// 동일한 재고가 없을 경우 수량 추가시키기
	public boolean insertStock(StockHeadDTO stockHeadDTO) {
		OkawariDAO dao = new OkawariDAO();
		int result = dao.insertAddStockHead(stockHeadDTO);
		if (result > 0) {
			return true;
		}
		return false;
	}

}
