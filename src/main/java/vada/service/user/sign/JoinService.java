package vada.service.user.sign;

import vada.dto.UserDTO;

public interface JoinService extends SignService {
	
	public abstract int join(UserDTO userDTO) throws Exception;

	public abstract boolean checkUserid(String userid) throws Exception;

	public abstract boolean checkNickname(String nickname) throws Exception;
}
