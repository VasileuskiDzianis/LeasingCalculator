package by.vasilevsky.leasing.controller.command.implementation;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;

public class NotFound404Command implements Command {
	private static volatile NotFound404Command instance;
	
	private NotFound404Command() {
		
	}
	
	public static Command getInstance() {
		NotFound404Command localInstance = instance;
		if (localInstance == null) {
			synchronized (NotFound404Command.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new NotFound404Command();
				}
			}
		}
		return localInstance;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ResourceBundle messages = (ResourceBundle) request.getAttribute("messages");
		
		request.setAttribute("errorMessage", messages.getString("error.message.404internal"));

		return "error.tiles";
	}
}
