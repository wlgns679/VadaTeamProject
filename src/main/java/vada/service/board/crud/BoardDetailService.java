package vada.service.board.crud;

import java.util.Map;

public interface BoardDetailService extends BoardCRUDService {

	public abstract Map<String, Object> getBoardList(int bid) throws Exception;

	public abstract Map<String, Object> notifyView(int notifyid) throws Exception;

	public abstract int reserveBoard(int productnum, String command, String userid) throws Exception;

}