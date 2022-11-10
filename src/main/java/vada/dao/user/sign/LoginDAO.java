package vada.dao.user.sign;

import vada.dto.UserDTO;
import vada.service.user.sign.LoginService;

public interface LoginDAO extends LoginService {

	public abstract UserDTO userLogin(String userid, String userpw) throws Exception;

	public abstract UserDTO adminynLogin(String userid, String userpw) throws Exception;

}
