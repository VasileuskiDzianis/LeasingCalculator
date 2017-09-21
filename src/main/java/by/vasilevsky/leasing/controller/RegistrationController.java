package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.registration.RegistrationService;
import by.vasilevsky.leasing.view.RegistrationFormModel;

@WebServlet(urlPatterns = { "/registration" })
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 8780315513488014013L;

	private ServiceFactory serviceFactory;
	private RegistrationService registrationService;

	@Override
	public void init() {
		serviceFactory = new ServiceFactoryImpl();
		registrationService = serviceFactory.getRegistrationService();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("registration.tiles");
		view.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationFormModel model = (RegistrationFormModel) request.getAttribute("registrationFormModel");
		if (model.getLogin() != null && registrationService.isLoginExisting(model.getLogin())) {
			model.setLoginMessage("Адрес занят");
			model.setErrors(true);
		}
		if (model.hasErrors()) {
			request.setAttribute("registrationFormModel", model);
			RequestDispatcher view = request.getRequestDispatcher("registration.tiles");
			view.forward(request, response);
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
			RequestDispatcher view = request.getRequestDispatcher("registration.tiles");
			view.forward(request, response);
		}
	}
}
