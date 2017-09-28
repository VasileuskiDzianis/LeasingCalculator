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
	private static final String I18N_FILE = "i18n";
	
	public static final String MESSAGES_ALIAS = "messages";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Locale locale = (Locale) request.getAttribute(CookieLocaleResolverFilter.LOCALE_ALIAS);
		ResourceBundle messages = ResourceBundle.getBundle(I18N_FILE, locale);

		request.setAttribute(MESSAGES_ALIAS, messages);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
