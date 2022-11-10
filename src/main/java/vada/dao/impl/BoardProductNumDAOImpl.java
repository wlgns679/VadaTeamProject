package vada.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vada.constants.VADAConstants;
import vada.dao.BoardProductNumDAO;
import vada.dao.impl.board.BoardDAOImpl;

public class BoardProductNumDAOImpl extends BoardDAOImpl implements BoardProductNumDAO {

	@Override
	// 게시글 작성 시 이미지와 게시글 매칭을 위해 필요한 마지막 제품넘버를 얻는 메소드 
	public int getProductNum() throws Exception {

		Connection conn = getConnection();
		
		// select * from board order by productnum desc limit 1 
		PreparedStatement pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_BOARD_SQL"));
	
		ResultSet rs = pstmt.executeQuery();
		
		int productnum = 0;
		
		if (rs.next()) {
			productnum = rs.getInt("productnum");
		}

		closeConnection(rs, pstmt, conn);

		return productnum;

	} // getProductNum
	
	@Override
	// 페이징처리를 위해 모든 게시글 수를 얻는 메소드 
	public int allProductCount() throws Exception {

		Connection conn = getConnection();
		
		// select count(*) from board
		PreparedStatement pstmt = conn.prepareStatement(VADAConstants.props.getProperty("ALL_PRODUCT_COUNT"));
	
		ResultSet rs = pstmt.executeQuery();
		
		int productCount = 0;
		
		if (rs.next()) {
			productCount = rs.getInt(1);
		}

		closeConnection(rs, pstmt, conn);

		return productCount;

	} // allProductCount

} // class
