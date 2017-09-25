package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.forms.ProfileFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

@WebServlet(urlPatterns = "/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = -6602582281415954285L;
	
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
		User user = userService.findUserById(userId);
		ProfileFormModel model = new ProfileFormModel();
		
		model.setUserId(Integer.toString(userId));
		model.setDetailsId(Integer.toString(user.getUserDetails().getId()));
		model.setFirstName(user.getUserDetails().getFirstName());
		model.setLastName(user.getUserDetails().getLastName());
		model.setAge(Integer.toString(user.getUserDetails().getAge()));
		
		request.setAttribute("profileFormModel", model);
		RequestDispatcher view = request.getRequestDispatcher("profile.tiles");
		view.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileFormModel model = (ProfileFormModel) request.getAttribute("profileFormModel");
		if (!model.hasErrors()) {
			User user = userService.findUserById(Integer.parseInt(model.getUserId()));
			user.getUserDetails().setFirstName(model.getFirstName());
			user.getUserDetails().setLastName(model.getLastName());
			user.getUserDetails().setAge(Integer.parseInt(model.getAge()));
			userService.updateUser(user);
			
			response.sendRedirect("profile");
			
			return;
		}
		request.setAttribute("profileFormModel", model);
		RequestDispatcher view = request.getRequestDispatcher("profile.tiles");
		view.forward(request, response);
	}
}
