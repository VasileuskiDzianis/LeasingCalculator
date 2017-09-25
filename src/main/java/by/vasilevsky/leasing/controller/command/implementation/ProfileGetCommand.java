package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.forms.ProfileFormModel;
import by.vasilevsky.leasing.domain.user.User;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.user.UserService;

public class ProfileGetCommand implements Command {
	private static volatile ProfileGetCommand instance;
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private UserService userService = serviceFactory.getUserService();

	private ProfileGetCommand() {

	}

	public static Command getInstance() {
		ProfileGetCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (ProfileGetCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ProfileGetCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt((String) request.getSession().getAttribute("userId"));
		User user = userService.findUserById(userId);
		ProfileFormModel model = new ProfileFormModel();

		model.setUserId(Integer.toString(userId));
		model.setDetailsId(Integer.toString(user.getUserDetails().getId()));
		model.setFirstName(user.getUserDetails().getFirstName());
		model.setLastName(user.getUserDetails().getLastName());
		model.setAge(Integer.toString(user.getUserDetails().getAge()));

		request.setAttribute("profileFormModel", model);

		return "profile.tiles";
	}
}
