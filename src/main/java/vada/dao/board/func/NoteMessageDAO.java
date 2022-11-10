package vada.dao.board.func;

import java.util.ArrayList;

import vada.dto.NoteMessageDTO;
import vada.service.board.func.NoteMessageService;

public interface NoteMessageDAO extends NoteMessageService {
	
	public abstract int insertMessage(NoteMessageDTO noteMessageDTO);
	
	public abstract ArrayList<NoteMessageDTO> showMessage();

}
