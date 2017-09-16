package by.vasilevsky.leasing.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.view.ProfileFormModel;

import java.io.IOException;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = -6602582281415954285L;
	
	private ServiceFactory serviceFactory = new ServiceFactoryImpl();
	private UserService userService = serviceFactory.getUserService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
		User user = userService.findUserById(userId);
		ProfileFormModel formModel = new ProfileFormModel();
		
		formModel.setUserId(Integer.toString(userId));
		formModel.setDetailsId(Integer.toString(user.getUserDetails().getId()));
		formModel.setFirstName(user.getUserDetails().getFirstName());
		formModel.setLastName(user.getUserDetails().getLastName());
		formModel.setAge(Integer.toString(user.getUserDetails().getAge()));
		
		request.setAttribute("profileFormModel", formModel);
		RequestDispatcher view = request.getRequestDispatcher("profile.tiles");
		view.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileFormModel formModel = new ProfileFormModel();
		formModel.setUserId(request.getParameter("userId"));
		formModel.setDetailsId(request.getParameter("detailsId"));
		formModel.setFirstName(request.getParameter("firstName"));
		formModel.setLastName(request.getParameter("lastName"));
		formModel.setAge(request.getParameter("age"));
		
		User user = userService.findUserById(Integer.parseInt(formModel.getUserId()));
		user.getUserDetails().setFirstName(formModel.getFirstName());
		user.getUserDetails().setLastName(formModel.getLastName());
		user.getUserDetails().setAge(Integer.parseInt(formModel.getAge()));
		
		userService.updateUser(user);
		
		response.sendRedirect("profile");
	}
}
