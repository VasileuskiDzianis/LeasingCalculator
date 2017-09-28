package by.vasilevsky.leasing.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.PageMapping;
import by.vasilevsky.leasing.controller.command.UrlMapping;
import by.vasilevsky.leasing.controller.forms.ProfileFormModel;
import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class ProfileUpdateCommand implements Command {
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileFormModel model = (ProfileFormModel) request.getAttribute(ProfileFormModel.ALIAS);
		if (!model.hasErrors()) {
			UserDetails userDetails = buildUserDetailsFromModel(model);
			userService.updateUserDetails(userDetails);

			response.sendRedirect(UrlMapping.PROFILE);
			
			return;
		}
		request.setAttribute(ProfileFormModel.ALIAS, model);

		request.getRequestDispatcher(PageMapping.PROFILE).forward(request, response);
	}

	private UserDetails buildUserDetailsFromModel(ProfileFormModel model) {
		UserDetails userDetails = new UserDetails();
		userDetails.setId(Integer.parseInt(model.getDetailsId()));
		userDetails.setFirstName(model.getFirstName());
		userDetails.setLastName(model.getLastName());
		userDetails.setAge(Integer.parseInt(model.getAge()));

		return userDetails;
	}
}