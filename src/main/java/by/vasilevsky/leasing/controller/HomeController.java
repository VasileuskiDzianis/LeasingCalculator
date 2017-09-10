package by.vasilevsky.leasing.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home",""})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = -6602582281415954285L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = request.getRequestDispatcher("calculator.tiles");
		view.forward(request, response);
	}
}
