package vada.dao.impl.board.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vada.constants.VADAConstants;
import vada.dao.board.crud.BoardListDAO;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.ImgDTO;
import vada.dto.NotifylistDTO;
import vada.dto.ProductpriceDTO;

public class BoardListDAOImpl extends BoardDAOImpl implements BoardListDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	// 게시글 리스트를 얻는 메소드
	public List<Map<String, Object>> getBoardList() throws Exception {

		conn = getConnection();
		
		StringBuffer whereSQLBuffer = new StringBuffer();
		whereSQLBuffer.append(" order by wdate desc ");
		String descQuery = whereSQLBuffer.toString();
		
		// select * from board b inner join img i on b.productnum=i.imgproductnum inner join productprice p on p.productpricenum=b.productnum where i.imgnum=1 order by wdate desc
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_BOARD_IMG_PRICE_SQL") + descQuery) ;

		rs = pstmt.executeQuery();

		List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();

		while (rs.next()) {
			
			BoardDTO boardDTO = new BoardDTO();
			ImgDTO imgDTO = new ImgDTO();
			ProductpriceDTO productPriceDTO = new ProductpriceDTO();

			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWdate(rs.getTimestamp("wdate"));
			boardDTO.setProductnum(rs.getInt("productnum"));
			
			productPriceDTO.setProductprice(rs.getInt("productprice"));

			imgDTO.setImgsname(rs.getString("imgsname"));
			imgDTO.setImgcname(rs.getString("imgcname"));
			imgDTO.setImgproductnum(rs.getInt("imgproductnum"));

			Map<String, Object> boardMap = new HashMap<String, Object>();

			boardMap.put("title", boardDTO.getTitle());
			boardMap.put("productnum", boardDTO.getProductnum());
			boardMap.put("wdate", boardDTO.getWdate());
			
			boardMap.put("productprice", productPriceDTO.getProductprice());
			
			boardMap.put("imgsname", imgDTO.getImgsname());
			boardMap.put("imgcname", imgDTO.getImgcname());
			boardMap.put("imgproductnum", imgDTO.getImgproductnum());

			boardList.add(boardMap);

		}

		closeConnection(rs, pstmt, conn);

		return boardList;

	} // getBoardList
	
	@Override
	// 신고 게시글 리스트를 얻는 메소드
	public List<NotifylistDTO> notifyListBoard() throws Exception {

		conn = getConnection();

		//select * from notifylist order by notifyid desc
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_NOTIFY_LIST_SQL"));

		rs = pstmt.executeQuery();

		List<NotifylistDTO> list = new ArrayList<NotifylistDTO>();

		while (rs.next()) {

			NotifylistDTO notifyListDTO = new NotifylistDTO();

			notifyListDTO.setNotifyid(rs.getInt("notifyid"));
			notifyListDTO.setNotifyreason(rs.getString("notifyreason"));
			notifyListDTO.setNotifyuserid(rs.getString("notifyuserid"));
			notifyListDTO.setNotifydate(rs.getTimestamp("notifydate"));

			list.add(notifyListDTO);

		}

		closeConnection(rs, pstmt, conn);

		return list;

	} // notifyListBoard

} // class
