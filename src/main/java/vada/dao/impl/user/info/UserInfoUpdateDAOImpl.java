package vada.dao.impl.user.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vada.constants.VADAConstants;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dao.user.info.UserInfoUpdateDAO;
import vada.dto.UserDTO;

public class UserInfoUpdateDAOImpl extends BoardDAOImpl implements UserInfoUpdateDAO {

	// 회원정보 수정을 위해 해당 세션ID에 해당하는 회원정보를 얻기 위한 파라미터 및 메소드
	@Override
	public UserDTO UserInfoSelect(String userid) throws SQLException {

		UserDTO userDTO = new UserDTO();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet result = null;

		// select userid, userpw, address, name, tel, email, nickname, detailaddress from `user` where userid=?
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_USERINFO_UPDATE_SQL"));
		pstmt.setString(1, userid);

		result = pstmt.executeQuery();

		if (result.next()) {
			userDTO.setUserid(result.getString("userid"));
			userDTO.setUserpw(result.getString("userpw"));
			userDTO.setAddress(result.getString("address"));
			userDTO.setName(result.getString("name"));
			userDTO.setTel(result.getString("tel"));
			userDTO.setEmail(result.getString("email"));
			userDTO.setNickname(result.getString("nickname"));
			userDTO.setDetailaddress(result.getString("detailaddress"));
//			userDTO.setInterestcategorynum(result.getInt("interestcategorynum"));		//TODO 카테고리 추가해야함
		}

		closeConnection(result, pstmt, conn);

		return userDTO;
		
	} // UserInfoSelect

	// 회원정보를 수정할 유저데이터(userDTO)와 해당 userid를 받아와서 회원정보 업데이트 하기 위한 파라미터 및 메소드
	@Override
	public UserDTO UserInfoUpdate(String userid, UserDTO userDTO) throws SQLException {

		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		int result = 0;

		// update user set userpw=?, address=?, name=?, tel=?, email=?, nickname=?,
		// detailaddress=? where userid=?
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("UPDATE_USERINFO_UADATE_SQL"));
		pstmt.setString(1, userDTO.getUserpw());
		pstmt.setString(2, userDTO.getAddress());
		pstmt.setString(3, userDTO.getName());
		pstmt.setString(4, userDTO.getTel());
		pstmt.setString(5, userDTO.getEmail());
		pstmt.setString(6, userDTO.getNickname());
		pstmt.setString(7, userDTO.getDetailaddress());
		pstmt.setString(8, userid);

		result = pstmt.executeUpdate();
		
		closeConnection(pstmt, conn);
		
		return userDTO;

	} // UserInfoUpdate
	
} // class
