package vada.handler.board;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import vada.dao.impl.BoardProductNumDAOImpl;
import vada.dao.impl.board.crud.BoardWriteDAOImpl;
import vada.dao.impl.board.img.BoardImgWriteDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.ImgDTO;
import vada.handler.CommandHandler;
import vada.service.board.crud.BoardWriteService;
import vada.service.board.img.BoardImgService;

// 글쓰기 처리 핸들러
public class BoardWriteProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		BoardDTO boardDTO = new BoardDTO();

		boardDTO.setSellerid((String) session.getAttribute("userid"));
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setContent(request.getParameter("content"));

		// 상위 카테고리가 1000(전체) 이면 전체에 해당하는 키워드넘버(1000) 으로 지정
		if (request.getParameter("bcategorynum2").equals("1000")) {
			boardDTO.setBcategorynum(Integer.parseInt(request.getParameter("bcategorynum")));
		// 아니라면 하위 카테고리 넘버로 지정
		} else {
			boardDTO.setBcategorynum(Integer.parseInt(request.getParameter("bcategorynum2")));
		}

		BoardWriteService boardWriteService = new BoardWriteDAOImpl();
		BoardImgService boardImgWriteService = new BoardImgWriteDAOImpl();

		int result = 0;
		int productNum = 0;
		Collection<Part> parts = null;
		int listIndex = 0;
		ImgDTO imgDTO = null;
		List<String> imgsnameList = (List<String>) request.getAttribute("imgsnameList");

		try {

			// 게시글 데이터 DB에 저장
			result = boardWriteService.writeBoard(boardDTO);

			// 가장 마지막에 작성된 게시글 번호 가져오기
			productNum = new BoardProductNumDAOImpl().getProductNum();

			// 게시글의 가격 데이터 DB에 저장
			boardWriteService.writePrice(productNum, Integer.parseInt(request.getParameter("productprice")));

			// 게시글의 이미지 데이터 DB에 저장
			parts = request.getParts();

			for (Part part : parts) {
				imgDTO = new ImgDTO();

				if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize() > 0) {
					imgDTO.setImgcname(part.getSubmittedFileName());
					imgDTO.setImgsname(imgsnameList.get(listIndex));
					imgDTO.setImgsize((int) part.getSize());
					imgDTO.setImgnum(listIndex + 1);

					boardImgWriteService.writeBoardImg(productNum, imgDTO);

					listIndex++;
				}

			}

			// 게시글 작성 시 이미지를 선택하지 않았을 때 default 이미지로 DB에 저장
			if (listIndex == 0) {
				imgDTO = new ImgDTO();
				imgDTO.setImgcname("no-image.jpg");
				imgDTO.setImgsname("img/no-image.jpg");
				imgDTO.setImgsize(1);
				imgDTO.setImgnum(1);

				boardImgWriteService.writeBoardImg(productNum, imgDTO);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/jsp/board/mainFormIndex.jsp";
	} // process

} // BoardWriteProcHandler
