package vada.handler.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.func.CategoryListDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.CategoryDTO;
import vada.dto.ProductpriceDTO;
import vada.handler.CommandHandler;
import vada.service.board.func.CategoryService;
 
// 게시글 업데이트 폼에 이전 데이터 출력을 위한 폼
public class BoardUpdateFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		// 이미지 파일 이름 담는 리스트
		List imgcnamelist = new ArrayList();
		// 이미지 사이즈 담는 리스트
		List imgsizelist = new ArrayList();
		
		BoardDTO boardDTO = new BoardDTO();
		ProductpriceDTO productpriceDTO = new ProductpriceDTO();

		boardDTO.setProductnum(Integer.parseInt(request.getParameter("productnum")));
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setContent(request.getParameter("content"));

		productpriceDTO.setProductprice(Integer.parseInt(request.getParameter("productprice")));

		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("productpriceDTO", productpriceDTO);

		// 이미지의 파일명과 사이즈를 리스트에 담음
		for (int i = 0; i < 3; i++) {
			String imgcname = (String) request.getParameter("imgcname" + String.valueOf(i))==null?"":(String) request.getParameter("imgcname" + String.valueOf(i));
			String imgsize = (String) request.getParameter("imgsize" + String.valueOf(i))==null?"":(String) request.getParameter("imgsize" + String.valueOf(i));

			imgcnamelist.add(imgcname);
			imgsizelist.add(imgsize);

		}

		request.setAttribute("imgcnamelist", imgcnamelist);
		request.setAttribute("imgsizelist", imgsizelist);

		CategoryService categoryService = new CategoryListDAOImpl();
		List<CategoryDTO> categoryDTOList = null;
		
		try {
			// 모든 카테고리를 받아옴
			categoryDTOList = categoryService.getCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("categoryDTOList", categoryDTOList);

		return "/jsp/board/boardUpdateForm.jsp";
		
	} // process

} // BoardUpdateFormHandler
