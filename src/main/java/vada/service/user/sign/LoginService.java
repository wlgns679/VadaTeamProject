package vada.service.user.sign;

import vada.dto.UserDTO;

public interface LoginService extends SignService {

	public abstract UserDTO userLogin(String userid, String userpw) throws Exception;

	public abstract UserDTO adminynLogin(String userid, String userpw) throws Exception;
}