package by.vasilevsky.leasing.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.UrlMapping;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.service.validator.Validator;

public class UsersDeleteCommand implements Command {
	private static final String ID_FOR_DELETION_ALIAS = "user_for_deletion_id";
	
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Validator.validateNumber(request.getParameter(ID_FOR_DELETION_ALIAS))) {
			//Fix it: extra actions
			int userForDeletionId = Integer.parseInt(request.getParameter(ID_FOR_DELETION_ALIAS));
			User userForDeletion = userService.findUserById(userForDeletionId);
			userService.deleteUser(userForDeletion);
		}
		response.sendRedirect(UrlMapping.USERS);
	}
}
