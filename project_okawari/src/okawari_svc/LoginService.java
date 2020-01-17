package okawari_svc;

import okawari_dao.OkawariDAO;
import okawari_model.UserDTO;

public class LoginService {

	public UserDTO loginUser(String user_id, String user_pass) {

		OkawariDAO dao = new OkawariDAO();
		UserDTO dto = dao.login(user_id, user_pass);
		return dto;
	}

}
