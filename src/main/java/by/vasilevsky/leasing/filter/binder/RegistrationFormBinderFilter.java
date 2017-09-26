package by.vasilevsky.leasing.filter.binder;

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

import by.vasilevsky.leasing.controller.forms.RegistrationFormModel;
import by.vasilevsky.leasing.service.validator.Validator;

@WebFilter("/registration")
public class RegistrationFormBinderFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		ResourceBundle messages = (ResourceBundle) request.getAttribute("messages");
		
		if (httpRequest.getMethod().equalsIgnoreCase("post")) {
			RegistrationFormModel model = new RegistrationFormModel();
			model.setLogin(request.getParameter("login"));
			model.setFirstPassword(request.getParameter("password1"));
			model.setSecondPassword(request.getParameter("password2"));
			checkRegistartionFormModel(model, messages);
		
			request.setAttribute("registrationFormModel", model);
		}
		chain.doFilter(request, response);
	}
	
	private void checkRegistartionFormModel(RegistrationFormModel formModel, ResourceBundle messages) {
		if (formModel.getFirstPassword() == null) {
			formModel.setFirstPasswordMessage(messages.getString("form.message.empty"));
			formModel.setErrors(true);
		}
		if (formModel.getLogin() == null) {
			formModel.setLoginMessage(messages.getString("form.message.empty"));
			formModel.setErrors(true);
		}
		if (formModel.getFirstPassword() != null
				&& (!formModel.getFirstPassword().equals(formModel.getSecondPassword()))) {
			formModel.setSecondPasswordMessage(messages.getString("form.message.pswmatching"));
			formModel.setErrors(true);
		}
		if (!Validator.validatePassword(formModel.getFirstPassword())) {
			formModel.setFirstPasswordMessage(messages.getString("form.message.6chars"));
			formModel.setErrors(true);
		}
		if (!Validator.validateLogin(formModel.getLogin())) {
			formModel.setLoginMessage(messages.getString("form.message.incorrectaddr"));
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
