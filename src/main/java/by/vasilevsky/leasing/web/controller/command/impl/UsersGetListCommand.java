package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.PageMapping;

public class UsersGetListCommand implements Command {
	private static final String USERS_ALIAS = "users";

	private final UserService userService;

	public UsersGetListCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userService.findAll();
		request.setAttribute(USERS_ALIAS, users);

		request.getRequestDispatcher(PageMapping.USERS).forward(request, response);
	}
}
