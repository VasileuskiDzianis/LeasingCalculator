package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.forms.ProfileFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class ProfilePostCommand implements Command {
	private static volatile ProfilePostCommand instance;
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	private ProfilePostCommand() {

	}

	public static Command getInstance() {
		ProfilePostCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (ProfilePostCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ProfilePostCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ProfileFormModel model = (ProfileFormModel) request.getAttribute("profileFormModel");
		if (!model.hasErrors()) {
			User user = userService.findUserById(Integer.parseInt(model.getUserId()));
			user.getUserDetails().setFirstName(model.getFirstName());
			user.getUserDetails().setLastName(model.getLastName());
			user.getUserDetails().setAge(Integer.parseInt(model.getAge()));
			userService.updateUser(user);
			
			return "redirect:profile";
		}
		request.setAttribute("profileFormModel", model);

		return "profile.tiles";
	}
}
