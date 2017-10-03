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
import by.vasilevsky.leasing.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.service.validator.Validator;

@WebFilter(urlPatterns = "/profile", filterName = "profileBindingFilter")
public class ProfileFormBinder implements Filter {
	private static final String METHOD_POST = "post";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equalsIgnoreCase(METHOD_POST)) {
			ProfileFormModel model = new ProfileFormModel();
			model.setUserId(request.getParameter(ProfileFormMapping.FIELD_USER_ID));
			model.setDetailsId(request.getParameter(ProfileFormMapping.FIELD_DETAILS_ID));
			model.setFirstName(request.getParameter(ProfileFormMapping.FIELD_FIRST_NAME));
			model.setLastName(request.getParameter(ProfileFormMapping.FIELD_LAST_NAME));
			model.setAge(request.getParameter(ProfileFormMapping.FIELD_AGE));
			checkProfileFormModel(model, messages);
		
			request.setAttribute(ProfileFormMapping.ALIAS, model);
		}
		chain.doFilter(request, response);
	}
	
	private void checkProfileFormModel(ProfileFormModel model, ResourceBundle messages) {
		if (model.getUserId() == null || !Validator.validateNumber(model.getUserId())) {
			model.setErrorsExist(true);
			model.setMainMessage(messages.getString(MessageMapping.INCORRECT_DATA));
		}
		if (model.getDetailsId() == null || !Validator.validateNumber(model.getDetailsId())) {
			model.setErrorsExist(true);
			model.setMainMessage(messages.getString(MessageMapping.INCORRECT_DATA));
		}
		if (model.getFirstName() == null || !Validator.validateName(model.getFirstName())) {
			model.setErrorsExist(true);
			model.setFirstNameMessage(messages.getString(MessageMapping.INCORRECT_DATA));
		}
		if (model.getLastName() == null || !Validator.validateName(model.getLastName())) {
			model.setErrorsExist(true);
			model.setLastNameMessage(messages.getString(MessageMapping.INCORRECT_DATA));
		}
		if (model.getAge() == null || !Validator.validateNumber(model.getAge())) {
			model.setErrorsExist(true);
			model.setAgeMessage(messages.getString(MessageMapping.INCORRECT_DATA));
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
