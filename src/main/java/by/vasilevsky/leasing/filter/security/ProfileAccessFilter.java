package by.vasilevsky.leasing.filter.security;

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

import by.vasilevsky.leasing.controller.command.UrlMapping;
import by.vasilevsky.leasing.domain.user.UserRole;

@WebFilter(urlPatterns = "/profile", filterName = "profileAccessFilter")
public class ProfileAccessFilter implements Filter {
	public static final String USER_ROLE_ALIAS = "userRole";
	public static final String USER_ID_ALIAS = "userId";
	private static final String METHOD_POST = "post";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String userRole = (String) httpRequest.getSession().getAttribute(USER_ROLE_ALIAS);
		if (!UserRole.USER.toString().equals(userRole)) {

			httpRequest.getSession().setAttribute(USER_ROLE_ALIAS, UserRole.ANONYMOUS.toString());
			httpResponse.sendRedirect(UrlMapping.LOGINATION);
			
			return;
		}
		if (httpRequest.getMethod().equalsIgnoreCase(METHOD_POST)) {
			String userIdFromRequest = httpRequest.getParameter(USER_ID_ALIAS);
			String userIdFromSession = (String) httpRequest.getSession().getAttribute(USER_ID_ALIAS);
			
			if (userIdFromSession == null || !userIdFromSession.equals(userIdFromRequest)) {
				httpRequest.getSession().setAttribute(USER_ROLE_ALIAS, UserRole.ANONYMOUS.toString());
				httpResponse.sendRedirect(UrlMapping.LOGINATION);
				
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
