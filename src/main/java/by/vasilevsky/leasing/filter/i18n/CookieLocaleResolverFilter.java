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

public class CookieLocaleResolverFilter implements Filter {
	private static final String LANG_COOKIE_NAME = "language";
	private static final String DEFAULT_LOCALE = "en_US";

	public static final String LOCALE_ALIAS = "locale";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Cookie langCookie = null;
		Cookie[] cookies = httpRequest.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(LANG_COOKIE_NAME)) {
					langCookie = cookie;
				}
			}
		}
		if (langCookie != null) {
			httpRequest.setAttribute(LOCALE_ALIAS, new Locale(langCookie.getValue()));
		} else {
			httpRequest.setAttribute(LOCALE_ALIAS, new Locale(DEFAULT_LOCALE));
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
