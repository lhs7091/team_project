package okawari_svc;

import okawari_dao.OkawariDAO;
import okawari_model.MenuDTO;

public class AddMenuService {

	public boolean addMenu(MenuDTO dto) {
		OkawariDAO dao = new OkawariDAO();
		int insertResult = dao.insertNewMenu(dto);
		if (insertResult > 0) {
			return true;
		}
		return false;
	}

}