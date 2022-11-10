package vada.dao.impl.board.img;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vada.constants.CommonConstants;
import vada.constants.VADAConstants;
import vada.dto.ImgDTO;
import vada.dto.NotifyimgDTO;

public class BoardImgWriteDAOImpl extends AbstractBoardImgDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;

	// 게시글에 해당하는 이미지를 insert하기 위한 imgproductum(productnum), imgDTO 파라미터
	@Override
	public int writeBoardImg(int imgproductnum, ImgDTO imgDTO) throws Exception {

		conn = getConnection();

		// insert into img (imgproductnum, imgnum, imgsname, imgsize, imgcname) values (?, ?, ?, ?, ?)
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("INSERT_IMG_SQL"));

		pstmt.setInt(1, imgproductnum);
		pstmt.setInt(2, imgDTO.getImgnum());

		String[] imgsname = imgDTO.getImgsname().split("img");
		pstmt.setString(3, imgsname[1]);

		pstmt.setInt(4, imgDTO.getImgsize());
		pstmt.setString(5, imgDTO.getImgcname());

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, conn);

		return result;

	} // writeBoardImg
	
	// 신고 게시글에 해당하는 신고 이미지를 insert하기 위한 notifyid, notifyImgDTO 파라미터
	@Override
	public int notifyWriteBoardImg(int notifyid, NotifyimgDTO notifyImgDTO) throws Exception {
		conn = getConnection();

		// insert into notifyimg (notifyimgnotifyid, notifyimgnum, notifyimgsname, notifyimgsize, notifyimgcname, notifyimgdate) values (?, ?, ?, ?, ?, now())
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_NOTIFY_IMG_WRITE_SQL"));
		
		pstmt.setInt(1, notifyid);
		pstmt.setInt(2, notifyImgDTO.getNotifyimgnum());
		
		String [] notifyimgsname = notifyImgDTO.getNotifyimgsname().split("img");
		pstmt.setString(3, notifyimgsname[1]);
		
		pstmt.setInt(4, notifyImgDTO.getNotifyimgsize());
		pstmt.setString(5, notifyImgDTO.getNotifyimgcname());

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, conn);

		return result;
		
	} // notifyWriteBoardImg

} // class
