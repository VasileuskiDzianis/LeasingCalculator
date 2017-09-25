package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.controller.forms.CalculatorFormModel;
import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.Property;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.rate.insurance.LeaseTypeInsuranceService;
import by.vasilevsky.leasing.service.rate.lease.LeaseCurrencyRateService;
import by.vasilevsky.leasing.service.rate.lease.LeaseTypeAgeMarginService;

@WebServlet(urlPatterns = { "/calculate" })
public class PaymentsScheduleController extends HttpServlet {
	private static final long serialVersionUID = -267046298350756472L;

	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final PaymentsScheduleService paymentsScheduleService = serviceFactory.getPaymentsScheduleService();
	private final LeaseCurrencyRateService leaseCurrencyRateService = serviceFactory.getLeaseCurrencyRateService();
	private final LeaseTypeAgeMarginService leaseTypeAgeMarginService = serviceFactory.getLeaseTypeAgeMarginService();
	private final LeaseTypeInsuranceService leaseTypeInsuranceService = serviceFactory.getLeaseTypeInsuranceService();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CalculatorFormModel model = (CalculatorFormModel) request.getAttribute("calculatorFormModel");
		if (model.hasErrors()) {
			request.setAttribute("calculatorFormModel", model);
			RequestDispatcher view = request.getRequestDispatcher("calculator.tiles");
			view.forward(request, response);

			return;
		}
		PaymentsSchedule paymentsSchedule = new PaymentsSchedule();
		Property property = new Property();
		paymentsSchedule.setCurrency(Currency.valueOf(Currency.class, model.getCurrency()));
		paymentsSchedule.setPrepaymentPercentage(Float.parseFloat(model.getPrepay()));
		paymentsSchedule.setBuyingOutPercentage(Float.parseFloat(model.getByuingout()));
		paymentsSchedule.setLeaseDuration(Integer.parseInt(model.getDuration()));
		property.setPropertyType(PropertyType.valueOf(PropertyType.class, model.getObjectType()));
		property.setAge(Integer.parseInt(model.getAge()));
		property.setCurrency(paymentsSchedule.getCurrency());

		boolean noVatOnCost = Boolean.parseBoolean(model.getNoVatOnCost());

		if (noVatOnCost) {
			property.setPrice(Float.parseFloat(model.getCost()));
		} else {
			property.setPrice(Float.parseFloat(model.getCost()) / (1 + PaymentsScheduleService.VAT_RATE));
		}
		property.setVat(property.getPrice() * PaymentsScheduleService.VAT_RATE);

		boolean includeInsurance = Boolean.parseBoolean(model.getInsurance());

		if (includeInsurance) {
			float insuranceRate = leaseTypeInsuranceService.findInsuranceByObjectType(property.getPropertyType())
					.getInsuranceRate();
			paymentsSchedule.setInsuranceRate(insuranceRate);
		}

		float leaseCurrencyRate = leaseCurrencyRateService.findLeaseRateByCurrency(paymentsSchedule.getCurrency())
				.getCurrencyRate();
		float leaseTypeAgeMarginRate = leaseTypeAgeMarginService
				.findLeaseRateByTypeAndAge(property.getPropertyType(), property.getAge()).getMargin();

		paymentsSchedule.setLeaseRate(leaseCurrencyRate + leaseTypeAgeMarginRate);
		paymentsSchedule.setProperty(property);
		paymentsScheduleService.countPayments(paymentsSchedule);

		request.setAttribute("paymentsSchedule", paymentsSchedule);
		RequestDispatcher view = request.getRequestDispatcher("payments_schedule.tiles");
		view.forward(request, response);
	}
}
