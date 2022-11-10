package vada.dao.impl.board.func;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vada.constants.VADAConstants;
import vada.dao.board.func.LikeAddDAO;

public class LikeCheckDAOImpl extends AbstractLikeDAO implements LikeAddDAO {

	// 한 아이디에 중복된 찜목록이 존재하는지 확인하기 위한 메소드
	@Override
	public List likeCheck(String userid) throws Exception {
		
		// select * from likelist where likeuserid=?
		PreparedStatement pstmt = getConnection().prepareStatement(VADAConstants.props.getProperty("SELECT_LIKE_CHECK_SQL"));

		pstmt.setString(1, userid);
		ResultSet rs = pstmt.executeQuery();
		List list = new ArrayList();
		while (rs.next()) {
			list.add(rs.getInt("likeproductnum"));
		}

		closeConnection(rs, pstmt);

		return list;
	}

}