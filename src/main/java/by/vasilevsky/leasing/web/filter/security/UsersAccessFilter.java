package by.vasilevsky.leasing.web.filter.security;

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
import by.vasilevsky.leasing.web.controller.command.UrlMapping;

@WebFilter("/users")
public class UsersAccessFilter implements Filter {
	public static final String USER_ROLE_ALIAS = "userRole";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String userRole = (String) httpRequest.getSession().getAttribute(USER_ROLE_ALIAS);
		if (!UserRole.ADMIN.toString().equals(userRole)) {
			httpRequest.getSession().setAttribute(USER_ROLE_ALIAS, UserRole.ANONYMOUS.toString());
			httpResponse.sendRedirect(UrlMapping.LOGINATION);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}