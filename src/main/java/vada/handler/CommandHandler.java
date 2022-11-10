package vada.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 리턴된 URL로 폼 이동 처리를 위한 인터페이스
public interface CommandHandler {
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
