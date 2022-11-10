package vada.dao.impl.board.func;

import java.sql.PreparedStatement;

import vada.constants.VADAConstants;
import vada.dao.board.func.LikeAddDAO;

public class LikeAddDAOImpl extends AbstractLikeDAO implements LikeAddDAO {

	// 찜목록 추가를 위한 메소드
	@Override
	public int likeAdd(String userid, int productnum) throws Exception {
		
		//insert into likelist (likeuserid, likeproductnum, likedate) values (?, ?, now())
		PreparedStatement pstmt = getConnection().prepareStatement(VADAConstants.props.getProperty("INSERT_LIKE_ADD_SQL"));
		
		pstmt.setString(1, userid);
		pstmt.setInt(2, productnum);
		int result = pstmt.executeUpdate();
		
		closeConnection(pstmt, getConnection());
		
		return result;
		
	} // likeAdd

} // class
