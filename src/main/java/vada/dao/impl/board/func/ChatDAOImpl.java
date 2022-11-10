package vada.dao.impl.board.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vada.constants.VADAConstants;
import vada.dao.board.func.ChatDAO;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dto.KtuserchatroomDTO;

public class ChatDAOImpl extends BoardDAOImpl implements ChatDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;

	@Override
	public int ktchatBoard(int productnum, KtuserchatroomDTO ktuserchatroomDTO) throws Exception {

		int result = 0;
		conn = getConnection();

		// insert into ktuserchatroom values(?, ?, ?, ?, ?, ?)
		String sql1 = VADAConstants.props.getProperty("INSERT_KTCHATROOM_SQL");

		pstmt = conn.prepareStatement(sql1);

		pstmt.setString(1, ktuserchatroomDTO.getKtuserid());
		pstmt.setInt(2, productnum);
		pstmt.setString(3, ktuserchatroomDTO.getKtsellerid());
		pstmt.setString(4, ktuserchatroomDTO.getChatroomtitle());
		pstmt.setInt(5, ktuserchatroomDTO.getChatroomusercnt());
		pstmt.setTimestamp(6, ktuserchatroomDTO.getChatroomdate());

		result = pstmt.executeUpdate();

		return result;
	} // ktchatBoard

	public List<KtuserchatroomDTO> ktchatroomList(String ktuserid) throws Exception {

		conn = getConnection();

		// select * from ktuserchatroom where ktuserid=?
		String sql = VADAConstants.props.getProperty("SELECT_KTCHATROOM_SQL");

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, ktuserid);

		ResultSet rs = pstmt.executeQuery();
		List<KtuserchatroomDTO> list = null;
		if (rs != null && rs.next()) {
			list = new ArrayList<KtuserchatroomDTO>();
			while (rs.next()) {
				KtuserchatroomDTO ktuserchatroomDTO = new KtuserchatroomDTO();
				ktuserchatroomDTO.setKtuserid(rs.getString("ktuserid"));
				ktuserchatroomDTO.setKtproductnum(rs.getInt("ktproductnum"));
				ktuserchatroomDTO.setKtsellerid(rs.getString("ktsellerid"));
				ktuserchatroomDTO.setChatroomtitle(rs.getString("chatroomtitle"));
				ktuserchatroomDTO.setChatroomusercnt(rs.getInt("chatroomusercnt"));
				ktuserchatroomDTO.setChatroomdate(rs.getTimestamp("chatroomdate"));
				list.add(ktuserchatroomDTO);
			}
		}
		closeConnection(rs, pstmt, conn);

		return list;

	} // ktchatroomList
	
} // class
