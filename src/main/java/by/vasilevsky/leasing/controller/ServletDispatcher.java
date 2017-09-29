package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.CommandProvider;

public class ServletDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String METHOD_GET = "get";
	private static final String METHOD_POST = "post";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandProvider.getInstance().getCommand(METHOD_GET, request.getServletPath()).execute(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandProvider.getInstance().getCommand(METHOD_POST, request.getServletPath()).execute(request, response);
	}
}
