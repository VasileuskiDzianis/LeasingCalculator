package by.vasilevsky.leasing.web.filter.binder;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import by.vasilevsky.leasing.web.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.web.form.LoginFormModel;

@WebFilter("/login")
public class LoginFormBinderFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equalsIgnoreCase(MethodType.POST.name())) {
			LoginFormModel model = new LoginFormModel();
			model.setLogin(request.getParameter(LoginFormMapping.FIELD_LOGIN));
			model.setPassword(request.getParameter(LoginFormMapping.FIELD_PASSWORD));
			checkLoginationFormModel(model, messages);
			request.setAttribute(LoginFormMapping.ALIAS, model);
		}
		chain.doFilter(request, response);
	}

	private void checkLoginationFormModel(LoginFormModel formModel, ResourceBundle messages) {
		if (formModel.getLogin() == null 
				|| formModel.getPassword() == null 
				|| formModel.getLogin().length() == 0
				|| formModel.getPassword().length() == 0) {
			formModel.setMainMessage(messages.getString(MessageMapping.INCORRECT_LOGIN_OR_PSW));
			formModel.setErrorsExist(true);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
