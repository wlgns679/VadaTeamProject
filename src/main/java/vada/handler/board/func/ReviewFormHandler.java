package vada.handler.board.func;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.handler.CommandHandler;

// 리뷰 작성 폼 핸들러
public class ReviewFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		return "/jsp/board/func/reviewForm.jsp?productnum=" + request.getParameter("productnum");
	}
 
}
