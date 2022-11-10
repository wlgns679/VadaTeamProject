package vada.dao.user.sign;

import vada.dto.UserDTO;
import vada.service.user.sign.JoinService;

public interface JoinDAO extends JoinService {

	public abstract int join(UserDTO userDTO) throws Exception;

	public abstract boolean checkUserid(String userid) throws Exception;

	public abstract boolean checkNickname(String nickname) throws Exception;
}
