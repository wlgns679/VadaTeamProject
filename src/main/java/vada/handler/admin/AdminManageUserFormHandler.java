package vada.handler.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.dao.impl.board.admin.ManagerDAOImpl;
import vada.dto.UserDTO;
import vada.handler.CommandHandler;
import vada.service.board.admin.ManagerService;

// 관리자만 사용가능한 회원정보관리 폼 핸들러
public class AdminManageUserFormHandler implements CommandHandler {

	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ManagerService managerService = new ManagerDAOImpl();
		List<UserDTO> list = null;
		try {
			// 유저정보 리스트를 받음
			list = managerService.listBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		
		return "/jsp/admin/adminManageUserForm.jsp";
	}
}
