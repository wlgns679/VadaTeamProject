package vada.dao.board.img;

import vada.dto.ImgDTO;
import vada.dto.NotifyimgDTO;
import vada.service.board.img.BoardImgService;

public interface BoardImgWriteDAO extends BoardImgService {

	public abstract int writeBoardImg(int imgproductnum, ImgDTO boardImgDTO) throws Exception;

	public abstract int notifyWriteBoardImg(int notifyid, NotifyimgDTO notifyImgDTO) throws Exception; 
	
}
