package vada.dao.impl.board.func;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vada.constants.VADAConstants;
import vada.dao.board.func.BoardSearchListDAO;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dto.BoardDTO;

public class BoardSearchListDAOImpl extends BoardDAOImpl implements BoardSearchListDAO {

	// 카테고리 및 검색 키워드에 매칭되는 게시글을 얻기 위한 메소드
	@Override
	public List<Map<String, Object>> searchBoard(String level1Category, String level2Category, String searchText)
			throws Exception {

		StringBuffer whereSQLBuffer = new StringBuffer();

		// 전체 검색(카테고리1을 선택 안 했을 때)
		if (level1Category.equals("1000")) {
			whereSQLBuffer.append(" and 1=1 ");
		}

		// 카테고리 1은 선택하고 카테고리2를 선택 안 했을 때
		else if (level2Category.equals("1000")) {
			whereSQLBuffer.append(" and bcategorynum like '");
			String cate1prepend = level1Category.substring(0, 2);
			whereSQLBuffer.append(cate1prepend);
			whereSQLBuffer.append("%' ");
		}

		// 카테고리1 카테고리2를 모두 선택했을 때
		else {
			whereSQLBuffer.append(" and bcategorynum like '");
			whereSQLBuffer.append(level2Category);
			whereSQLBuffer.append("%' ");
		}

		// 검색어가 있을 때 게시글 제목에 해당 검색어가 포함된 게시글 가져옴
		if (searchText != null) {
			whereSQLBuffer.append(" and title like '%");
			whereSQLBuffer.append(searchText);
			whereSQLBuffer.append("%' ");
		}

		whereSQLBuffer.append(" order by wdate desc ");

		String searchQuery = whereSQLBuffer.toString();

		Connection conn = getConnection();

		// select * from board b inner join img i on b.productnum=i.imgproductnum inner
		// join productprice p on p.productpricenum=b.productnum where i.imgnum=1 and
		// searchQuery
		PreparedStatement pstmt = conn
				.prepareStatement(VADAConstants.props.getProperty("SELECT_BOARD_IMG_PRICE_SQL") + searchQuery);

		ResultSet rs = pstmt.executeQuery();

		// Map 타입 boardMap을 하나씩 리스트로 담기 위한 ArrayList
		List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();

		while (rs.next()) {

			// BoardDTO, BoardPrice, ImgDTO 세가지 객체의 필요한 데이터들만 담기 위한 map 타입 boardMap
			Map<String, Object> boardMap = new HashMap<String, Object>();

			boardMap.put("title", rs.getString("title"));
			boardMap.put("productnum", rs.getInt("productnum"));
			boardMap.put("wdate", rs.getTimestamp("wdate"));
			boardMap.put("imgsname", rs.getString("imgsname"));
			boardMap.put("imgcname", rs.getString("imgcname"));
			boardMap.put("productprice", rs.getInt("productprice"));

			boardList.add(boardMap);

		}

		closeConnection(rs, pstmt, conn);

		return boardList;

	} // searchBoard

	@Override
	public List<Map<String, Object>> searchCateBoard(int categoryNum) throws Exception {

		StringBuffer whereSQLBuffer = new StringBuffer();

		whereSQLBuffer.append(" and bcategorynum like ");
		whereSQLBuffer.append("'" + categoryNum + "%' and bcategorynum != 1000");

		whereSQLBuffer.append(" order by wdate desc ");

		String searchQuery = whereSQLBuffer.toString();

		Connection conn = getConnection();

		// select * from board b inner join img i on b.productnum=i.imgproductnum inner
		// join productprice p on p.productpricenum=b.productnum where i.imgnum=1
		// searchQuery
		PreparedStatement pstmt = conn
				.prepareStatement(VADAConstants.props.getProperty("SELECT_BOARD_IMG_PRICE_SQL") + searchQuery);

		ResultSet rs = pstmt.executeQuery();

		// Map 타입 boardMap을 하나씩 리스트로 담기 위한 ArrayList
		List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();

		while (rs.next()) {

			// BoardDTO, BoardPrice, ImgDTO 세가지 객체의 필요한 데이터들만 담기 위한 map 타입 boardMap
			Map<String, Object> boardMap = new HashMap<String, Object>();

			boardMap.put("title", rs.getString("title"));
			boardMap.put("productnum", rs.getInt("productnum"));
			boardMap.put("wdate", rs.getTimestamp("wdate"));
			boardMap.put("imgsname", rs.getString("imgsname"));
			boardMap.put("imgcname", rs.getString("imgcname"));
			boardMap.put("productprice", rs.getInt("productprice"));

			boardList.add(boardMap);

		}

		closeConnection(rs, pstmt, conn);

		return boardList;

	} // searchBoard

} // class
