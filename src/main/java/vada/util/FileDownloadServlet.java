//package vada.util;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import vada.dto.ImgDTO;
//
//public class FileDownloadServlet extends HttpServlet {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		filedownload(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		filedownload(req, resp);
//	}
//
//	private void filedownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
////		BoardFileService boardFileService = new BoardFileViewDAOImpl();
////		BoardFileDTO boardFileDTO = null;
//		try {
////			boardFileDTO = boardFileService.viewBoardFile(Integer.parseInt(req.getParameter("bfid")));
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
////		resp.setContentType(boardFileDTO.getBfcontenttype());
////		resp.setHeader("Content-disposition", "attachment; filename=" + boardFileDTO.getBfcfn());
////		File sfnFile = new File(boardFileDTO.getBfsfn());
////		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sfnFile));
//		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
//		byte[] buffer = new byte[10240];
//		int length;
////		while ((length = bis.read(buffer)) > 0) {
////			bos.write(buffer, 0, length);
////		}
////		bis.close();
//		bos.flush();
//		bos.close();
//	} 
//} // class
