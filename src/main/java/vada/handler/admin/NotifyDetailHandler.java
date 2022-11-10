package vada.handler.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.crud.BoardDetailDAOImpl;
import vada.dto.NotifylistDTO;
import vada.handler.CommandHandler;
import vada.service.board.crud.BoardDetailService;

// 신고글 세부정보 폼 핸들러
public class NotifyDetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		int notifyid = Integer.parseInt(request.getParameter("notifyid") == null ? "" : (String) request.getParameter("notifyid"));

		BoardDetailService notifyDetailService = new BoardDetailDAOImpl();

		Map<String, Object> map = null;
		try {
			map = notifyDetailService.notifyView(notifyid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		NotifylistDTO notifylistDTO = new NotifylistDTO();

		notifylistDTO = (NotifylistDTO) map.get("notifylistDTO");
		List list = (List) map.get("imglist");

		request.setAttribute("notifylistDTO", notifylistDTO);
		request.setAttribute("imglist", list);

		return "/jsp/admin/notifyDetail.jsp";
	} // process

} // NotifyDetailHandler