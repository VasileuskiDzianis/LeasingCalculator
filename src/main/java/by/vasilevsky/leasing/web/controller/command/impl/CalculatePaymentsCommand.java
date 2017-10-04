package by.vasilevsky.leasing.web.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.Property;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;
import by.vasilevsky.leasing.domain.rate.insurance.Insurance;
import by.vasilevsky.leasing.domain.rate.lease.BaseRate;
import by.vasilevsky.leasing.domain.rate.lease.Margin;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.rate.insurance.InsuranceService;
import by.vasilevsky.leasing.service.rate.lease.BaseRateService;
import by.vasilevsky.leasing.service.rate.lease.MarginService;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.filter.binder.CalculatorFormMapping;
import by.vasilevsky.leasing.web.form.CalculatorFormModel;

public class CalculatePaymentsCommand implements Command {
	private static final String PAYMENTS_SCHEDULE_ALIAS = "paymentsSchedule";

	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final PaymentsScheduleService paymentsService = serviceFactory.getPaymentsScheduleService();
	private final BaseRateService rateService = serviceFactory.getBaseRateService();
	private final MarginService marginService = serviceFactory.getMarginService();
	private final InsuranceService insuranceService = serviceFactory.getInsuranceService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CalculatorFormModel model = (CalculatorFormModel) request.getAttribute(CalculatorFormMapping.ALIAS);
		
		if (model.hasErrors()) {
			request.getRequestDispatcher(PageMapping.CALCULATOR_FORM).forward(request, response);

			return;
		}
		
		Property property = buildProperty(model);
		PaymentsSchedule payments = buildPaymentsSchedule(model, property);
		paymentsService.countPayments(payments);

		request.setAttribute(PAYMENTS_SCHEDULE_ALIAS, payments);

		request.getRequestDispatcher(PageMapping.PAYMENTS_SCHEDULE).forward(request, response);
	}

	private Property buildProperty(CalculatorFormModel model) {
		Property property = new Property();
		property.setPropertyType(PropertyType.valueOf(PropertyType.class, model.getObjectType()));
		property.setAge(Integer.parseInt(model.getAge()));
		property.setCurrency(Currency.valueOf(Currency.class, model.getCurrency()));
		property.setPrice(getPrice(model));
		property.setVat(property.getPrice() * PaymentsScheduleService.VAT_RATE);

		return property;
	}

	private Float getPrice(CalculatorFormModel model) {
		boolean noVatOnCost = Boolean.parseBoolean(model.getNoVatOnCost());

		return noVatOnCost 
				? Float.parseFloat(model.getCost())
				: Float.parseFloat(model.getCost()) / (1 + PaymentsScheduleService.VAT_RATE);
	}

	private PaymentsSchedule buildPaymentsSchedule(CalculatorFormModel model, Property property) {
		PaymentsSchedule payments = new PaymentsSchedule();
		payments.setCurrency(Currency.valueOf(Currency.class, model.getCurrency()));
		payments.setPrepaymentPercentage(Float.parseFloat(model.getPrepay()));
		payments.setBuyingOutPercentage(Float.parseFloat(model.getByuingout()));
		payments.setLeaseDuration(Integer.parseInt(model.getDuration()));

		boolean includeInsurance = Boolean.parseBoolean(model.getInsurance());
		if (includeInsurance) {
			Insurance insurance = insuranceService.findInsuranceByObjectType(property.getPropertyType());
			payments.setInsuranceRate(insurance.getRate());
		}
		BaseRate rate = rateService.findRateByCurrency(payments.getCurrency());
		Margin margin = marginService.findMarginByTypeAndAge(property.getPropertyType(), property.getAge());

		payments.setLeaseRate(rate.getRate() + margin.getMargin());
		payments.setProperty(property);

		return payments;
	}
}
