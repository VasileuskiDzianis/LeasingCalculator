package by.vasilevsky.leasing.filter.i18n;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LocaleMessagesResolverFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Locale locale = (Locale) request.getAttribute("locale");
		ResourceBundle messages = ResourceBundle.getBundle("i18n", locale);

		request.setAttribute("messages", messages);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
