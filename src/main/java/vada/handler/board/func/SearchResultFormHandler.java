package vada.handler.board.func;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.func.BoardSearchListDAOImpl;
import vada.handler.CommandHandler;

// 검색결과를 보여주는 검색 결과 폼 핸들러
public class SearchResultFormHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String cate1 = request.getParameter("categories1") == null ? "" : request.getParameter("categories1");
		String cate2 = request.getParameter("categories2") == null ? "" : request.getParameter("categories2");
		String searchText = request.getParameter("searchText") == null ? "" : request.getParameter("searchText");

		List<Map<String, Object>> list = null;
		try {
			// 상/하위 카테고리 및 검색어 키워드에 맞는 데이터를 list에 저장
			list = new BoardSearchListDAOImpl().searchBoard(cate1, cate2, searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}// 사용자가 입력한 검색어에 해당하는 결과를 가진 리스트
		
		System.out.println("@@@@@@@@@@@@listsize"+list.size());
		
		int cnt = 0;
		try {
			cnt = list.size();
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
		
		
		request.setAttribute("cate1", cate1);
		request.setAttribute("cate2", cate2);
		request.setAttribute("searchText", searchText);
		request.setAttribute("list", list); 
		return "/jsp/board/func/searchResultForm.jsp";
	} // process

} // SearchResultFormHandler
