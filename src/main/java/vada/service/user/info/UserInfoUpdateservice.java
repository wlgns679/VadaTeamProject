package vada.service.user.info;

import java.sql.SQLException;

import vada.dto.UserDTO;
import vada.service.board.BoardService;

public interface UserInfoUpdateservice extends BoardService {
	
	public abstract UserDTO UserInfoSelect(String userid) throws Exception ;

	public abstract UserDTO UserInfoUpdate(String userid, UserDTO userDTO) throws SQLException;
}
