package vada.dao.user.info;

import java.sql.SQLException;

import vada.dto.UserDTO;
import vada.service.user.info.UserInfoUpdateservice;

public interface UserInfoUpdateDAO extends UserInfoUpdateservice {

	public abstract UserDTO UserInfoSelect(String userid) throws Exception;

	public abstract UserDTO UserInfoUpdate(String userid, UserDTO userDTO) throws SQLException;

}
