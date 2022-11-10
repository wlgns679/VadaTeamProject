package vada.service.board.crud;

import java.util.List;
import java.util.Map;

import vada.dto.NotifylistDTO;

public interface BoardListService extends BoardCRUDService {

	public abstract List<Map<String, Object>> getBoardList() throws Exception;

	public abstract List<NotifylistDTO> notifyListBoard() throws Exception;

}
