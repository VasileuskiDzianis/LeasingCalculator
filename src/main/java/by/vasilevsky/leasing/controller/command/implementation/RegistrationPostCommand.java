package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.forms.RegistrationFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.registration.RegistrationService;

public class RegistrationPostCommand implements Command {
	private static volatile RegistrationPostCommand instance;
	private final ServiceFactory serviceFactory = ServiceFactoryImpl.getInstance();
	private final RegistrationService registrationService = serviceFactory.getRegistrationService();

	private RegistrationPostCommand() {

	}

	public static Command getInstance() {
		RegistrationPostCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (RegistrationPostCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new RegistrationPostCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		RegistrationFormModel model = (RegistrationFormModel) request.getAttribute("registrationFormModel");
		if (model.getLogin() != null && registrationService.isLoginExisting(model.getLogin())) {
			model.setLoginMessage("Адрес занят");
			model.setErrors(true);
		}
		if (model.hasErrors()) {
			request.setAttribute("registrationFormModel", model);
			
			return "registration.tiles";
		} else {
			User user = new User();
			user.setLogin(model.getLogin());
			user.setPassword(model.getFirstPassword());
			user.setUserRole(UserRole.USER);
			UserDetails userDetails = new UserDetails();
			userDetails.setAge(0);
			userDetails.setFirstName("");
			userDetails.setLastName("");
			user.setUserDetails(userDetails);

			registrationService.registerNewUser(user);
			model.setMainMessage("Зарегистрирован");

			request.setAttribute("registrationFormModel", model);
			
			return "registration.tiles";
		}
	}
}
