package vada.dao.impl.board.func;

import java.sql.PreparedStatement;

import vada.constants.VADAConstants;
import vada.dao.board.func.SoldOutDAO;
import vada.dao.impl.board.BoardDAOImpl;

public class SoldOutDAOImpl extends BoardDAOImpl implements SoldOutDAO {

	@Override
	// 제품 예약한 사람과 거래가 완료되면 예약한사람의 ID와 게시글번호(제품번호)를 받아와서 판매완료 처리를 하기 위한 파라미터 및 메소드
	public int soldOut(String reserveid, int productnum) throws Exception {
		
		// update board set buyerid=?, soldoutdate=now() where productnum=?
		PreparedStatement pstmt = getConnection()
				.prepareStatement(VADAConstants.props.getProperty("UPDATE_SOLDOUT_SQL"));

		pstmt.setString(1, reserveid);
		pstmt.setInt(2, productnum);

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, getConnection());
		
		return result;

	} // soldOut
	
} // class
