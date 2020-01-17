package okawari_svc;

import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.DeliveryDTO;

public class DeliveryService {

	public void addDeliveryTable(DeliveryDTO deliveryDTO) {
		OkawariDAO dao = new OkawariDAO();
		dao.insertDelivery(deliveryDTO);

	}

	public ArrayList<DeliveryDTO> showDeliveryList(int order_num) {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<DeliveryDTO> list = dao.selectDeliveryList(order_num);
		return list;
	}

	public void removeDeliveryList(int order_num) {
		OkawariDAO dao = new OkawariDAO();
		dao.deleteDeliveryList(order_num);
	}

}
