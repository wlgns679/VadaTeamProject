package vada.dao.impl.board.func;

import java.sql.PreparedStatement;

import vada.constants.VADAConstants;
import vada.dao.board.func.LikeAddDAO;

public class LikeListDeleteDAOImpl extends AbstractLikeDAO implements LikeAddDAO {

	// 해당 세션 ID와 제품번호에 매칭되는 찜목록테이블의 데이터 행을 삭제
	// 찜목록 삭제를 위한 메소드
	@Override
	public int likeDelete(String userid, int productnum) throws Exception {

		// delete from likelist where likeproductnum=? and likeuserid=?
		PreparedStatement pstmt = getConnection()
				.prepareStatement(VADAConstants.props.getProperty("DELETE_LIKE_DELETE_SQL"));
		
		pstmt.setInt(1, productnum);
		pstmt.setString(2, userid);
		
		int result = pstmt.executeUpdate();
		
		closeConnection(pstmt, getConnection());
		
		return result;
		
	} // likeDelete
	
} // class
