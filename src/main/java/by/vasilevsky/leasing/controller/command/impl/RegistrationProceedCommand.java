package by.vasilevsky.leasing.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.PageMapping;
import by.vasilevsky.leasing.controller.forms.RegistrationFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.filter.binder.RegistrationFormMapping;
import by.vasilevsky.leasing.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.registration.RegistrationService;

public class RegistrationProceedCommand implements Command {
	private static final String DEFAULT_USER_NAME = "unknown";
	private static final String DEFAULT_USER_SURNAME = "unknown";
	private static final int DEFAULT_USER_AGE = 18;
	
	private final ServiceFactory serviceFactory = ServiceFactoryImpl.getInstance();
	private final RegistrationService registrationService = serviceFactory.getRegistrationService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		RegistrationFormModel model = (RegistrationFormModel) request.getAttribute(RegistrationFormMapping.ALIAS);
		if (model.getLogin() != null && registrationService.isLoginExisting(model.getLogin())) {
			model.setLoginMessage(messages.getString(MessageMapping.ADDRESS_IN_USE_MESSAGE));
			model.setErrors(true);
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
			registrationService.registerNewUser(user);
			model.setMainMessage(messages.getString(MessageMapping.REGISTERED_MESSAGE));
		}
		request.getRequestDispatcher(PageMapping.REGISTRATION).forward(request, response);
	}
}
