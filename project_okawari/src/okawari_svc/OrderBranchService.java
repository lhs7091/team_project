package okawari_svc;

import okawari_dao.OkawariDAO;
import okawari_model.OrderBranchDTO;

public class OrderBranchService {
	public boolean insertOrderBranch(OrderBranchDTO dto) {
		OkawariDAO dao = new OkawariDAO();
		int insertResult = dao.insertOrderBranch(dto);

		if (insertResult > 0) {
			return true;
		}
		return false;
	}

	public boolean approveOrderHead(String user_id, int order_num, String order_expired_date) {
		OkawariDAO dao = new OkawariDAO();
		int updateResult = dao.updateCompleteOrderHead(user_id, order_num, order_expired_date);
		if (updateResult > 0) {
			return true;
		}
		return false;
	}

	public boolean approveOrderBranch(String user_id, int order_num) {
		OkawariDAO dao = new OkawariDAO();
		int updateResult = dao.updateCompleteOrderBranch(user_id, order_num);
		if (updateResult > 0) {
			return true;
		}
		return false;

	}

	// 주문번호에 해당하는 주문 내역을 갖고온다.
	public OrderBranchDTO showOneOrder(int order_num) {
		OrderBranchDTO dto = new OrderBranchDTO();
		OkawariDAO dao = new OkawariDAO();
		dto = dao.selectOneOrderNumber(order_num);
		return dto;
	}

}
