package by.vasilevsky.leasing.controller.error;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exception")
public class ExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute("messages");
		Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		
		if (exception != null && exception.getClass() == Exception.class) {
			request.setAttribute("errorMessage", messages.getString("error.message.500internal"));
		} else if (exception != null && exception.getClass() == IllegalArgumentException.class) {
			request.setAttribute("errorMessage", messages.getString("error.message.baddata"));
		} else if (statusCode == 404) {
			request.setAttribute("errorMessage", messages.getString("error.message.404internal"));
		} else {
			request.setAttribute("errorMessage", messages.getString("error.message.500internal"));
		}
		RequestDispatcher view = request.getRequestDispatcher("error.tiles");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
