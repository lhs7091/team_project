package okawari_svc;

import java.util.List;

import okawari_dao.OkawariDAO;
import okawari_model.MenuDTO;

public class MenuListService {

	public List<MenuDTO> branchMenuListCustomersv(String user_id, String Check) {
		List<MenuDTO> menuList = null;
		OkawariDAO dao = new OkawariDAO();
		String branch_id = "";
		for (int i = 0; i < user_id.length() - 1; i++) {
			char ch = user_id.charAt(i);
			branch_id += ch;
		}
		// y는 현재 판매 가능중인 물건 check = y
		menuList = dao.branchMenuList(branch_id, Check);
		return menuList;
	}

	public List<MenuDTO> menuListCustomersv() {
		List<MenuDTO> menuList = null;
		OkawariDAO dao = new OkawariDAO();

		// y는 현재 판매 가능중인 물건 check = y
		menuList = dao.menuList("y");

		return menuList;
	}
}