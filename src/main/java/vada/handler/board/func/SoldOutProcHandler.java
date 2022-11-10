package vada.handler.board.func;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.func.SoldOutDAOImpl;
import vada.handler.CommandHandler;
import vada.service.board.func.SoldOutService;

// 판매완료 처리 핸들러
public class SoldOutProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		String productnum = request.getParameter("productnum")==null? "" : request.getParameter("productnum");;
		// 예약중일때만 판매완료 처리 되기 위해서 예약중인지 확인하기 위한 변수
		String reserveid = request.getParameter("reserveid")==null? "" : request.getParameter("reserveid");
		
		SoldOutService soldOutService = new SoldOutDAOImpl();
		
		int result = 0;
		try {
			// 판매완료 처리
			result = soldOutService.soldOut(reserveid , Integer.parseInt(productnum));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/boarddetailform.do";
		
	} // process

} // SoldOutProcHandler
