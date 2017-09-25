package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.forms.LoginationFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.domain.user.UserRole;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.logination.LoginationService;
import by.vasilevsky.leasing.service.user.UserService;

@WebServlet(urlPatterns = { "/logination" })
public class LoginationController extends HttpServlet {
	private static final long serialVersionUID = 8780315513488014012L;

	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private LoginationService loginationService = serviceFactory.getLoginationService();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("logination.tiles");
		view.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginationFormModel model = (LoginationFormModel) request.getAttribute("loginationFormModel");
		if (!model.hasErrors()) {
			UserRole userRole = loginationService.authenticateUser(model.getLogin(), model.getPassword());
			if (!userRole.equals(UserRole.ANONYMOUS)) {
				User user = userService.findUserByLogin(model.getLogin());

				request.getSession().setAttribute("userRole", userRole.toString());
				request.getSession().setAttribute("userId", Integer.toString(user.getId()));
				response.sendRedirect("home");
				return;
			}
		}
		model.setMainMessage("Не верные логин или пароль");
		request.setAttribute("loginationFormModel", model);
		RequestDispatcher view = request.getRequestDispatcher("logination.tiles");
		view.forward(request, response);
	}
}
