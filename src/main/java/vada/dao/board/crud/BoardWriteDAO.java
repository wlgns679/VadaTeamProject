package vada.dao.board.crud;

import vada.dto.BoardDTO;
import vada.dto.NotifylistDTO;
import vada.service.board.crud.BoardWriteService;

public interface BoardWriteDAO extends BoardWriteService {

	public abstract int writeBoard(BoardDTO boardDTO) throws Exception;

	public abstract int writePrice(int productnum, int productprice) throws Exception;

	public abstract int notifyWriteBoard(NotifylistDTO notifyDTO, int notifyProductNum, String userid) throws Exception;

	public abstract int get_Notifyid() throws Exception;

}
