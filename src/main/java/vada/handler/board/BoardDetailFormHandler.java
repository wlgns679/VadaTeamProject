package vada.handler.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.crud.BoardDetailDAOImpl;
import vada.dto.BoardDTO;
import vada.dto.CategoryDTO;
import vada.dto.ProductpriceDTO;
import vada.handler.CommandHandler;
import vada.service.board.crud.BoardDetailService;

// 게시글 세부정보 획득 핸들러
public class BoardDetailFormHandler implements CommandHandler {

	public String process(HttpServletRequest request, HttpServletResponse response) {

		String productnum = (String) request.getParameter("productnum") == null ? "" : (String) request.getParameter("productnum");

		ProductpriceDTO productpriceDTO = new ProductpriceDTO();
		BoardDTO boardDTO = new BoardDTO();
		CategoryDTO categoryDTO = new CategoryDTO();

		BoardDetailService boardDetailService = new BoardDetailDAOImpl();

		Map<String, Object> boardMap = null;

		try {
			// 여러 객체의 데이터들이 담긴 게시글 세부정보 획득
			boardMap = boardDetailService.getBoardList(Integer.parseInt(productnum));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 맵의 데이터에서 각 해당 객체에 해당하는 데이터를 담음
		boardDTO = (BoardDTO) boardMap.get("boardDTO");
		productpriceDTO = (ProductpriceDTO) boardMap.get("ProductpriceDTO");
		categoryDTO = (CategoryDTO) boardMap.get("categoryDTO");

		List imgDTOList = (List) boardMap.get("imgDTOList");

		// 기본 값은 판매중으로 텍스트 표시
		String reserveText = "판매중";
		
		// 구매자아이디가 있다면
		if (!boardDTO.getBuyerid().equals("default")) {
			reserveText = "판매완료";
		// 구매자 아이디가 없다면
		} else {
			// 예약중이라면
			if (boardDTO.getReservation().equals("yes")) {
				reserveText = "판매예약";
			// 예약중이 아니라면
			} else {
				reserveText = "판매중";
			}
		}
		
		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("reserveText", reserveText);
		request.setAttribute("productpriceDTO", productpriceDTO);
		request.setAttribute("categoryDTO", categoryDTO);
		request.setAttribute("imgDTOList", imgDTOList);

		return "/jsp/board/boardDetailForm.jsp";
		
	} // process
} // BoardDetailFormHandler
