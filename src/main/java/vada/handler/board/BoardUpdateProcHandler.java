package vada.handler.board;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import vada.dao.impl.board.crud.BoardUpdateDAOImpl;
import vada.dao.impl.board.img.BoardImgDeleteDAOImpl;
import vada.dao.impl.board.img.BoardImgWriteDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.CategoryDTO;
import vada.dto.ImgDTO;
import vada.dto.ProductpriceDTO;
import vada.handler.CommandHandler;
import vada.service.board.crud.BoardUpdateService;
import vada.service.board.img.BoardImgService;
 
// 업데이트 폼에서 새로 수정된 데이터 처리를 위한 핸들러
public class BoardUpdateProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		// 게시글번호(제품번호)에 해당하는 게시글 정보 수정하기 위한 변수
		int productnum = Integer.parseInt((String) request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum"));

		// 기존 이미지 삭제(DB)
		BoardImgService boardImgDeleteService = new BoardImgDeleteDAOImpl();
		try {
			boardImgDeleteService.deleteBoardImg(productnum);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		Collection<Part> parts = null;
		try {
			parts = request.getParts();
		} catch (IOException | ServletException e1) {
			e1.printStackTrace();
		}

		// 이미지 서버 네임 목록 리스트에 저장
		List<String> imgsnameList = (List<String>) request.getAttribute("imgsnameList");

		int listIndex = 0;

		BoardImgService boardImgWriteService = new BoardImgWriteDAOImpl();

		ImgDTO imgDTO = new ImgDTO();
		BoardDTO boardDTO = new BoardDTO();
		CategoryDTO categoryDTO = new CategoryDTO();
		ProductpriceDTO productpriceDTO = new ProductpriceDTO();

		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setProductnum(Integer.parseInt(request.getParameter("productnum")));
		boardDTO.setBcategorynum(Integer.parseInt(request.getParameter("bcategorynum2")));
		productpriceDTO.setProductprice(Integer.parseInt(request.getParameter("productprice")));

		// parts에 저장된 이미지 데이터들을 DB에 저장
		for (Part part : parts) {
			if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize() > 0) {

				imgDTO.setImgcname(part.getSubmittedFileName());
				imgDTO.setImgsname(imgsnameList.get(listIndex));
				imgDTO.setImgsize((int) part.getSize());
				imgDTO.setImgnum(listIndex + 1);

				try {
					boardImgWriteService.writeBoardImg(productnum, imgDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}

				listIndex++;
			}
		}

		// 게시글 작성 시 이미지를 선택하지 않았을 때 default 이미지로
		if (listIndex == 0) {
			imgDTO = new ImgDTO();
			imgDTO.setImgcname("no-image.jpg");
			imgDTO.setImgsname("img/no-image.jpg");
			imgDTO.setImgsize(1);
			imgDTO.setImgnum(1);

			try {
				// DB에 저장
				boardImgWriteService.writeBoardImg(productnum, imgDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		BoardUpdateService boardUpdateService = new BoardUpdateDAOImpl();

		try {
			// 기존 게시글 정보 ( 가격, 제목, 내용, 카테고리 ) 수정
			boardUpdateService.updateBoard(productnum, boardDTO, productpriceDTO, categoryDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/jsp/board/mainFormIndex.jsp?productnum=" + productnum;
	}

}
