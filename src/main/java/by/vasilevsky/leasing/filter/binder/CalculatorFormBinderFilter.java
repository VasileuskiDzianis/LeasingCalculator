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

import by.vasilevsky.leasing.controller.forms.CalculatorFormModel;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;

@WebFilter("/calculate")
public class CalculatorFormBinderFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute("messages");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equalsIgnoreCase("post")) {
			CalculatorFormModel model = new CalculatorFormModel();
			model.setCurrency(request.getParameter("currency"));
			model.setObjectType(request.getParameter("objecttype"));
			model.setAge(request.getParameter("age"));
			model.setCost(request.getParameter("cost"));
			model.setNoVatOnCost(request.getParameter("no_vat_on_cost"));
			model.setPrepay(request.getParameter("prepay"));
			model.setDuration(request.getParameter("duration"));
			model.setByuingout(request.getParameter("byuingoutpercent"));
			model.setInsurance(request.getParameter("include_insurance"));
			checkCalculatorFormModel(model, messages);
		
			request.setAttribute("calculatorFormModel", model);
		}
		chain.doFilter(request, response);
	}
	
	private void checkCalculatorFormModel(CalculatorFormModel model, ResourceBundle messages) {
		try {
			Currency.valueOf(Currency.class, model.getCurrency());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.setErrors(true);
			model.setCurrencyMessage(messages.getString("form.message.incorrectdata"));
		}
		try {
			PropertyType.valueOf(PropertyType.class, model.getObjectType());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.setErrors(true);
			model.setObjectTypeMessage(messages.getString("form.message.incorrectdata"));
		}
		try {
			Integer.parseInt(model.getAge());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrors(true);
			model.setAgeMessage(messages.getString("form.message.incorrectdata"));
		}
		try {
			Integer.parseInt(model.getDuration());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrors(true);
			model.setDurationMessage(messages.getString("form.message.incorrectdata"));
		}
		try {
			Float.parseFloat(model.getCost());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrors(true);
			model.setCostMessage(messages.getString("form.message.incorrectdata"));
		}
		try {
			Float.parseFloat(model.getPrepay());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrors(true);
			model.setPrepayMessage(messages.getString("form.message.incorrectdata"));
		}
		try {
			Float.parseFloat(model.getByuingout());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrors(true);
			model.setByuingoutMessage(messages.getString("form.message.incorrectdata"));
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
}
