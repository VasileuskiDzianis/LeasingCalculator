package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.domain.user.UserRole;

public class LogoutGetCommand implements Command {
	private static volatile LogoutGetCommand instance;
	
	private LogoutGetCommand() {
		
	}
	
	public static Command getInstance() {
		LogoutGetCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (LogoutGetCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LogoutGetCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("userRole", UserRole.ANONYMOUS.toString());
		
		return "redirect:home";
	}
}
