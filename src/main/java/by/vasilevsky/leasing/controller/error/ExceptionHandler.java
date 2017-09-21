package by.vasilevsky.leasing.controller.error;

import java.io.IOException;

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
		Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (exception != null && exception.getClass() == Exception.class) {
			request.setAttribute("errorMessage", "500: Внутренняя ошибка");
		} else if (exception != null && exception.getClass() == IllegalArgumentException.class) {
			request.setAttribute("errorMessage", "Введены некорректные данные");
		} else if (statusCode == 404) {
			request.setAttribute("errorMessage", "404: Запрашиваемая страница не найдена");
		} else {
			request.setAttribute("errorMessage", "500: Внутренняя ошибка");
		}
		RequestDispatcher view = request.getRequestDispatcher("error.tiles");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
