package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.CommandProvider;

public class ServletDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SPLITTER = ":";
	private static final String	REDIRECTION_ALIAS = "redirect";
	private static final int ALIAS_INDEX = 0;
	private static final int PATH_INDEX = 1;
	
	private CommandProvider commandProvider = CommandProvider.getInstance();
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder requestMapping = new StringBuilder();
		requestMapping.append(request.getMethod());
		requestMapping.append(SPLITTER);
		requestMapping.append(request.getServletPath());
		
		Command command = commandProvider.getCommand(requestMapping.toString());
		String responsePath = command.execute(request, response);
		
		if (isRedirection(responsePath)) {
			response.sendRedirect(getRedirectionPath(responsePath));
			
			return;
		}
		RequestDispatcher view = request.getRequestDispatcher(responsePath);
		view.forward(request, response);		
	}


	private String getRedirectionPath(String responsePath) {
		String[] splitedPath = responsePath.split(SPLITTER);
		
		return splitedPath[PATH_INDEX];
	}


	private boolean isRedirection(String responsePath) {
		String[] splitedPath = responsePath.split(SPLITTER);
		
		return splitedPath[ALIAS_INDEX].equals(REDIRECTION_ALIAS);
	}
}
