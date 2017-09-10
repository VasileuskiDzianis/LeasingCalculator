package by.vasilevsky.leasing.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(urlPatterns = {"/calculate"})
public class CalculatePaymentsScheduleController extends HttpServlet {
	private static final long serialVersionUID = -267046298350756472L;

	public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
		
		
		
        RequestDispatcher view = request.getRequestDispatcher("payments_schedule.tiles");
        view.forward(request, response);
    }
}
