package vada.service.board.crud;

public interface BoardDeleteService extends BoardCRUDService {

	public abstract int deleteBoard(int productnum) throws Exception;

	public abstract int deleteNotify(int notifyid) throws Exception;
	
}
