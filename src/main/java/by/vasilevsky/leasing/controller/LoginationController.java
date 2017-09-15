package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.view.LoginationFormModel;

@WebServlet(urlPatterns = { "/logination" })
public class LoginationController extends HttpServlet {
	private static final long serialVersionUID = 8780315513488014012L;

	private ServiceFactory serviceFactory = new ServiceFactoryImpl();
	private LoginationService loginationService = serviceFactory.getLoginationService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = request.getRequestDispatcher("logination.tiles");
		view.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginationFormModel formModel = new LoginationFormModel();
		UserRole userRole;

		formModel.setLogin(request.getParameter("login"));
		formModel.setPassword(request.getParameter("password"));

		checkLoginationFormModel(formModel);

		if (formModel.isErrorsExist()) {

			request.setAttribute("loginationFormModel", formModel);
			RequestDispatcher view = request.getRequestDispatcher("calculator.tiles");
			view.forward(request, response);
		} else {

			userRole = loginationService.authenticateUser(formModel.getLogin(), formModel.getPassword());
			if (userRole.equals(UserRole.ANONYMOUS)) {
				formModel.setMainMessage("Не верные логин или пароль");

				request.getSession().setAttribute("userRole", userRole.toString());
				request.setAttribute("loginationFormModel", formModel);
				RequestDispatcher view = request.getRequestDispatcher("calculator.tiles");
				view.forward(request, response);
			} else {

				request.getSession().setAttribute("userRole", userRole.toString());
				
				response.sendRedirect("home");
			}
		}
	}

	private void checkLoginationFormModel(LoginationFormModel formModel) {
		if (formModel.getLogin() == null || formModel.getLogin().length() == 0) {
			formModel.setMainMessage("Не верные логин или пароль");
			formModel.setErrorsExist(true);
		}
		if (formModel.getPassword() == null || formModel.getPassword().length() == 0) {
			formModel.setMainMessage("Не верные логин или пароль");
			formModel.setErrorsExist(true);
		}
	}
}
