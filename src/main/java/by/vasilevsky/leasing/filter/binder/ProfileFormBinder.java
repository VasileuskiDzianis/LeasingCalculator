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

import by.vasilevsky.leasing.controller.forms.ProfileFormModel;
import by.vasilevsky.leasing.validator.Validator;

@WebFilter(urlPatterns = "/profile", filterName = "profileBindingFilter")
public class ProfileFormBinder implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute("messages");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equalsIgnoreCase("post")) {
			ProfileFormModel model = new ProfileFormModel();
			model.setUserId(request.getParameter("userId"));
			model.setDetailsId(request.getParameter("detailsId"));
			model.setFirstName(request.getParameter("firstName"));
			model.setLastName(request.getParameter("lastName"));
			model.setAge(request.getParameter("age"));
			checkProfileFormModel(model, messages);
		
			request.setAttribute("profileFormModel", model);
		}
		chain.doFilter(request, response);
	}
	
	private void checkProfileFormModel(ProfileFormModel model, ResourceBundle messages) {
		if (model.getUserId() == null || !Validator.validateNumber(model.getUserId())) {
			model.setErrorsExist(true);
			model.setMainMessage(messages.getString("form.message.incorrectdata"));
		}
		if (model.getDetailsId() == null || !Validator.validateNumber(model.getDetailsId())) {
			model.setErrorsExist(true);
			model.setMainMessage(messages.getString("form.message.incorrectdata"));
		}
		if (model.getFirstName() == null || !Validator.validateName(model.getFirstName())) {
			model.setErrorsExist(true);
			model.setFirstNameMessage(messages.getString("form.message.incorrectdata"));
		}
		if (model.getLastName() == null || !Validator.validateName(model.getLastName())) {
			model.setErrorsExist(true);
			model.setLastNameMessage(messages.getString("form.message.incorrectdata"));
		}
		if (model.getAge() == null || !Validator.validateNumber(model.getAge())) {
			model.setErrorsExist(true);
			model.setAgeMessage(messages.getString("form.message.incorrectdata"));
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
}
