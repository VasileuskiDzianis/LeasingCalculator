package by.vasilevsky.leasing.controller.command.implementation;

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
		request.setAttribute("errorMessage", "404: Запрашиваемая страница не найдена");

		return "error.tiles";
	}
}
