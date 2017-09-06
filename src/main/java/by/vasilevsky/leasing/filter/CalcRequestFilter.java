package by.vasilevsky.leasing.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class CalcRequestFilter implements Filter {
	private FilterConfig fc;

	public void init(FilterConfig config) {
		this.fc = config;
		System.out.println("++++++ Filter initialization: " + config.getInitParameter("LogFileName"));
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
		String name = httpReq.getServerName();
		if (name != null) {
			fc.getServletContext().log("User " + name + " is calculatig");
			System.out.println("++++++++++++ Filter Logging");
		}
		System.out.println("++++++++++++ Filter not Logging");

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
