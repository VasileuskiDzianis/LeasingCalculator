package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.forms.LoginationFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.service.user.UserService;

public class LoginationPostCommand implements Command {
	private static volatile LoginationPostCommand instance;
	
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private LoginationService loginationService = serviceFactory.getLoginationService();
	private UserService userService = serviceFactory.getUserService();
	
	private LoginationPostCommand() {
		
	}
	
	public static Command getInstance() {
		LoginationPostCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (LoginationPostCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LoginationPostCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LoginationFormModel model = (LoginationFormModel) request.getAttribute("loginationFormModel");
		if (!model.hasErrors()) {
			UserRole userRole = loginationService.authenticateUser(model.getLogin(), model.getPassword());
			if (!userRole.equals(UserRole.ANONYMOUS)) {
				User user = userService.findUserByLogin(model.getLogin());
				request.getSession().setAttribute("userRole", userRole.toString());
				request.getSession().setAttribute("userId", Integer.toString(user.getId()));
				
				return "redirect:home";
			}
		}
		model.setMainMessage("Не верные логин или пароль");
		request.setAttribute("loginationFormModel", model);
		
		return "logination.tiles";
	}
}
