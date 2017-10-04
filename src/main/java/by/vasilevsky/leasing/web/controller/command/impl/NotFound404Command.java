package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.filter.i18n.MessageMapping;

public class NotFound404Command implements Command {
	private static final String ERROR_MESSAGE_ALIAS = "errorMessage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		request.setAttribute(
							ERROR_MESSAGE_ALIAS, 
							messages.getString(MessageMapping.ERROR_MESSAGE_404_DONT_EXIST)
		);

		request.getRequestDispatcher(PageMapping.ERROR).forward(request, response);
	}
}
