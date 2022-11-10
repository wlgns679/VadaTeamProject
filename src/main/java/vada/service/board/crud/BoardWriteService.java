package vada.service.board.crud;

import vada.dto.BoardDTO;
import vada.dto.NotifylistDTO;

public interface BoardWriteService extends BoardCRUDService {

	public abstract int writeBoard(BoardDTO boardDTO) throws Exception;

	public abstract int writePrice(int productnum, int productprice) throws Exception;

	public abstract int notifyWriteBoard(NotifylistDTO notifyDTO, int notifyProductNum, String userid) throws Exception;

	public abstract int get_Notifyid() throws Exception;

}
