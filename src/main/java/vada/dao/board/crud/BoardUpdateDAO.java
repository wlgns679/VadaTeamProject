package vada.dao.board.crud;

import vada.dto.BoardDTO;
import vada.dto.CategoryDTO;
import vada.dto.ProductpriceDTO;
import vada.service.board.crud.BoardUpdateService;

public interface BoardUpdateDAO extends BoardUpdateService {

	public abstract int updateBoard(int productnum, BoardDTO boardDTO, ProductpriceDTO productpriceDTO, CategoryDTO categoryDTO) throws Exception; 
	
}
