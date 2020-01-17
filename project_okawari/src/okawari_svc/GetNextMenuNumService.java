package okawari_svc;

import okawari_dao.OkawariDAO;

public class GetNextMenuNumService {

	public int getNextMenuNumService() {
		OkawariDAO dao = new OkawariDAO();
		int result = dao.getNextMenuNum();
		return result;
	}

}