package by.vasilevsky.leasing.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.PageMapping;
import by.vasilevsky.leasing.controller.command.UrlMapping;
import by.vasilevsky.leasing.controller.forms.ProfileFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class ProfileUpdateCommand implements Command {
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileFormModel model = (ProfileFormModel) request.getAttribute(ProfileFormModel.ALIAS);
		if (!model.hasErrors()) {
			
			//fix it: it doesn't need to find by id
			User user = userService.findUserById(Integer.parseInt(model.getUserId()));
			user.getUserDetails().setFirstName(model.getFirstName());
			user.getUserDetails().setLastName(model.getLastName());
			user.getUserDetails().setAge(Integer.parseInt(model.getAge()));
			userService.updateUser(user);
			
			response.sendRedirect(UrlMapping.PROFILE);
			return;
		}
		request.setAttribute(ProfileFormModel.ALIAS, model);

		request.getRequestDispatcher(PageMapping.PROFILE).forward(request, response);
	}
}
