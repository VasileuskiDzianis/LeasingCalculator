package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.exception.ServiceException;
import by.vasilevsky.leasing.service.login.LoginService;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.CommandException;
import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.controller.command.UrlMapping;
import by.vasilevsky.leasing.web.filter.binder.LoginFormMapping;
import by.vasilevsky.leasing.web.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.web.filter.security.ProfileAccessFilter;
import by.vasilevsky.leasing.web.form.LoginFormModel;

public class LoginProceedCommand implements Command {
	private final LoginService loginService;
	private final UserService userService;

	public LoginProceedCommand() {
		loginService = ServiceFactory.getInstance().getLoginService();
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		LoginFormModel model = (LoginFormModel) request.getAttribute(LoginFormMapping.ALIAS);
		if (!model.hasErrors()) {
			UserRole userRole = null;
			try {
				userRole = loginService.authenticateUser(model.getLogin(), model.getPassword());
			} catch (ServiceException e) {
				throw new CommandException("User authentication error", e);
			}
			if (!userRole.equals(UserRole.ANONYMOUS)) {
				User user = null;
				try {
					user = userService.findUserByLogin(model.getLogin());
				} catch (ServiceException e) {
					throw new CommandException("Finding user by login error", e);
				}
				request.getSession().setAttribute(ProfileAccessFilter.USER_ROLE_ALIAS, userRole.toString());
				request.getSession().setAttribute(ProfileAccessFilter.USER_ID_ALIAS, Integer.toString(user.getId()));

				response.sendRedirect(UrlMapping.HOME);
				
				return;
			}
		}
		
		model.setMainMessage(messages.getString(MessageMapping.INCORRECT_LOGIN_OR_PSW));
		request.setAttribute(LoginFormMapping.ALIAS, model);

		request.getRequestDispatcher(PageMapping.LOGINATION).forward(request, response);
	}
}
