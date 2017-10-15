package by.vasilevsky.leasing.web.controller.error;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.filter.i18n.MessageMapping;

@WebServlet("/exception")
public class ExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(ExceptionHandler.class);
	
	private static final String ERROR_MESSAGE_ALIAS = "errorMessage";
	
	private static final String JAVAX_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";
	private static final String JAVAX_SERVLET_ERROR_EXCEPTION = "javax.servlet.error.exception";
	private static final int CODE_404 = 404;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		Throwable exception = (Throwable) request.getAttribute(JAVAX_SERVLET_ERROR_EXCEPTION);
		Integer statusCode = (Integer) request.getAttribute(JAVAX_SERVLET_ERROR_STATUS_CODE);
		
		if (exception != null && exception instanceof IllegalArgumentException) {
			request.setAttribute(ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_BADDATA));
		} else if (exception != null && exception instanceof Exception) {
			request.setAttribute(ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_500_INTERNAL));
		} else if (statusCode == CODE_404) {
			request.setAttribute(ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_404_DONT_EXIST));
		} else {
			request.setAttribute(ERROR_MESSAGE_ALIAS, messages.getString(MessageMapping.ERROR_MESSAGE_500_INTERNAL));
		}
		LOGGER.error("Exception occured", exception);
		
		request.getRequestDispatcher(PageMapping.ERROR).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
