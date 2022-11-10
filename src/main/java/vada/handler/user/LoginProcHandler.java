package vada.handler.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.user.sign.LoginDAOImpl;
import vada.dto.UserDTO;
import vada.handler.CommandHandler;
import vada.service.user.sign.LoginService;

// 로그인 처리 핸들러
public class LoginProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String userid = (String) request.getParameter("userid") == null ? "" : (String) request.getParameter("userid");
		String userpw = (String) request.getParameter("userpw") == null ? "" : (String) request.getParameter("userpw");

		LoginService loginService = new LoginDAOImpl();

		UserDTO userDTO = null;
		
		// 사용자가 입력한 아이디와 비밀번호에 매칭된 사용자 정보를 userDTO에 저장
		userDTO = loginService.userLogin(userid, userpw);

		String url = "";

		// 매칭된 사용자 정보가 없는 경우 로그인 실패
		if (userDTO == null) {
			url = "/jsp/check/failedLogin.jsp";
		}

		// 매칭된 사용자 정보가 있는 경우
		else if (userDTO != null) {
			
			// 블랙 리스트 회원이 로그인 시
			if (userDTO.getBlackyn().equals("yes")) {
				url = "/jsp/check/blackIDLogin.jsp";
			} 
			
			// 블랙 리스트 회원이 아닌 일반 사용자 로그인 시
			else {
				request.setAttribute("msg", "로그인에 성공하셨습니다.");
				session.setAttribute("dbusernickname", userDTO.getNickname());
				session.setAttribute("userid", userDTO.getUserid());
				session.setAttribute("adminyn", userDTO.getAdminyn());
				url = "/mainform.do";
			}
		}

		return url;
		
	} // process

} // LoginProcHandler
