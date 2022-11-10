package vada.handler.board.func;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.func.BoardReviewDAOImpl;
import vada.dto.BoardDTO;
import vada.handler.CommandHandler;
import vada.service.board.func.BoardReviewService;

// 리뷰 처리 핸들러
public class ReviewProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		BoardReviewService boardReviewService = new BoardReviewDAOImpl();
		
		BoardDTO boardDTO = new BoardDTO();
		
		int productnum = Integer.parseInt(request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum"));
		 
		// 리뷰내용
		boardDTO.setReview(request.getParameter("review"));
		// 리뷰점수
		boardDTO.setReviewscore(Integer.parseInt(request.getParameter("reviewscore")));
		// 게시글번호(제품번호)
		boardDTO.setProductnum(productnum);
		
		int result = 0;
		try {
			// 리뷰 작성
			result = boardReviewService.updateBoardReview(boardDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/boarddetailform.do?productnum="+productnum;
	} // process

} // ReviewProcHandler
