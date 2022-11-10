package vada.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;

import vada.constants.CommonConstants;
import vada.dao.impl.board.img.BoardImgListDAOImpl;
import vada.dto.ImgDTO;
import vada.service.board.img.BoardImgService;

public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 14357642724634526L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	} // doGet

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("command===========>" + req.getParameter("command"));
		
		String command = req.getParameter("command") == null ? "" : req.getParameter("command"); 
		
		if (command.equals("write")) {
			process(req, resp, "/boardwriteproc.do", "IMG_UPLOAD_DIR");
		} 
		else if(command.equals("notifyWrite")) {
			process(req, resp,"/notifywriteproc.do","NOTIFY_IMG_UPLOAD_DIR");
		}
		
		else if (command.equals("update")) {  // 게시글 수정일 때
			
			// 서버 컴퓨터 이미지 경로에 있는 이미지 삭제
			BoardImgService boardImgService = new BoardImgListDAOImpl();
			List<ImgDTO> imgDTOList = null;
			try {
				imgDTOList = boardImgService.getBoardImgList(Integer.parseInt(req.getParameter("productnum")));
				for (ImgDTO imgDTO : imgDTOList) {
					File sfnFile = new File("C:/eclipse_workspace/Vada/src/main/webapp/"+imgDTO.getImgsname());
					if (sfnFile.exists())
						sfnFile.delete();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			System.out.println("여기까지 오나요??"+req.getParameter("productnum"));
			
			process(req, resp, "/boardupdateproc.do", "IMG_UPLOAD_DIR");
			
			
		} // else if
		
	} // dopost
	
	private void process(HttpServletRequest req, HttpServletResponse resp, String dispatchURI, String imgDir) {
		try {
			Collection<Part> parts = req.getParts();
			List<String> imgsnameList = new ArrayList<String>();
			List<String> contenttypeList = new ArrayList<String>();
			for (Part part : parts) {

				if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize() > 0) {

					File uploadDir = new File(CommonConstants.props.getProperty(imgDir)
							+ new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())));
					System.out.println(CommonConstants.props.getProperty(imgDir));
					
					if (!uploadDir.exists())
						uploadDir.mkdir();

					String uploadFileName = "";
					uploadFileName = uploadDir.getPath() + "/" + RandomStringUtils.random(100, true, true).substring(0, 10)+".png";

					imgsnameList.add(uploadFileName);

					part.write(uploadFileName);
					part.delete();

				} // outter if

			} // for

			req.setAttribute("imgsnameList", imgsnameList);

//			product = request
//			private name  = sfnList
//			private type  = contenttype
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(dispatchURI);
			dispatcher.forward(req, resp);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	} // process

} // class
