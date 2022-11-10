package vada.handler.board.func;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import vada.dao.impl.board.crud.BoardWriteDAOImpl;
import vada.dao.impl.board.img.BoardImgWriteDAOImpl;
import vada.dto.NotifyimgDTO;
import vada.dto.NotifylistDTO;
import vada.handler.CommandHandler;
import vada.service.board.crud.BoardWriteService;
import vada.service.board.img.BoardImgService;

// 신고글 쓰기 처리 핸들러
public class NotifyWriteProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		int notifyProductNum = Integer.parseInt(request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum"));
		NotifylistDTO notifyDTO = new NotifylistDTO();
		notifyDTO.setNotifyreason(request.getParameter("notifyreason"));
		
		int result = 0; // DB 작성완료시 0<result
		
		BoardWriteService notifyWriteService = new BoardWriteDAOImpl();
		try {
			// 신고 게시글 쓰기
			result = notifyWriteService.notifyWriteBoard(notifyDTO, notifyProductNum, userid);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		int notifyid = 0;
		try {
			// 신고 게시글 마지막 ID 획득
			notifyid = notifyWriteService.get_Notifyid();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 이미지 쓰기 Impl 생성
		BoardImgService notifyImgService = new BoardImgWriteDAOImpl();
		Collection<Part> parts = null; 
		try {
			parts = request.getParts();
		} catch (Exception ex) { 
 
		}

		// 신고 첨부파일 파일 처리
		List<String> imgsnameList = (List<String>) request.getAttribute("imgsnameList");
		int listIndex = 0;
		NotifyimgDTO notifyImgDTO = null;
		
		for (Part part : parts) {
			if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize() > 0) {
				notifyImgDTO = new NotifyimgDTO();
				notifyImgDTO.setNotifyimgcname(part.getSubmittedFileName());
				notifyImgDTO.setNotifyimgsname(imgsnameList.get(listIndex));
				notifyImgDTO.setNotifyimgnum(listIndex + 1);
				notifyImgDTO.setNotifyimgsize((int) part.getSize());
				try {
					notifyImgService.notifyWriteBoardImg(notifyid, notifyImgDTO);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				listIndex++;
			}
		}

		// 신고글 작성 시 이미지를 선택하지 않았을 때 default 이미지로 DB에 저장
		if (listIndex == 0) {
			notifyImgDTO = new NotifyimgDTO();
			notifyImgDTO.setNotifyimgcname("no-image.jpg");
			notifyImgDTO.setNotifyimgsname("img/no-image.jpg");
			notifyImgDTO.setNotifyimgsize(1);
			notifyImgDTO.setNotifyimgnum(1);
			
			try {
				notifyImgService.notifyWriteBoardImg(notifyid, notifyImgDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		return "/jsp/board/mainFormIndex.jsp";
		
	} // process

} // NotifyWriteProcHandler
