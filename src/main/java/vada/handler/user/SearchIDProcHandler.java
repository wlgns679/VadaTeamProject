package vada.handler.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.user.search.SearchUserIDDAOImpl;
import vada.handler.CommandHandler;
import vada.service.user.search.SearchUserIDService;

// ID찾기 처리 핸들러
public class SearchIDProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email")==null?"":request.getParameter("email");
		String name = request.getParameter("username")==null?"":request.getParameter("username");

		SearchUserIDService searchUserIDService = new SearchUserIDDAOImpl();

		String userid = null;
		try {
			// 이름과 이메일을 모두 만족하는 userid 획득
			userid = searchUserIDService.searchUserID(name, email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = "";

		// 유저 아이디가 없다면
		if (userid == null) {
			url = "jsp/check/failedFindID.jsp";

		// 유저 아이디가 있다면
		} else {
			url = "jsp/check/findIDLogin.jsp?searchUserid=" + userid;
		}
		
		return url;
	} // process
} // SearchIDProcHandler
