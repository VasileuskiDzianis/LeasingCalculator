package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;

public class CalculateGetCommand implements Command {
	private static volatile CalculateGetCommand instance;
	
	private CalculateGetCommand() {
		
	}
	
	public static Command getInstance() {
		CalculateGetCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (CalculateGetCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new CalculateGetCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "calculator.tiles";
	}
}
