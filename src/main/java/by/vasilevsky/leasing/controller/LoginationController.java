package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.view.LoginationFormModel;

@WebServlet(urlPatterns = { "/logination" })
public class LoginationController extends HttpServlet {
	private static final long serialVersionUID = 8780315513488014012L;

	private ServiceFactory serviceFactory = new ServiceFactoryImpl();
	private LoginationService loginationService = serviceFactory.getLoginationService();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = request.getRequestDispatcher("logination.tiles");
		view.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginationFormModel formModel = new LoginationFormModel();

		formModel.setLogin(request.getParameter("login"));
		formModel.setPassword(request.getParameter("password"));
		checkLoginationFormModel(formModel);

		if (!formModel.isErrorsExist()) {
			UserRole userRole = loginationService.authenticateUser(formModel.getLogin(), formModel.getPassword());
			if (!userRole.equals(UserRole.ANONYMOUS)) {
				User user = userService.findUserByLogin(formModel.getLogin());
				
				request.getSession().setAttribute("userRole", userRole.toString());
				request.getSession().setAttribute("userId", Integer.toString(user.getId()));
				response.sendRedirect("home");
				return;
			}
		}
		formModel.setMainMessage("Не верные логин или пароль");
		request.setAttribute("loginationFormModel", formModel);
		RequestDispatcher view = request.getRequestDispatcher("logination.tiles");
		view.forward(request, response);
	}

	private void checkLoginationFormModel(LoginationFormModel formModel) {
		if (formModel.getLogin() == null || formModel.getPassword() == null || formModel.getLogin().length() == 0
				|| formModel.getPassword().length() == 0) {
			formModel.setMainMessage("Не верные логин или пароль");
			formModel.setErrorsExist(true);
		}
	}
}
