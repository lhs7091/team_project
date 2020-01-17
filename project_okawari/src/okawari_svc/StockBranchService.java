package okawari_svc;

import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.DeliveryDTO;
import okawari_model.StockBranchDTO;

public class StockBranchService {
	public ArrayList<StockBranchDTO> ShowStockBranch(String stock_branch_id) {
		ArrayList<StockBranchDTO> stockList = null;
		OkawariDAO dao = new OkawariDAO();

		stockList = dao.StockBranchList(stock_branch_id);

		return stockList;
	}

	// 본사로 부터 받은 물품을 지사로 저장하기 위해 dao로 보냅니다.
	public boolean addStockBranch(DeliveryDTO deliveryDTO) {
		OkawariDAO dao = new OkawariDAO();
		int updateResult = dao.updateStockBranch(deliveryDTO);
		if (updateResult > 0) {
			return false; // 업데이트 안된 상태.. 동일한게 없으니까..
		}
		return true;
	}

	public void insertStockBranch(DeliveryDTO deliveryDTO) {
		OkawariDAO dao = new OkawariDAO();
		dao.insertStockBranch(deliveryDTO);

	}

}
