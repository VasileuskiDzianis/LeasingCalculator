package by.vasilevsky.leasing.filter.i18n;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleChangeFilter implements Filter {
	private static final String LANG_COOKIE_NAME = "language";
	private static final String LANG_PARAM_NAME = "lang";
	private static final int COOKIE_MAX_AGE = 86400;
		
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String targetLanguage = request.getParameter(LANG_PARAM_NAME);
		
		if (targetLanguage != null) {
			httpRequest.setAttribute("locale", new Locale(targetLanguage));
			Cookie langCookie = new Cookie(LANG_COOKIE_NAME, targetLanguage);
			langCookie.setMaxAge(COOKIE_MAX_AGE);
			httpResponse.addCookie(langCookie);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
