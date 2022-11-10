package vada.handler.board.func;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.func.NoteMessageDAOImpl;
import vada.dto.NoteMessageDTO;
import vada.handler.CommandHandler;

// 쪽지 리스트 출력 핸들러
public class NoteMessageListFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		NoteMessageDAOImpl noteMessageDAOImpl = new NoteMessageDAOImpl();
		List<NoteMessageDTO> listmessage = new ArrayList<NoteMessageDTO>();

		NoteMessageDTO noteMessageDTO = new NoteMessageDTO();
		
		// 쪽지목록 listmessage 리스트에 저장
		listmessage = noteMessageDAOImpl.showMessage();		
		
		request.setAttribute("listmessage", listmessage);
		
		return "/jsp/board/func/noteMessageListForm.jsp";
		
	} // process

} // NoteMessageListFormHandler
