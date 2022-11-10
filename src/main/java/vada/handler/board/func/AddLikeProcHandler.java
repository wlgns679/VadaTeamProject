package vada.handler.board.func;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vada.dao.impl.board.func.LikeAddDAOImpl;
import vada.dao.impl.board.func.LikeCheckDAOImpl;
import vada.handler.CommandHandler;
import vada.service.board.func.LikeService;

// 찜목록 추가 핸들러
public class AddLikeProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		// 찜목록에 추가할 제품번호(게시글 번호) 를 받아옴
		int productnum = Integer.parseInt((String) request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum"));
		
		// 현재 사용중인 아이디를 userid에 저장
		String userid = (String) session.getAttribute("userid");

		LikeService likeService = new LikeCheckDAOImpl();
		List list = null;

		try {
			// 중복된 찜목록이 있는지 체크하기 위해 user의 찜목록을 list에 담아옴
			list = likeService.likeCheck(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean add = true; // 1

		for (Object a : list) {
			// 중복된 찜목록 게시글이 존재한다면
			if ((int) a == productnum) {
				// 찜목록을 추가 하지 않도록 지정
				add = false; // 0
			} 
		}
		
		// 중복된 찜목록이 없다면
		if (add) {
			try {
				// 찜목록 추가
				int result = new LikeAddDAOImpl().likeAdd(userid, productnum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "/likelistform.do";
	} // process

} // AddLikeProcHandler
