package vada.service.board.func;

import java.util.List;

import vada.dto.KtuserchatroomDTO;

public interface ChatService extends BoardFuncService {

	public abstract int ktchatBoard(int productnum, KtuserchatroomDTO ktuserchatroomDTO) throws Exception;

	public abstract List<KtuserchatroomDTO> ktchatroomList(String ktuserid) throws Exception;
}
