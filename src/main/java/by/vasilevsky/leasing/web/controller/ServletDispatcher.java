package by.vasilevsky.leasing.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.web.MethodType;
import by.vasilevsky.leasing.web.controller.command.CommandProvider;

public class ServletDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandProvider.getInstance()
						.getCommand(MethodType.GET.name(), request.getServletPath())
						.execute(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandProvider.getInstance()
						.getCommand(MethodType.POST.name(), request.getServletPath())
						.execute(request, response);
	}
}
