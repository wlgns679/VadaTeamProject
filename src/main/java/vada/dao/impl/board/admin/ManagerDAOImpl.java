package vada.dao.impl.board.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vada.constants.VADAConstants;
import vada.dao.board.admin.ManagerDAO;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dto.UserDTO;

public class ManagerDAOImpl extends BoardDAOImpl implements ManagerDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	// 관리자 회원이 아닌 일반 회원정보들을 리스트를 얻기 위한 메소드
	@Override
	public List<UserDTO> listBoard() throws Exception {

		// select * from user where adminyn='no' order by joindate desc
		String prependSQL = VADAConstants.props.getProperty("SELECT_MANAGER_SEARCH_SQL");

		conn = getConnection();
		pstmt = conn.prepareStatement(prependSQL);
		ResultSet rs = pstmt.executeQuery();

		List<UserDTO> list = new ArrayList<UserDTO>();
		while (rs.next()) {
			UserDTO boardDTO = new UserDTO();
			boardDTO.setUserid(rs.getString("userid"));
			boardDTO.setJoindate(rs.getTimestamp("joindate"));
			boardDTO.setBlackyn(rs.getString("blackyn"));

			list.add(boardDTO);
		}

		closeConnection(rs, pstmt, conn);

		return list;
	}

	// userid를 블랙리스트에 추가하기 위한 파라미터 및 메소드
	@Override
	public int blackList(String userid, String blackyn) throws Exception {

		conn = getConnection();
		UserDTO boardDTO = new UserDTO();

		// update `user` set blackyn=? where userid =?
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("UPDATE_MANAGER_BLACK_CHANGE"));

		pstmt.setString(1, blackyn);
		pstmt.setString(2, userid);

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, conn);

		return result;
	}

}