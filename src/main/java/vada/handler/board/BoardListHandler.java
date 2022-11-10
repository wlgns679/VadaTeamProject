package vada.handler.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vada.dao.impl.BoardProductNumDAOImpl;
import vada.dao.impl.board.crud.BoardListDAOImpl;
import vada.handler.CommandHandler;
import vada.service.board.crud.BoardListService;

// 메인 폼 핸들러
public class BoardListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		// 게시글 출력
		BoardListService boardListService = new BoardListDAOImpl();

		List<Map<String, Object>> boardList = null;
		
		
		int cnt = 0;
		try {
			// 모든 게시글 리스트에 저장
			boardList = boardListService.getBoardList();
			cnt = new BoardProductNumDAOImpl().allProductCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 한 페이지에 출력될 글 수
		int pageSize = 9;
		
		// 현 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum ="1";
		}
		
//		pageNum*pageSize-(pageSize-1) ~ pageNum*pageSize
		
		//첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize + 1;
		
		int pageCount = 0;
		int pageBlock = 0;
		int startPage=0;
		int endPage= 0;
		
		if(cnt!=0) {
			// 전체 페이지 수 계산
			pageCount = cnt/pageSize + (cnt%pageSize==0?0:1);
			
			// 한페이지에 보여줄 페이지 출력
			pageBlock = 10;
			
			// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
			startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
			
			// 한 페이지에 보여줄 페이지 블럭 끝 번호 계산
			endPage = startPage + pageBlock-1;
			if(endPage>pageCount) {
				endPage = pageCount;
			}
			
		}
		
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
	
		request.setAttribute("boardList", boardList);

		
		return "/jsp/board/mainForm.jsp";
		
	} // process
	
}
// MainFormHandler