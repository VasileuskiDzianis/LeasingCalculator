package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.exception.ServiceException;
import by.vasilevsky.leasing.service.login.LoginService;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.CommandException;
import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.filter.binder.RegistrationFormMapping;
import by.vasilevsky.leasing.web.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.web.form.RegistrationFormModel;

public class RegistrationProceedCommand implements Command {
	private static final String DEFAULT_USER_NAME = "unknown";
	private static final String DEFAULT_USER_SURNAME = "unknown";
	private static final int DEFAULT_USER_AGE = 18;

	private final LoginService loginService;

	public RegistrationProceedCommand() {
		loginService = ServiceFactory.getInstance().getLoginService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		RegistrationFormModel model = (RegistrationFormModel) request.getAttribute(RegistrationFormMapping.ALIAS);

		try {
			if (model.getLogin() != null && loginService.isLoginExisting(model.getLogin())) {
				model.setLoginMessage(messages.getString(MessageMapping.ADDRESS_IN_USE_MESSAGE));
				model.setErrors(true);
			}
		} catch (ServiceException e) {
			throw new CommandException("User registration error", e);
		}

		if (!model.hasErrors()) {
			User user = new User();
			user.setLogin(model.getLogin());
			user.setPassword(model.getFirstPassword());
			user.setUserRole(UserRole.USER);
			UserDetails userDetails = new UserDetails();
			userDetails.setAge(DEFAULT_USER_AGE);
			userDetails.setFirstName(DEFAULT_USER_NAME);
			userDetails.setLastName(DEFAULT_USER_SURNAME);
			user.setUserDetails(userDetails);

			try {
				loginService.registerNewUser(user);
			} catch (ServiceException e) {
				throw new CommandException("User registration error", e);
			}
			model.setMainMessage(messages.getString(MessageMapping.REGISTERED_MESSAGE));
		}
		request.getRequestDispatcher(PageMapping.REGISTRATION).forward(request, response);
	}
}
