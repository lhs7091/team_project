package okawari_svc;

import okawari_dao.OkawariDAO;

public class FindMenuPriceService {

	public int selectMenuPrice(int order_menu_num) {

		OkawariDAO dao = new OkawariDAO();
		int menuPrice = dao.selctMenuPrice(order_menu_num);

		return menuPrice;
	}
}
