package by.vasilevsky.leasing.controller.command.implementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;

public class HomeGetCommand implements Command {
	private static volatile HomeGetCommand instance;
	
	private HomeGetCommand() {
		
	}
	
	public static Command getInstance() {
		HomeGetCommand localInstance = instance;
		if (localInstance == null) {
			synchronized (HomeGetCommand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new HomeGetCommand();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		return "about.tiles";
	}
}
