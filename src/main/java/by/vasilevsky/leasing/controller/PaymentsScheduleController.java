package by.vasilevsky.leasing.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vasilevsky.leasing.domain.currency.Currency;
import by.vasilevsky.leasing.domain.lease_object.Property;
import by.vasilevsky.leasing.domain.lease_object.PropertyType;
import by.vasilevsky.leasing.domain.payments.PaymentsSchedule;
import by.vasilevsky.leasing.service.ServiceFactory;
import by.vasilevsky.leasing.service.ServiceFactoryImpl;
import by.vasilevsky.leasing.service.payments.PaymentsScheduleService;
import by.vasilevsky.leasing.service.rate.insurance.LeaseTypeInsuranceService;
import by.vasilevsky.leasing.service.rate.lease.LeaseCurrencyRateService;
import by.vasilevsky.leasing.service.rate.lease.LeaseTypeAgeMarginService;
import by.vasilevsky.leasing.view.CalculatorFormModel;

@WebServlet(urlPatterns = { "/calculate" })
public class PaymentsScheduleController extends HttpServlet {
	private static final long serialVersionUID = -267046298350756472L;

	private ServiceFactory serviceFactory;
	private PaymentsScheduleService paymentsScheduleService;
	private LeaseCurrencyRateService leaseCurrencyRateService;
	private LeaseTypeAgeMarginService leaseTypeAgeMarginService;
	private LeaseTypeInsuranceService leaseTypeInsuranceService;

	@Override
	public void init() {
		serviceFactory = new ServiceFactoryImpl();
		paymentsScheduleService = serviceFactory.getPaymentsScheduleService();
		leaseCurrencyRateService = serviceFactory.getLeaseCurrencyRateService();
		leaseTypeAgeMarginService = serviceFactory.getLeaseTypeAgeMarginService();
		leaseTypeInsuranceService = serviceFactory.getLeaseTypeInsuranceService();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
		checkCalculatorFormModel(model);
		if (model.isErrorsExist()) {
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

	private void checkCalculatorFormModel(CalculatorFormModel model) {
		try {
			Currency.valueOf(Currency.class, model.getCurrency());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.setErrorsExist(true);
			model.setCurrencyMessage("не корректные данные");
		}
		try {
			PropertyType.valueOf(PropertyType.class, model.getObjectType());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.setErrorsExist(true);
			model.setObjectTypeMessage("не корректные данные");
		}
		try {
			Integer.parseInt(model.getAge());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrorsExist(true);
			model.setAgeMessage("не корректные данные");
		}
		try {
			Integer.parseInt(model.getDuration());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrorsExist(true);
			model.setDurationMessage("не корректные данные");
		}
		try {
			Float.parseFloat(model.getCost());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrorsExist(true);
			model.setCostMessage("не корректные данные");
		}
		try {
			Float.parseFloat(model.getPrepay());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrorsExist(true);
			model.setPrepayMessage("не корректные данные");
		}
		try {
			Float.parseFloat(model.getByuingout());
		} catch (NumberFormatException | NullPointerException e) {
			model.setErrorsExist(true);
			model.setByuingoutMessage("не корректные данные");
		}
	}
}
