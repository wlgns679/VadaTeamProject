package vada.handler.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.board.func.CategoryListDAOImpl;
import vada.dao.impl.user.info.UserInfoUpdateDAOImpl;
import vada.dto.CategoryDTO;
import vada.dto.UserDTO;
import vada.handler.CommandHandler;
import vada.service.board.func.CategoryService;
import vada.service.user.info.UserInfoUpdateservice;

// 유저 정보 수정 폼 핸들러
public class UserInfoUpdateFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		UserDTO userDTO = new UserDTO();
		
		// 관심 카테고리를 설정하기위해 카테고리 목록을 출력
		List<CategoryDTO> categoryDTOList = null;
	 	CategoryService categoryService = new CategoryListDAOImpl();
		try {
			categoryDTOList = categoryService.getCategoryList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	 	
	 	request.setAttribute("categoryDTOList", categoryDTOList);
	 	
	 	// 유저 아이디 세션에 저장
	 	String userid=(String)session.getAttribute("userid");
	 	UserInfoUpdateservice userInfoUpdateservice = new UserInfoUpdateDAOImpl();
	 	
	 	try {
	 		// 현재 세션ID에 해당하는 유저 정보를 획득
			userDTO = userInfoUpdateservice.UserInfoSelect(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	
	 	request.setAttribute("userDTO", userDTO);
		return "/jsp/user/userInfoUpdateForm.jsp";
	} // process

} // UserInfoUpdateFormHandler