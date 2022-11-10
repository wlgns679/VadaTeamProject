package vada.dao.board.func;

import java.util.List;
import java.util.Map;

import vada.service.board.func.BoardSearchListService;

public interface BoardSearchListDAO extends BoardSearchListService {
   
   public abstract List<Map<String, Object>> searchBoard(String level1Category, String level2Category, String searchText)throws Exception;
   
   public List<Map<String, Object>> searchCateBoard(int categoryNum) throws Exception;
   
}