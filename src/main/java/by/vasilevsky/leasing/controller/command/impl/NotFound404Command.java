package by.vasilevsky.leasing.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.Command;
import by.vasilevsky.leasing.controller.command.PageMapping;
import by.vasilevsky.leasing.filter.i18n.LocaleMessagesResolverFilter;

public class NotFound404Command implements Command {
	private static final String ERROR_MESSAGE_ALIAS = "errorMessage";
	private static final String NOT_FOUND_404_MESSAGE = "error.message.404internal";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(LocaleMessagesResolverFilter.MESSAGES_ALIAS);
		request.setAttribute(ERROR_MESSAGE_ALIAS, messages.getString(NOT_FOUND_404_MESSAGE));

		request.getRequestDispatcher(PageMapping.ERROR).forward(request, response);
	}
}
