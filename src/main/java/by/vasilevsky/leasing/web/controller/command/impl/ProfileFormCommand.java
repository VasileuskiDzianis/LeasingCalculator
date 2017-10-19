package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.exception.ServiceException;
import by.vasilevsky.leasing.service.user.UserService;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.CommandException;
import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.filter.binder.ProfileFormMapping;
import by.vasilevsky.leasing.web.filter.security.ProfileAccessFilter;
import by.vasilevsky.leasing.web.form.ProfileFormModel;

public class ProfileFormCommand implements Command {
	private final UserService userService;

	public ProfileFormCommand() {
		userService = ServiceFactory.getInstance().getUserService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt((String) request.getSession().getAttribute(ProfileAccessFilter.USER_ID_ALIAS));
		User user;
		try {
			user = userService.findUserById(userId);
		} catch (ServiceException e) {
			throw new CommandException("Finding user by id error", e);
		}
		ProfileFormModel model = new ProfileFormModel();
		model.setUserId(Integer.toString(userId));
		model.setDetailsId(Integer.toString(user.getUserDetails().getId()));
		model.setFirstName(user.getUserDetails().getFirstName());
		model.setLastName(user.getUserDetails().getLastName());
		model.setAge(Integer.toString(user.getUserDetails().getAge()));
		request.setAttribute(ProfileFormMapping.ALIAS, model);

		request.getRequestDispatcher(PageMapping.PROFILE).forward(request, response);
	}
}
