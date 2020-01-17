package okawari_svc;

import java.util.ArrayList;

import okawari_dao.OkawariDAO;
import okawari_model.UserDTO;

public class UserService {

	public boolean registerBranch(UserDTO dto) {
		OkawariDAO dao = new OkawariDAO();
		int insertResult = dao.insertBranch(dto);
		if (insertResult > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<UserDTO> showUserList() {
		OkawariDAO dao = new OkawariDAO();
		ArrayList<UserDTO> userList = dao.selectAllUser();
		return userList;
	}

	public UserDTO showOneUser(String user_id) {
		OkawariDAO dao = new OkawariDAO();
		UserDTO dto = dao.selectOneUser(user_id);
		return dto;
	}

	public boolean modifyUser(UserDTO dto) {
		OkawariDAO dao = new OkawariDAO();
		int updateResult = dao.updateOneUser(dto);
		if (updateResult > 0) {
			return true;
		}
		return false;
	}

	public boolean removeUser(String user_id) {
		OkawariDAO dao = new OkawariDAO();
		int removeResult = dao.removeUser(user_id);
		if (removeResult > 0) {
			return true;
		}
		return false;
	}

}
