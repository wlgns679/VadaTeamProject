package vada.service.board.img;

import java.util.List;

import vada.dto.ImgDTO;
import vada.dto.NotifyimgDTO;
import vada.service.board.BoardService;

public interface BoardImgService extends BoardService {	
	
	public abstract List<ImgDTO> getBoardImgList(int productnum) throws Exception;

	public abstract ImgDTO viewBoardImg(int imgnum) throws Exception;

	public abstract int writeBoardImg(int imgproductnum, ImgDTO boardImgDTO) throws Exception;

	public abstract int deleteBoardImg(int imgproductnum) throws Exception;
	
	public abstract int updateBoardImg(int userid, List<ImgDTO> list) throws Exception;
	
	public abstract int notifyWriteBoardImg(int notifyid, NotifyimgDTO notifyImgDTO) throws Exception;

}
