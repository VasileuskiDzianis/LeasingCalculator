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
import by.vasilevsky.leasing.service.rate.InsuranceService;
import by.vasilevsky.leasing.service.rate.LeaseRateService;
import by.vasilevsky.leasing.web.controller.command.Command;
import by.vasilevsky.leasing.web.controller.command.PageMapping;
import by.vasilevsky.leasing.web.filter.binder.CalculatorFormMapping;
import by.vasilevsky.leasing.web.form.CalculatorFormModel;

public class CalculatePaymentsCommand implements Command {
	private static final String PAYMENTS_SCHEDULE_ALIAS = "paymentsSchedule";

	private final ServiceFactory serviceFactory;
	private final PaymentsScheduleService paymentsService;
	private final LeaseRateService rateService;
	private final InsuranceService insuranceService;
	
	public CalculatePaymentsCommand() {
		serviceFactory = ServiceFactory.getInstance();
		paymentsService = serviceFactory.getPaymentsScheduleService();
		rateService = serviceFactory.getLeaseRateService();
		insuranceService = serviceFactory.getInsuranceService();
	}

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
	
	private PaymentsSchedule buildPaymentsSchedule(CalculatorFormModel model, Property property) {
		PaymentsSchedule payments = new PaymentsSchedule();
		payments.setCurrency(Currency.valueOf(Currency.class, model.getCurrency()));
		payments.setPrepaymentPercentage(Float.parseFloat(model.getPrepay()));
		payments.setBuyingOutPercentage(Float.parseFloat(model.getByuingout()));
		payments.setLeaseDuration(Integer.parseInt(model.getDuration()));
		payments.setInsuranceRate(getInsurance(model, property));
		payments.setLeaseRate(getFullLeaseRate(payments.getCurrency(), property));
		payments.setProperty(property);

		return payments;
	}

	private float getPrice(CalculatorFormModel model) {
		boolean noVatOnCost = Boolean.parseBoolean(model.getNoVatOnCost());
		float cost = Float.parseFloat(model.getCost());
		
		return noVatOnCost 
						? cost 
						: deductVat(cost);
	}

	private Float getFullLeaseRate(Currency currency, Property property) {
		BaseRate rate = rateService.findRateByCurrency(currency);
		Margin margin = rateService.findMarginByTypeAndAge(property.getPropertyType(), property.getAge());
		
		return rate.getRate() + margin.getMargin();
	}
	
	private float getInsurance(CalculatorFormModel model, Property property) {
		boolean includeInsurance = Boolean.parseBoolean(model.getInsurance());
		Insurance insurance;
		if (includeInsurance) {
			insurance = insuranceService.findInsuranceByObjectType(property.getPropertyType());
		} else {
			insurance = new Insurance();
		}
		
		return insurance.getRate();
	}
	
	private float deductVat(Float value) {
		
		return value / (1 + PaymentsScheduleService.VAT_RATE);
	}
}
