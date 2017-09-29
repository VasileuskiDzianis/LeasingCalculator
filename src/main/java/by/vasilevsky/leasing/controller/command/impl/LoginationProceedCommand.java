package by.vasilevsky.leasing.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.PageMapping;
import by.vasilevsky.leasing.controller.command.UrlMapping;
import by.vasilevsky.leasing.controller.forms.LoginationFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.filter.binder.LoginationFormMapping;
import by.vasilevsky.leasing.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.filter.security.ProfileAccessFilter;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.service.user.UserService;

public class LoginationProceedCommand implements Command {
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final LoginationService loginationService = serviceFactory.getLoginationService();
	private final UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		LoginationFormModel model = (LoginationFormModel) request.getAttribute(LoginationFormMapping.ALIAS);
		if (!model.hasErrors()) {
			UserRole userRole = loginationService.authenticateUser(model.getLogin(), model.getPassword());
			if (!userRole.equals(UserRole.ANONYMOUS)) {
				User user = userService.findUserByLogin(model.getLogin());
				request.getSession().setAttribute(ProfileAccessFilter.USER_ROLE_ALIAS, userRole.toString());
				request.getSession().setAttribute(ProfileAccessFilter.USER_ID_ALIAS, Integer.toString(user.getId()));
				
				response.sendRedirect(UrlMapping.HOME);
				return;
			}
		}
		model.setMainMessage(messages.getString(MessageMapping.INCORRECT_LOGIN_OR_PSW));
		request.setAttribute(LoginationFormMapping.ALIAS, model);
		
		request.getRequestDispatcher(PageMapping.LOGINATION).forward(request, response);
	}
}
