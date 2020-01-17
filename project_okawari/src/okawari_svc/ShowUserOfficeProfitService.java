package okawari_svc;

import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.SalesBranchDTO;

public class ShowUserOfficeProfitService {

	public ArrayList<SalesBranchDTO> selectUserProfit(String user_id, String startDay, String lastDay) {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<SalesBranchDTO> list = dao.selectUserProfitList(user_id, startDay, lastDay);

		return list;
	}

}