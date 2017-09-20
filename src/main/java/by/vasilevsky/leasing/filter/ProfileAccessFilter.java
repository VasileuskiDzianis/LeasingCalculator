package by.vasilevsky.leasing.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.user.UserRole;

@WebFilter("/profile")
public class ProfileAccessFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String userRole = (String) httpRequest.getSession().getAttribute("userRole");

		if (!UserRole.USER.toString().equals(userRole)) {

			httpRequest.getSession().setAttribute("userRole", UserRole.ANONYMOUS.toString());
			httpResponse.sendRedirect("logination");
			
			return;
		}

		if (httpRequest.getMethod().equalsIgnoreCase("post")) {
			String userIdFromRequest = httpRequest.getParameter("userId");
			String userIdFromSession = (String) httpRequest.getSession().getAttribute("userId");
			
			if (userIdFromSession == null || !userIdFromSession.equals(userIdFromRequest)) {
				httpRequest.getSession().setAttribute("userRole", UserRole.ANONYMOUS.toString());
				httpResponse.sendRedirect("logination");
				
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
