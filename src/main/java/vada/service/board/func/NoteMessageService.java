package vada.service.board.func;

import java.util.ArrayList;

import vada.dto.NoteMessageDTO;

public interface NoteMessageService extends BoardFuncService {

	public abstract int insertMessage(NoteMessageDTO noteMessageDTO);

	public abstract ArrayList<NoteMessageDTO> showMessage();

}
