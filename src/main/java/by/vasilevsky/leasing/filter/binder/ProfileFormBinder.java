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
import by.vasilevsky.leasing.view.ProfileFormModel;

@WebFilter(urlPatterns = "/profile", filterName = "profileBindingFilter")
public class ProfileFormBinder implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equalsIgnoreCase("post")) {
			ProfileFormModel model = new ProfileFormModel();
			model.setUserId(request.getParameter("userId"));
			model.setDetailsId(request.getParameter("detailsId"));
			model.setFirstName(request.getParameter("firstName"));
			model.setLastName(request.getParameter("lastName"));
			model.setAge(request.getParameter("age"));
			checkProfileFormModel(model);
		
			request.setAttribute("profileFormModel", model);
		}
		chain.doFilter(request, response);
	}
	
	private void checkProfileFormModel(ProfileFormModel model) {
		if (model.getUserId() == null || !Validator.validateNumber(model.getUserId())) {
			model.setErrorsExist(true);
			model.setMainMessage("Не верный id");
		}
		if (model.getFirstName() == null || !Validator.validateName(model.getFirstName())) {
			model.setErrorsExist(true);
			model.setFirstNameMessage("не корректные данные");
		}
		if (model.getLastName() == null || !Validator.validateName(model.getLastName())) {
			model.setErrorsExist(true);
			model.setLastNameMessage("не корректные данные");
		}
		if (model.getAge() == null || !Validator.validateNumber(model.getAge())) {
			model.setErrorsExist(true);
			model.setAgeMessage("не корректные данные");
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
}
