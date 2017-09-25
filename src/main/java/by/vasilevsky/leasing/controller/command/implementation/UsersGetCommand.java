package by.vasilevsky.leasing.controller.command.implementation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class UsersGetCommand implements Command {
	private static volatile UsersGetCommand instance;
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final UserService userService = serviceFactory.getUserService();

	private UsersGetCommand() {

	}

	public static Command getInstance() {
		UsersGetCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (UsersGetCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new UsersGetCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = userService.findAll();
		request.setAttribute("users", users);

		return "users.tiles";
	}
}
