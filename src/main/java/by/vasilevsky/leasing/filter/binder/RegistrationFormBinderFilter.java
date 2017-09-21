package by.vasilevsky.leasing.filter.binder;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import by.vasilevsky.leasing.service.validator.Validator;
import by.vasilevsky.leasing.view.RegistrationFormModel;

@WebFilter("/registration")
public class RegistrationFormBinderFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equalsIgnoreCase("post")) {
			RegistrationFormModel model = new RegistrationFormModel();
			model.setLogin(request.getParameter("login"));
			model.setFirstPassword(request.getParameter("password1"));
			model.setSecondPassword(request.getParameter("password2"));
			checkRegistartionFormModel(model);
		
			request.setAttribute("registrationFormModel", model);
		}
		chain.doFilter(request, response);
	}
	
	private void checkRegistartionFormModel(RegistrationFormModel formModel) {
		if (formModel.getFirstPassword() == null) {
			formModel.setFirstPasswordMessage("Поле не может быть пустым");
			formModel.setErrors(true);
		}
		if (formModel.getLogin() == null) {
			formModel.setLoginMessage("Поле не может быть пустым");
			formModel.setErrors(true);
		}
		if (formModel.getFirstPassword() != null
				&& (!formModel.getFirstPassword().equals(formModel.getSecondPassword()))) {
			formModel.setSecondPasswordMessage("Пароли не совпадают");
			formModel.setErrors(true);
		}
		if (!Validator.validatePassword(formModel.getFirstPassword())) {
			formModel.setFirstPasswordMessage("6 букв минимум");
			formModel.setErrors(true);
		}
		if (!Validator.validateLogin(formModel.getLogin())) {
			formModel.setLoginMessage("Не корректный адрес");
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
