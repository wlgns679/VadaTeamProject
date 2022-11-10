package vada.handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.crud.BoardDeleteDAOImpl;
import vada.handler.CommandHandler;

// 게시글 삭제 처리 핸들러
public class BoardDeleteProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		int productnum = Integer.parseInt((String) request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum"));
		
		try {
			// 해당 게시글번호(제품번호)에 해당하는 게시글 삭제
			new BoardDeleteDAOImpl().deleteBoard(productnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/mainform.do";
		
	}

}
