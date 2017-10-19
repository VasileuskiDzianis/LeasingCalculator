package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.exception.ServiceException;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.util.Validator;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.CommandException;
import by.vasilevsky.leasing.web.controller.command.UrlMapping;

public class UsersDeleteCommand implements Command {
	private static final String ID_FOR_DELETION_ALIAS = "user_for_deletion_id";

	private final UserService userService;

	public UsersDeleteCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Validator.validateNumber(request.getParameter(ID_FOR_DELETION_ALIAS))) {
			int userForDeletionId = Integer.parseInt(request.getParameter(ID_FOR_DELETION_ALIAS));
			try {
				userService.deleteUserById(userForDeletionId);
			} catch (ServiceException e) {
				throw new CommandException("User deleting error", e);
			}
		}
		response.sendRedirect(UrlMapping.USERS);
	}
}
