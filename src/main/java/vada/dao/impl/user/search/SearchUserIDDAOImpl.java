package vada.dao.impl.user.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vada.constants.VADAConstants;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dao.user.search.SearchUserIDDAO;

public class SearchUserIDDAOImpl extends BoardDAOImpl implements SearchUserIDDAO {

	// 아이디 찾기 위한 메소드로써 이름과 이메일로 찾기 위한 파라미터
	@Override
	public String searchUserID(String name, String email) throws Exception {
		
		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// select * from user where name=? and email=?
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_SEARCH_USERID_SQL"));

		pstmt.setString(1, name);
		pstmt.setString(2, email);

		rs = pstmt.executeQuery();
		String dbuserid = null;

		if (rs.next()) {
			dbuserid = rs.getString("userid");
		}

		closeConnection(rs, pstmt, conn);
		
		return dbuserid;

	} // searchUserID
	
} // class
