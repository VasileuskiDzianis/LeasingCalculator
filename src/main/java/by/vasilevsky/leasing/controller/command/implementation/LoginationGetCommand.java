package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;

public class LoginationGetCommand implements Command {
	private static volatile LoginationGetCommand instance;
	
	private LoginationGetCommand() {
		
	}
	
	public static Command getInstance() {
		LoginationGetCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (LoginationGetCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new LoginationGetCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "logination.tiles";
	}
}
