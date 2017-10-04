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

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.web.MethodType;
import by.vasilevsky.leasing.web.filter.i18n.MessageMapping;
import by.vasilevsky.leasing.web.form.CalculatorFormModel;

@WebFilter("/calculate")
public class CalculatorFormBinderFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ResourceBundle messages = (ResourceBundle) request.getAttribute(MessageMapping.ALIAS);
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getMethod().equalsIgnoreCase(MethodType.POST.name())) {
			CalculatorFormModel model = new CalculatorFormModel();
			model.setCurrency(request.getParameter(CalculatorFormMapping.FIELD_CURRENCY));
			model.setObjectType(request.getParameter(CalculatorFormMapping.FIELD_OBJECT_TYPE));
			model.setAge(request.getParameter(CalculatorFormMapping.FIELD_AGE));
			model.setCost(request.getParameter(CalculatorFormMapping.FIELD_COST));
			model.setNoVatOnCost(request.getParameter(CalculatorFormMapping.FIELD_NO_VAT_ON_COST));
			model.setPrepay(request.getParameter(CalculatorFormMapping.FIELD_PREPAY));
			model.setDuration(request.getParameter(CalculatorFormMapping.FIELD_DURATION));
			model.setByuingout(request.getParameter(CalculatorFormMapping.FIELD_BYUINGOUTPERCENT));
			model.setInsurance(request.getParameter(CalculatorFormMapping.FIELD_INCLUDE_INSURANCE));
			checkCalculatorFormModel(model, messages);

			request.setAttribute(CalculatorFormMapping.ALIAS, model);
		}
		chain.doFilter(request, response);
	}

	private void checkCalculatorFormModel(CalculatorFormModel model, ResourceBundle messages) {
		try {
			Currency.valueOf(Currency.class, model.getCurrency());
			PropertyType.valueOf(PropertyType.class, model.getObjectType());
			Integer.parseInt(model.getAge());
			Integer.parseInt(model.getDuration());
			Float.parseFloat(model.getCost());
			Float.parseFloat(model.getPrepay());
			Float.parseFloat(model.getByuingout());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.setErrors(true);
			model.setCurrencyMessage(messages.getString(MessageMapping.INCORRECT_DATA));
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
