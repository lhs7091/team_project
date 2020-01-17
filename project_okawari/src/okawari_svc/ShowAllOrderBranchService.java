package okawari_svc;

import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.OrderBranchDTO;

public class ShowAllOrderBranchService {

	public ArrayList<OrderBranchDTO> showAllOrderBranch() {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<OrderBranchDTO> list = dao.selectOrderBranchList();
		return list;
	}

	public ArrayList<OrderBranchDTO> showEachOrderBranch(String user_id) {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<OrderBranchDTO> list = dao.selectEachOrderBranchList(user_id);
		return list;
	}

}
