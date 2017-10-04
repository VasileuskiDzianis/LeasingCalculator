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

import by.vasilevsky.leasing.service.validator.Validator;
import by.vasilevsky.leasing.web.MethodType;
import by.vasilevsky.leasing.web.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.web.form.RegistrationFormModel;

@WebFilter("/registration")
public class RegistrationFormBinderFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);

		if (httpRequest.getMethod().equalsIgnoreCase(MethodType.POST.name())) {
			RegistrationFormModel model = new RegistrationFormModel();
			model.setLogin(request.getParameter(RegistrationFormMapping.FIELD_LOGIN));
			model.setFirstPassword(request.getParameter(RegistrationFormMapping.FIELD_PASSWORD_1));
			model.setSecondPassword(request.getParameter(RegistrationFormMapping.FIELD_PASSWORD_2));
			checkRegistartionFormModel(model, messages);

			request.setAttribute(RegistrationFormMapping.ALIAS, model);
		}
		chain.doFilter(request, response);
	}

	private void checkRegistartionFormModel(RegistrationFormModel formModel, ResourceBundle messages) {
		if (formModel.getFirstPassword() == null) {
			formModel.setFirstPasswordMessage(messages.getString(MessageMapping.EMPTY_FIELD));
			formModel.setErrors(true);
		}
		if (formModel.getLogin() == null) {
			formModel.setLoginMessage(messages.getString(MessageMapping.EMPTY_FIELD));
			formModel.setErrors(true);
		}
		if (formModel.getFirstPassword() != null
				&& (!formModel.getFirstPassword().equals(formModel.getSecondPassword()))) {
			formModel.setSecondPasswordMessage(messages.getString(MessageMapping.PASSWORDS_DONT_MATCH));
			formModel.setErrors(true);
		}
		if (!Validator.validatePassword(formModel.getFirstPassword())) {
			formModel.setFirstPasswordMessage(messages.getString(MessageMapping.TOO_LITTLE_CHARS));
			formModel.setErrors(true);
		}
		if (!Validator.validateLogin(formModel.getLogin())) {
			formModel.setLoginMessage(messages.getString(MessageMapping.INCORRECT_EMAIL));
			formModel.setErrors(true);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
