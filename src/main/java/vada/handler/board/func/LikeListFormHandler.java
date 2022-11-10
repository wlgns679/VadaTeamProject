package vada.handler.board.func;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.board.func.LikeListDAOImpl;
import vada.handler.CommandHandler;
import vada.service.board.func.LikeService;

// 내 찜목록 리스트를 보여주기 위한 핸들러
public class LikeListFormHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		   String userid= (String)session.getAttribute("userid");   
		   
		   LikeService likeService = new LikeListDAOImpl();
		   
		   // DB에서 내 찜목록 받아와서 list에 저장
		   List<Map<String, Object>> likeList = likeService.likeList(userid);
		   
		   request.setAttribute("likeList", likeList);
		
		return "/jsp/board/func/likeListForm.jsp";
		
	} // process

} // LikeListFormHandler
