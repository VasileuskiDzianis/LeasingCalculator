package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.service.validator.Validator;

public class UsersPostCommand implements Command {
	private static volatile UsersPostCommand instance;
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final UserService userService = serviceFactory.getUserService();

	private UsersPostCommand() {

	}

	public static Command getInstance() {
		UsersPostCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (UsersPostCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new UsersPostCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if (Validator.validateNumber(request.getParameter("user_for_deletion_id"))) {
			int userForDeletionId = Integer.parseInt(request.getParameter("user_for_deletion_id"));
			User userForDeletion = userService.findUserById(userForDeletionId);
			userService.deleteUser(userForDeletion);
		}
		return "redirect:users";
	}
}
