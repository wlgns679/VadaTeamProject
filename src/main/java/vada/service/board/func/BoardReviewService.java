package vada.service.board.func;

import vada.dto.BoardDTO;

public interface BoardReviewService extends BoardFuncService {
	
	public abstract int updateBoardReview(BoardDTO boardDTO) throws Exception;

}
