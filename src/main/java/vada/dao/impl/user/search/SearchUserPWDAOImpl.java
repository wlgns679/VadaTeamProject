package vada.dao.impl.user.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vada.constants.VADAConstants;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dao.user.search.SearchUserPWDAO;

public class SearchUserPWDAOImpl extends BoardDAOImpl implements SearchUserPWDAO {

	// 비밀번호 찾기 위한 메소드로써 아이디와 이메일로 찾기 위한 파리미터
	@Override
	public String searchUserPW(String userid, String email) throws Exception {

		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// select * from user where userid=? and email=?
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_SEARCH_USERPW_SQL"));

		pstmt.setString(1, userid);
		pstmt.setString(2, email);

		rs = pstmt.executeQuery();
		String dbuserpw = null;

		if (rs.next()) {
			dbuserpw = rs.getString("userpw");
		}

		closeConnection(rs, pstmt, conn);
		
		return dbuserpw;

	} // searchUserPW
	
} // class
