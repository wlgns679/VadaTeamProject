package vada.handler.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.handler.CommandHandler;

// 로그아웃 처리 핸들러
public class LogOutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		
		// 세션 초기화
		session.invalidate();
		
		return "/jsp/user/logOutAlert.jsp";
	}

}
