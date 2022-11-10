package vada.dao.impl.board.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vada.constants.VADAConstants;
import vada.dao.board.crud.BoardWriteDAO;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.NotifylistDTO;
import vada.util.ConnectionManager;

public class BoardWriteDAOImpl extends BoardDAOImpl implements BoardWriteDAO {
	// 게시글 테이블 작성을 위한 메소드
	
	Connection conn = null;
	PreparedStatement pstmt = null;

	@Override
	public int writeBoard(BoardDTO boardDTO) throws Exception {

		conn = getConnection();
		
		//insert into board (sellerid, title, wdate, content, bcategorynum, buyerid) values (?, ?, now(), ?, ?, 'default')
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("INSERT_BOARD_SQL"));

		pstmt.setString(1, boardDTO.getSellerid());
		pstmt.setString(2, boardDTO.getTitle());
		pstmt.setString(3, boardDTO.getContent());
		pstmt.setInt(4, boardDTO.getBcategorynum());

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, conn);

		return result;

	} // writeBoard

	// 게시글 테이블 작성 후 제품 가격 테이블 처리를 위한 메소드
	@Override
	public int writePrice(int productnum, int productprice) throws Exception {
		
		conn = getConnection();

		// insert into productprice (productpricenum, productprice, productpriceupdatedate) values (?, ?, now()) 
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("INSERT_PRODUCTPRICE_SQL"));
		
		pstmt.setInt(1, productnum);
		pstmt.setInt(2, productprice);

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, getConnection());

		return result;
		
	} // writeBoard

	// 신고글 작성을 위한 메소드
	@Override
	public int notifyWriteBoard(NotifylistDTO notifyDTO, int notifyProductNum, String userid) throws Exception {
			
		conn = getConnection();

		int result = 0;
		
		//insert into notifylist (notifyproductnum, notifyreason, notifyuserid, notifydate) values (?, ?, ?, now())
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("INSERT_NOTIFY_WRITE_SQL"));

		pstmt.setInt(1, notifyProductNum);
		pstmt.setString(2, notifyDTO.getNotifyreason());
		pstmt.setString(3, userid);

		result = pstmt.executeUpdate();

		closeConnection(pstmt, conn);

		return result;

	} // notifyWriteBoard

	// 신고글 작성 후 신고글 ID와 이미지를 매칭시키기 위해 마지막 신고글 ID를 얻기 위한 메소드
	@Override
	public int get_Notifyid() throws Exception {
		
		conn = ConnectionManager.getConnection();

		// select notifyid from notifylist order by notifyid desc limit 1
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_NOTIFY_ID_SQL"));

		ResultSet rs = pstmt.executeQuery();

		int notifyid = 0;

		if (rs != null && rs.next()) {
			notifyid = rs.getInt("notifyid");
		}

		closeConnection(rs, pstmt, conn);

		return notifyid;

	} // get_Notifyid

} // class