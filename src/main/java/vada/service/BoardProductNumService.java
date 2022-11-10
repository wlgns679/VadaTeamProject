package vada.service;

import vada.service.board.BoardService;

public interface BoardProductNumService extends BoardService {
	
	public abstract int getProductNum() throws Exception;

	public int allProductCount() throws Exception;
}
