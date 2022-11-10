package vada.handler.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.func.CategoryListDAOImpl;
import vada.dto.CategoryDTO;
import vada.handler.CommandHandler;
import vada.service.board.func.CategoryService;

// 게시글 쓰기 폼 핸들러
public class BoardWriteFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		// 게시글 쓰기 폼에 DB에 저장된 카테고리 목록 불러오기
	 	CategoryService categoryService = new CategoryListDAOImpl();
	 	List<CategoryDTO> categoryDTOList = null;
		try {
			categoryDTOList = categoryService.getCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	request.setAttribute("categoryDTOList", categoryDTOList);

		return "/jsp/board/boardWriteForm.jsp";
	}
}
