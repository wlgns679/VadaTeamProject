package vada.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vada.handler.CommandHandler;

public class MVCController extends HttpServlet {

	Map<String, Object> commandMap = null;

	public void init() throws ServletException {
		String commandFile = getInitParameter("commandFile");
		Properties prop = new Properties();
		String commandFilePath = getServletContext().getRealPath(commandFile);
		FileReader fr = null;
		try {
			fr = new FileReader(commandFilePath);
			prop.load(fr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Iterator it = prop.keySet().iterator();

		commandMap = new HashMap<String, Object>();

		while (it.hasNext()) {
			String command = (String) it.next();
			String handlerClassStr = prop.getProperty(command);
			try {
				Class handlerClass = Class.forName(handlerClassStr);
				CommandHandler handlerObj = (CommandHandler) handlerClass.newInstance();
				commandMap.put(command, handlerObj);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} // while

	} // int()

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String command = req.getRequestURI();
		if (command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length() + 1);
		}
		CommandHandler handler = (CommandHandler) commandMap.get(command);
		String viewPage = null;
		try {
			viewPage = handler.process(req, resp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (viewPage != null) {
			System.out.println("@@@@@@@@@@"+viewPage);
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		}
	}
} // class
