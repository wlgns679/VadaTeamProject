package vada.dao.impl.board.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vada.constants.VADAConstants;
import vada.dao.board.crud.BoardUpdateDAO;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.CategoryDTO;
import vada.dto.ProductpriceDTO;

public class BoardUpdateDAOImpl extends BoardDAOImpl implements BoardUpdateDAO {

	@Override
	//	게시글 수정을 위한 메소드
	public int updateBoard(int productnum, BoardDTO boardDTO, ProductpriceDTO productpriceDTO, CategoryDTO categoryDTO) {
		
		Connection conn = getConnection();

		// setAutoCommit(false)로 설정 : 트랜잭션이 끝날 때까지 commit 하지 않음
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;

		try {
			
			// update board set title=?, wdate=now(), content=? where productnum=? 
			pstmt1 = conn.prepareStatement(VADAConstants.props.getProperty("UPDATE_BOARD_SQL"));
			
			// update productprice set productprice=? where productpricenum=? 
			pstmt2 = conn.prepareStatement(VADAConstants.props.getProperty("UPDATE_PRODUCTPRICE_SQL"));
			
			// update board set bcategorynum=? where productnum=? 
			pstmt3 = conn.prepareStatement(VADAConstants.props.getProperty("UPDATE_CATEGORY_SQL"));

			// 게시글 정보
			pstmt1.setString(1, boardDTO.getTitle());
			pstmt1.setString(2, boardDTO.getContent());
			pstmt1.setInt(3, productnum);

			// 게시글 가격
			pstmt2.setInt(1, productpriceDTO.getProductprice());
			pstmt2.setInt(2, productnum);

			// 카테고리
			pstmt3.setInt(1, boardDTO.getBcategorynum());
			pstmt3.setInt(2, productnum);

			// 게시글 정보, 가격, 카테고리 수정
			result1 = pstmt1.executeUpdate();
			result2 = pstmt2.executeUpdate();
			result3 = pstmt3.executeUpdate();

			if (result1 > 0 && result2 > 0 &&  result3 > 0) {
				conn.commit();
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

			try {
				// 트랜잭션 예외 발생 시 rollback
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		finally {
			if (pstmt3 != null) {
				closeConnection(pstmt3, getConnection());
			}
			if (pstmt2 != null) {
				closeConnection(pstmt2, getConnection());
			}
			if (pstmt1 != null) {
				closeConnection(pstmt1, conn);
			}
			try {
				// 트랜잭션이 종료 시 자동 commit 가능하게 다시 설정
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result1 * result2 * result3;

	} // updateBoard
	
}// class
