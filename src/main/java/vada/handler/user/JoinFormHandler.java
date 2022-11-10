package vada.handler.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.func.CategoryListDAOImpl;
import vada.dto.CategoryDTO;
import vada.handler.CommandHandler;
import vada.service.board.func.CategoryService;

// 관심카테고리를 설정하기 위해 카테고리 목록을 보여주기 위한 회원가입 폼 핸들러
public class JoinFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		CategoryService categoryService = new CategoryListDAOImpl();
		List<CategoryDTO> categoryDTOList = null;
		try {
			// 카테고리 목록 불러오기
			categoryDTOList = categoryService.getCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("categoryDTOList", categoryDTOList);

		return "/jsp/user/joinForm.jsp";
	} // process
  
} // JoinFormHandler
