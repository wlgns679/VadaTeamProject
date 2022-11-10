package vada.dao.board.img;

import java.util.List;

import vada.dto.ImgDTO;
import vada.service.board.img.BoardImgService;

public interface BoardImgListDAO extends BoardImgService {
	
	public abstract List<ImgDTO> listBoardImg(int productnum) throws Exception;
}
