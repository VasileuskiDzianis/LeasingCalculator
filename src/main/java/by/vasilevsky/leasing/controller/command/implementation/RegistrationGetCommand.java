package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;

public class RegistrationGetCommand implements Command {
	private static volatile RegistrationGetCommand instance;
	
	private RegistrationGetCommand() {
		
	}
	
	public static Command getInstance() {
		RegistrationGetCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (RegistrationGetCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new RegistrationGetCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "registration.tiles";
	}
}
