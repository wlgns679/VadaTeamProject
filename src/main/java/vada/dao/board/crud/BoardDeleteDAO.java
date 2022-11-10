package vada.dao.board.crud;

import vada.service.board.crud.BoardDeleteService;

public interface BoardDeleteDAO extends BoardDeleteService {
	
	public abstract int deleteBoard(int productnum) throws Exception;

	public abstract int deleteNotify(int notifyid) throws Exception;
}
