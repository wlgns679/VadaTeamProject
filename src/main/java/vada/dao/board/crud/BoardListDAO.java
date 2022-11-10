package vada.dao.board.crud;

import java.util.List;
import java.util.Map;

import vada.dto.NotifylistDTO;
import vada.service.board.crud.BoardListService;

public interface BoardListDAO extends BoardListService {
	
	public abstract List<Map<String, Object>> getBoardList() throws Exception;	
	
	public abstract List<NotifylistDTO> notifyListBoard() throws Exception;

}
