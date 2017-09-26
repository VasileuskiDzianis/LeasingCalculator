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

import by.vasilevsky.leasing.controller.forms.LoginationFormModel;

@WebFilter("/logination")
public class LoginationFormBinderFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute("messages");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equalsIgnoreCase("post")) {
			LoginationFormModel model = new LoginationFormModel();

			model.setLogin(request.getParameter("login"));
			model.setPassword(request.getParameter("password"));
			checkLoginationFormModel(model, messages);

			request.setAttribute("loginationFormModel", model);
		}
		chain.doFilter(request, response);
	}

	private void checkLoginationFormModel(LoginationFormModel formModel, ResourceBundle messages) {
		if (formModel.getLogin() == null || formModel.getPassword() == null || formModel.getLogin().length() == 0
				|| formModel.getPassword().length() == 0) {
			formModel.setMainMessage(messages.getString("form.message.incorrectlogorpsw"));
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
