package vada.handler.board.func;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.board.func.BuyListDAOImpl;
import vada.handler.CommandHandler;
import vada.service.board.func.BuyListService;

// 내가 쓴 목록 리스트를 보여주기 위한 핸들러
public class MyBoardListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		BuyListService buyListService = new BuyListDAOImpl();
		
		List list = null;
		
		try {
			// 현재 세션 ID에 해당하는 유저구매목록 데이터를 list에 저장
			list = buyListService.sellList((String) session.getAttribute("userid"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("list", list);
		
		return "/jsp/board/func/myBoardList.jsp";
		
	} // process

} // UserBuyListFormHandler