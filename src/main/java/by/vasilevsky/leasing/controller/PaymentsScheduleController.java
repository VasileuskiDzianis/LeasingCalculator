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

@WebServlet(urlPatterns = { "/calculate" })
public class PaymentsScheduleController extends HttpServlet {
	private static final long serialVersionUID = -267046298350756472L;

	private ServiceFactory serviceFactory = new ServiceFactoryImpl();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PaymentsScheduleService paymentsScheduleService = serviceFactory.getPaymentsScheduleService();
		LeaseCurrencyRateService leaseCurrencyRateService = serviceFactory.getLeaseCurrencyRateService();
		LeaseTypeAgeMarginService leaseTypeAgeMarginService = serviceFactory.getLeaseTypeAgeMarginService();
		LeaseTypeInsuranceService leaseTypeInsuranceService = serviceFactory.getLeaseTypeInsuranceService();

		PaymentsSchedule paymentsSchedule = new PaymentsSchedule();
		Property property = new Property();
		paymentsSchedule.setCurrency(Currency.valueOf(Currency.class, request.getParameter("currency")));
		paymentsSchedule.setPrepaymentPercentage(Float.parseFloat(request.getParameter("prepay")));
		paymentsSchedule.setBuyingOutPercentage(Float.parseFloat(request.getParameter("byuingoutpercent")));
		paymentsSchedule.setLeaseDuration(Integer.parseInt(request.getParameter("duration")));

		property
				.setPropertyType(PropertyType.valueOf(PropertyType.class, request.getParameter("objecttype")));
		property.setAge(Integer.parseInt(request.getParameter("age")));
		property.setCurrency(paymentsSchedule.getCurrency());

		boolean noVatOnCost = false;

		if (request.getParameter("no_vat_on_cost") != null) {
			noVatOnCost = Boolean.parseBoolean(request.getParameter("no_vat_on_cost"));
		}

		if (request.getParameter("cost") != "") {
			if (noVatOnCost) {
				property.setPrice(Float.parseFloat(request.getParameter("cost")));
			} else {
				property.setPrice(
						Float.parseFloat(request.getParameter("cost")) / (1 + PaymentsScheduleService.VAT_RATE));
			}
			property.setVat(property.getPrice() * PaymentsScheduleService.VAT_RATE);
		} else {
			property.setPrice(0f);
		}

		boolean includeInsurance = false;
		if (request.getParameter("include_insurance") != null) {
			includeInsurance = Boolean.parseBoolean(request.getParameter("include_insurance"));
		}

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
