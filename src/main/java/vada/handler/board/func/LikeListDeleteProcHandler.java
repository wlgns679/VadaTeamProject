package vada.handler.board.func;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.board.func.LikeListDeleteDAOImpl;
import vada.handler.CommandHandler;

// 찜목록 삭제 처리 핸들러
public class LikeListDeleteProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		int productnum = Integer.parseInt((String) request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum"));

		String userid = (String) session.getAttribute("userid");
		try {
			// 찜목록 삭제
			new LikeListDeleteDAOImpl().likeDelete(userid, productnum);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		return "/Vada/jsp/board/func/likeListFormIndex.jsp";
		return "/likelistform.do";
	} // process

} // LikeListDeleteProcHandler
