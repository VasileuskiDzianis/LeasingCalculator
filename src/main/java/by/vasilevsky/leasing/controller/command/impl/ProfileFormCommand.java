package by.vasilevsky.leasing.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.PageMapping;
import by.vasilevsky.leasing.controller.forms.ProfileFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.filter.security.ProfileAccessFilter;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class ProfileFormCommand implements Command {
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final UserService userService = serviceFactory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt((String) request.getSession().getAttribute(ProfileAccessFilter.USER_ID_ALIAS));
		User user = userService.findUserById(userId);
		ProfileFormModel model = new ProfileFormModel();
		model.setUserId(Integer.toString(userId));
		model.setDetailsId(Integer.toString(user.getUserDetails().getId()));
		model.setFirstName(user.getUserDetails().getFirstName());
		model.setLastName(user.getUserDetails().getLastName());
		model.setAge(Integer.toString(user.getUserDetails().getAge()));
		request.setAttribute(ProfileFormModel.ALIAS, model);

		request.getRequestDispatcher(PageMapping.PROFILE).forward(request, response);
	}
}
