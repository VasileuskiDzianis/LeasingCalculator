package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.UserDetails;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.exception.ServiceException;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.CommandException;
import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.controller.command.UrlMapping;
import by.vasilevsky.leasing.web.filter.binder.ProfileFormMapping;
import by.vasilevsky.leasing.web.form.ProfileFormModel;

public class ProfileUpdateCommand implements Command {
	private final UserService userService;

	public ProfileUpdateCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileFormModel model = (ProfileFormModel) request.getAttribute(ProfileFormMapping.ALIAS);
		if (!model.hasErrors()) {
			UserDetails userDetails = buildUserDetailsFromModel(model);
			try {
				userService.updateUserDetails(userDetails);
			} catch (ServiceException e) {
				throw new CommandException("User profile updating error", e);
			}
			response.sendRedirect(UrlMapping.PROFILE);
			
			return;
		}
		
		request.setAttribute(ProfileFormMapping.ALIAS, model);
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