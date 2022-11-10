package vada.handler.board.func;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.board.crud.BoardDetailDAOImpl;
import vada.handler.CommandHandler;

// 예약처리 핸들러
public class ReserveprocHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		int productnum = Integer.parseInt(request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum"));
		
		String userid = (String) session.getAttribute("userid");
		String command = request.getParameter("command") == null ? "" : request.getParameter("command");

		try {
			// 제품예약인지 예약취소인지 확인하는 command, 게시글번호(제품번호)에 해당하는 예약자에 userid 저장
			int result = new BoardDetailDAOImpl().reserveBoard(productnum, command, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/boarddetailform.do";
	} // process

} // ReserveprocHandler