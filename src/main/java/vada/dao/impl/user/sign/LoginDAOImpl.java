package vada.dao.impl.user.sign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vada.constants.VADAConstants;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dao.user.sign.LoginDAO;
import vada.dto.UserDTO;

public class LoginDAOImpl extends BoardDAOImpl implements LoginDAO {

	@Override
	// 유저 아이디와 패스워드가 user테이블에 존재하는지 확인하기 위한 파라미터
	// 로그인을 하기 위한 메소드
	public UserDTO userLogin(String userid, String userpw) throws Exception {		
		
		Connection conn = getConnection();

		// select * from user where adminyn='no' and userid = ? and userpw = ?
		String sql = VADAConstants.props.getProperty("SELECT_USER_LOGIN_SQL");

		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userid);
		pstmt.setString(2, userpw);
		
		ResultSet rs = pstmt.executeQuery();
		
		UserDTO userDTO = null;
		while (rs.next()) {
			userDTO = new UserDTO();
			userDTO.setUserid(rs.getString("userid"));
			userDTO.setUserpw(rs.getString("userpw"));
			userDTO.setNickname(rs.getString("nickname"));
			userDTO.setAdminyn(rs.getString("adminyn"));
			userDTO.setBlackyn(rs.getString("blackyn"));
			userDTO.setCurrentip(rs.getString("currentip"));
		}

		closeConnection(rs, pstmt, conn);
		return userDTO;
	} // userLogin

	@Override
	// 관리자 아이디와 패스워드가 user테이블에 adminyn='yes' 상태로 존재하는지 확인하기 위한 파라미터
	// 관리자 로그인을 위한 메소드
	public UserDTO adminynLogin(String userid, String userpw) throws Exception {
		
		Connection conn = getConnection();

		// select * from user where adminyn='yes' and userid = ? and userpw = ?
		PreparedStatement pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_ADMIN_LOGIN_SQL"));
		
		pstmt.setString(1, userid);
		pstmt.setString(2, userpw);
		ResultSet rs = pstmt.executeQuery();

		UserDTO userDTO = null;
		while (rs.next()) {
			userDTO = new UserDTO();
			userDTO.setUserid(rs.getString("userid"));
			userDTO.setUserpw(rs.getString("userpw"));
			userDTO.setNickname(rs.getString("nickname"));
			userDTO.setAdminyn(rs.getString("adminyn"));
			userDTO.setBlackyn(rs.getString("blackyn"));

		}

		closeConnection(rs, pstmt, conn);
		return userDTO;

	} // adminynLogin
} // class