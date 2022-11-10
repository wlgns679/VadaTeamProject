package vada.handler.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.user.info.UserInfoUpdateDAOImpl;
import vada.dto.UserDTO;
import vada.handler.CommandHandler;
import vada.service.user.info.UserInfoUpdateservice;

// 유저 정보 수정 처리를 위한 핸들러
public class UserInfoUpdateProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		UserDTO userDTO = new UserDTO();
		
		// 유저 폼에서 넘어온 데이터를 userDTO 객체에 저장
		userDTO.setAddress(request.getParameter("address"));
		userDTO.setDetailaddress(request.getParameter("detailaddress"));
		userDTO.setEmail(request.getParameter("email"));
		userDTO.setName(request.getParameter("name"));
		userDTO.setInterestcategorynum(Integer.parseInt(request.getParameter("interestcategorynum")));
		userDTO.setNickname(request.getParameter("nickname"));
		userDTO.setTel(request.getParameter("tel"));
		userDTO.setUserpw(request.getParameter("userpw"));
		
		
		UserInfoUpdateservice userInfoUpdateservice = new UserInfoUpdateDAOImpl();
	    String userid=(String)session.getAttribute("userid");
	    
	    try {
	    	// 현재 세션ID에 해당하는 유저 정보 수정
			userInfoUpdateservice.UserInfoUpdate(userid, userDTO);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return "/jsp/board/mainFormIndex.jsp";
	} // process

} // UserInfoUpdateProcHandler