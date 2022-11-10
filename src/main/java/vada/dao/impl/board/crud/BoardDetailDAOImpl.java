package vada.dao.impl.board.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vada.constants.VADAConstants;
import vada.dao.board.crud.BoardDetailDAO;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.CategoryDTO;
import vada.dto.ImgDTO;
import vada.dto.NotifylistDTO;
import vada.dto.ProductpriceDTO;

public class BoardDetailDAOImpl extends BoardDAOImpl implements BoardDetailDAO {

	Connection conn = null;

	// 제품번호에 해당하는 게시글을 select하기 위한 파라미터 및 메소드
	@Override
	public Map<String, Object> getBoardList(int productnum) throws Exception {		

		Map<String, Object> map = new HashMap<String, Object>();

		conn = getConnection();

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

		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		try {

			// select * from board b inner join productprice p on b.productnum = p.productpricenum where productnum=?
			pstmt1 = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_BOARD_PRICE_SQL"));

			// select * from categories c inner join board b ON b.bcategorynum = c.categorynum where productnum=?
			pstmt2 = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_BOARD_CATEGORY_SQL"));

			// select * from board b inner join img i on b.productnum = i.imgproductnum where productnum=?
			pstmt3 = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_BOARD_IMG_SQL"));

			pstmt1.setInt(1, productnum);
			pstmt2.setInt(1, productnum);
			pstmt3.setInt(1, productnum);

			rs1 = pstmt1.executeQuery();
			rs2 = pstmt2.executeQuery();
			rs3 = pstmt3.executeQuery();

			BoardDTO boardDTO = null;
			ProductpriceDTO productDTO = null;
			CategoryDTO categoryDTO = null;
			ImgDTO imgDTO = null;

			if (rs1 != null && rs1.next()) {

				boardDTO = new BoardDTO();
				productDTO = new ProductpriceDTO();

				// 게시글 정보 DB에서 가져옴
				boardDTO.setTitle(rs1.getString("title"));
				boardDTO.setBcategorynum(rs1.getInt("bcategorynum"));
				boardDTO.setContent(rs1.getString("content"));
				boardDTO.setProductnum(productnum);
				boardDTO.setReservation(rs1.getString("reservationyn"));
				boardDTO.setBuyerid(rs1.getString("buyerid"));
				boardDTO.setSellerid(rs1.getString("sellerid"));
				boardDTO.setReserveid(rs1.getString("reserveid"));
				boardDTO.setSoldoutdate(rs1.getTimestamp("soldoutdate"));
				boardDTO.setReview(rs1.getString("review"));
				boardDTO.setReviewscore(rs1.getInt("reviewscore"));

				// 게시글 가격 DB에서 가져옴
				productDTO.setProductprice(rs1.getInt("productprice"));

			}

			if (rs2 != null && rs2.next()) {
				categoryDTO = new CategoryDTO();
				// 해당 게시글의 카테고리 분류 DB에서 가져옴
				categoryDTO.setCategoryname(rs2.getString("categoryname"));
			}

			List<ImgDTO> imgDTOList = new ArrayList<ImgDTO>();

			while (rs3 != null && rs3.next()) {
				imgDTO = new ImgDTO();

				// 해당 게시글의 사진 정보 DB에서 가져옴
				imgDTO.setImgsname(rs3.getString("imgsname"));
				imgDTO.setImgcname(rs3.getString("imgcname"));
				imgDTO.setImgsize(rs3.getInt("imgsize"));

				imgDTOList.add(imgDTO);
			}

			map.put("boardDTO", boardDTO);
			map.put("ProductpriceDTO", productDTO);
			map.put("categoryDTO", categoryDTO);

			map.put("imgDTOList", imgDTOList);

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				// 트랜잭션 예외 발생 시 rollback
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			
			try {
				// 트랜잭션이 종료 시 자동 commit 가능하게 다시 설정
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (rs3 != null) {
				closeConnection(rs1, pstmt1);
			}
			if (rs2 != null) {
				closeConnection(rs1, pstmt1);
			}
			if (rs1 != null) {
				closeConnection(rs1, pstmt1, conn);
			}
		}

		return map;

	} // viewBoard

	// 신고글ID에 해당하는 신고글 select 위한 파라미터
	@Override
	public Map<String, Object> notifyView(int notifyid) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		conn = getConnection();
		
		// select * from notifylist where notifyid=?
		PreparedStatement pstmt1 = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_NOTIFY_VIEW_SQL"));
		
		pstmt1.setInt(1, notifyid);

		// select * from notifyimg where notifyimgnotifyid=?
		PreparedStatement pstmt2 = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_NOTIFY_IMG_VIEW_SQL"));
		
		pstmt2.setInt(1, notifyid);

		ResultSet rs1 = pstmt1.executeQuery();
		ResultSet rs2 = pstmt2.executeQuery();

		NotifylistDTO notifylistDTO = null;

		if (rs1 != null && rs1.next()) {

			notifylistDTO = new NotifylistDTO();

			notifylistDTO.setNotifyreason(rs1.getString("notifyreason"));
			notifylistDTO.setNotifyuserid(rs1.getString("notifyuserid"));
			notifylistDTO.setNotifyid(rs1.getInt("notifyid"));
			notifylistDTO.setNotifydate(rs1.getTimestamp("notifydate"));
			notifylistDTO.setNotifyproductnum(rs1.getInt("notifyproductnum"));
			
		}

		List<String> list = new ArrayList<String>();
		
		while (rs2 != null && rs2.next()) {
			list.add(rs2.getString("notifyimgsname"));
		}

		map.put("notifylistDTO", notifylistDTO);
		map.put("imglist", list);

		closeConnection(rs1, pstmt1, conn);
		closeConnection(rs2, pstmt2);

		return map;
		
	} // notifyView
	
	// 예약 및 예약 취소 버튼 클릭을 처리하는 메소드
	@Override
	public int reserveBoard(int productnum, String command, String userid) throws Exception {
		
		conn = getConnection();

		PreparedStatement pstmt = null;

		// 예약 버튼을 클릭하면 reservationyn 을 yes로 변경
		if (command.equals("reserve")) {
			// update board set reservationyn='yes', reserveid=? where productnum=?
			pstmt = conn.prepareStatement(VADAConstants.props.getProperty("UPDATE_YES_RESERVE_BOARD_SQL"));
			pstmt.setString(1, userid);
			pstmt.setInt(2, productnum);
			
		} 
		// 예약 취소 버튼을 클릭하면 reservationyn 을 no로 변경
		else if(command.equals("cancel")){
			// update board set reservationyn='no', reserveid='default' where productnum=?
			pstmt = conn.prepareStatement(VADAConstants.props.getProperty("UPDATE_NO_RESERVE_BOARD_SQL"));
			pstmt.setInt(1, productnum);
			
		}

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, conn);
	
		return result;
	
	} // reserveBoard
	
} // class