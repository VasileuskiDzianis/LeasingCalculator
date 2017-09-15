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
import by.vasilevsky.leasing.service.validator.Validator;
import by.vasilevsky.leasing.view.RegistrationFormModel;

@WebServlet(urlPatterns = { "/registration" })
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 8780315513488014013L;
	
	private ServiceFactory serviceFactory = new ServiceFactoryImpl();
	private RegistrationService registrationService = serviceFactory.getRegistrationService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = request.getRequestDispatcher("registration.tiles");
		view.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationFormModel formModel = new RegistrationFormModel();

		formModel.setLogin(request.getParameter("login"));
		formModel.setFirstPassword(request.getParameter("password1"));
		formModel.setSecondPassword(request.getParameter("password2"));

		checkRegistartionFormModel(formModel);

		if (formModel.isErrorsExist()) {
			request.setAttribute("registrationFormModel", formModel);
			RequestDispatcher view = request.getRequestDispatcher("registration.tiles");
			view.forward(request, response);
		} else {
			User user = new User();
			user.setLogin(formModel.getLogin());
			user.setPassword(formModel.getFirstPassword());
			user.setUserRole(UserRole.USER);
			UserDetails userDetails = new UserDetails();
			userDetails.setAge(0);
			userDetails.setFirstName("");
			userDetails.setLastName("");
			user.setUserDetails(userDetails);
			
			registrationService.registerNewUser(user);
			formModel.setMainMessage("Зарегистрирован");
			
			request.setAttribute("registrationFormModel", formModel);
			RequestDispatcher view = request.getRequestDispatcher("registration.tiles");
			view.forward(request, response);
		}
	}

	private void checkRegistartionFormModel(RegistrationFormModel formModel) {
		if (formModel.getFirstPassword() == null) {
			formModel.setFirstPasswordMessage("Поле не может быть пустым");
			formModel.setHasErrors(true);
		}
		if (formModel.getLogin() == null) {
			formModel.setLoginMessage("Поле не может быть пустым");
			formModel.setHasErrors(true);
		}
		if (formModel.getFirstPassword() != null
				&& (!formModel.getFirstPassword().equals(formModel.getSecondPassword()))) {
			formModel.setSecondPasswordMessage("Пароли не совпадают");
			formModel.setHasErrors(true);
		}
		if (!Validator.validatePassword(formModel.getFirstPassword())) {
			formModel.setFirstPasswordMessage("6 букв минимум");
			formModel.setHasErrors(true);
		}
		if (!Validator.validateLogin(formModel.getLogin())) {
			formModel.setLoginMessage("Не корректный адрес");
			formModel.setHasErrors(true);
		}
		if (formModel.getLogin() != null && registrationService.isLoginExisting(formModel.getLogin())) {
			formModel.setLoginMessage("Адрес используется");
			formModel.setHasErrors(true);
		}
	}
}
