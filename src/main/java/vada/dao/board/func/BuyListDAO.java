package vada.dao.board.func;

import java.util.List;

import vada.dto.BoardDTO;
import vada.service.board.func.BuyListService;

public interface BuyListDAO extends BuyListService {
	
	public abstract List<BoardDTO> buyList(String userid) throws Exception; 
	public List<BoardDTO> sellList(String userid) throws Exception;

}
