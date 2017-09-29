package by.vasilevsky.leasing.controller.error;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.command.PageMapping;
import by.vasilevsky.leasing.filter.i18n.MessageMapping;

@WebServlet("/exception")
public class ExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String JAVAX_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";
	private static final String JAVAX_SERVLET_ERROR_EXCEPTION = "javax.servlet.error.exception";
	private static final int CODE_404 = 404;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		Throwable exception = (Throwable) request.getAttribute(JAVAX_SERVLET_ERROR_EXCEPTION);
		Integer statusCode = (Integer) request.getAttribute(JAVAX_SERVLET_ERROR_STATUS_CODE);
		
		if (exception != null && exception.getClass() == Exception.class) {
			request.setAttribute(MessageMapping.ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_500_INTERNAL));
		} else if (exception != null && exception.getClass() == IllegalArgumentException.class) {
			request.setAttribute(MessageMapping.ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_BADDATA));
		} else if (statusCode == CODE_404) {
			request.setAttribute(MessageMapping.ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_404_DONT_EXIST));
		} else {
			request.setAttribute(MessageMapping.ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_500_INTERNAL));
		}
		RequestDispatcher view = request.getRequestDispatcher(PageMapping.ERROR);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
