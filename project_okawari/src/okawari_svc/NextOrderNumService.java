package okawari_svc;

import okawari_dao.OkawariDAO;

public class NextOrderNumService {

	public int selectLastOrderNum() {
		OkawariDAO dao = new OkawariDAO();
		int lastOrderNum = dao.selectLastOrderNum();

		return lastOrderNum;
	}

}
