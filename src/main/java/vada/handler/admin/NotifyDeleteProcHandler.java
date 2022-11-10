package vada.handler.admin;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.crud.BoardDeleteDAOImpl;
import vada.handler.CommandHandler;

// 신고글 삭제 핸들러
public class NotifyDeleteProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		// 삭제할 게시물 productnum
		int notifyid = Integer.parseInt(request.getParameter("notifyid") == null ? "" : (String) request.getParameter("notifyid"));

		// 게시물 데이터 삭제
		try {
			new BoardDeleteDAOImpl().deleteNotify(notifyid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/adminmanagenotifyform.do";

	} // process

} // NotifyDeleteProcHandler