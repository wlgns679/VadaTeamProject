package vada.handler.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.admin.ManagerDAOImpl;
import vada.handler.CommandHandler;
import vada.service.board.admin.ManagerService;

// 유저 블랙리스트 처리 핸들러
public class BlackListProcHandler implements CommandHandler {

	public String process(HttpServletRequest request, HttpServletResponse response) {

		// 유저가 몇명인지 확인
		int listsize = Integer.parseInt(request.getParameter("listsize"));

		for (int i = 0; i < listsize; i++) {
			String buyerid = (String) request.getParameter(String.valueOf(i))==null?"":(String) request.getParameter(String.valueOf(i)); 
			String blackyn = (String) request.getParameter("blackyn" + String.valueOf(i))==null?"":(String) request.getParameter("blackyn" + String.valueOf(i)); 

			ManagerService managerService = new ManagerDAOImpl();
			int result = 0;
			try {
				// user테이블에 블랙리스트 여부에 yes 또는 no 로 등록
				result = managerService.blackList(buyerid, blackyn);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "/adminmanageuserform.do";

	}
}
