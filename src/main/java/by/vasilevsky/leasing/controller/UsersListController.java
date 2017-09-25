package by.vasilevsky.leasing.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.service.validator.Validator;

@WebServlet(urlPatterns = { "/users" })
public class UsersListController extends HttpServlet {
	private static final long serialVersionUID = -6602582281415954285L;

	private ServiceFactory serviceFactory;
	private UserService userService;
	
	@Override
	public void init() {
		serviceFactory = ServiceFactory.getInstance();
		userService = serviceFactory.getUserService();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userService.findAll();

		request.setAttribute("users", users);
		RequestDispatcher view = request.getRequestDispatcher("users.tiles");
		view.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Validator.validateNumber(request.getParameter("user_for_deletion_id"))) {
			int userForDeletionId = Integer.parseInt(request.getParameter("user_for_deletion_id"));
			User userForDeletion = userService.findUserById(userForDeletionId);
			userService.deleteUser(userForDeletion);
		}
		response.sendRedirect("users");
	}
}
