package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.UserRole;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 8780315513488014012L;


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("userRole", UserRole.ANONYMOUS.toString());
		response.sendRedirect("home");
	}

}
