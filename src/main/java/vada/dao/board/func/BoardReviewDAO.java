package vada.dao.board.func;

import vada.dto.BoardDTO;
import vada.service.board.func.BoardReviewService;

public interface BoardReviewDAO extends BoardReviewService {
	
	public abstract int updateBoardReview(BoardDTO boardDTO) throws Exception;

}
