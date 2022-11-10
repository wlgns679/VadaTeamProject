package vada.dao.board.admin;

import java.util.List;

import vada.dto.UserDTO;
import vada.service.board.admin.ManagerService;

public interface ManagerDAO extends ManagerService {
	
	public abstract List<UserDTO> listBoard() throws Exception;
	
	public abstract int blackList(String userid, String blackyn) throws Exception;

}
