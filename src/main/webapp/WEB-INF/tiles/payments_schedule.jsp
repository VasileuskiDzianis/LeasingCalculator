
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="payments-schedule">
	<div id="payments-schedule-parameters-container">
		<fmt:setLocale value="${locale}" />
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">${messages['label.payments.objecttype']}:</div>
			<div class="payments-schedule-parameter-item-value">
				${paymentsSchedule.property.propertyType}</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">${messages['label.payments.cost']}:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber value="${paymentsSchedule.property.price}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">${messages['label.payments.vat']}:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber value="${paymentsSchedule.property.vat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">${messages['label.payments.fullcost']}:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber
					value="${paymentsSchedule.property.price + paymentsSchedule.property.vat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">${messages['label.payments.prepayment']}:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber type="percent"
					value="${paymentsSchedule.prepaymentPercentage}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">${messages['label.payments.buyingout']}:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber type="percent"
					value="${paymentsSchedule.buyingOutPercentage}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">${messages['label.payments.currency']}:</div>
			<div class="payments-schedule-parameter-item-value">${paymentsSchedule.currency}</div>
		</div>
	</div>

	<div class="payments-list-row">
		<div class="payments-list-col">${messages['label.payments.date']}</div>
		<div class="payments-list-col">${messages['label.payments.purpose']}</div>
		<div class="payments-list-col">${messages['label.payments.debt']}</div>
		<div class="payments-list-col">${messages['label.payments.leaserate']}</div>
		<div class="payments-list-col">${messages['label.payments.leaseratevat']}</div>
		<div class="payments-list-col">${messages['label.payments.repayment']}</div>
		<div class="payments-list-col">${messages['label.payments.repaymentvat']}</div>
		<div class="payments-list-col">${messages['label.payments.insurance']}</div>
		<div class="payments-list-col">${messages['label.payments.insurancevat']}</div>
		<div class="payments-list-col">${messages['label.payments.total']}</div>
	</div>
	<c:forEach var="payment" items="${paymentsSchedule.monthlyPayments}">
		<div class="payments-list-row">
			<div class="payments-list-col">
				<fmt:formatDate value="${payment.paymentDate}" pattern="d MMM y" />
			</div>
			<div class="payments-list-col">
				<c:choose>
					<c:when test="${payment.paymentType eq 'PRE_PAYMENT'}">${messages['label.payments.purpose.prepay']}</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${payment.paymentType eq 'LEASE_PAYMENT'}">${messages['label.payments.purpose.lease']}</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${payment.paymentType eq 'BUYING_OUT_PAYMENT'}">${messages['label.payments.purpose.buyout']}</c:when>
				</c:choose>
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber value="${payment.remainingDebt}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber value="${payment.leaseMargin}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber value="${payment.leaseMarginVat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber value="${payment.propertyCostRepayment}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber value="${payment.propertyCostRepaymentVat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber value="${payment.insurance}" minFractionDigits="2"
					maxFractionDigits="2" />
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber value="${payment.insuranceVat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
			<div class="payments-list-col">
				<fmt:formatNumber
					value="${payment.leaseMargin + payment.leaseMarginVat + payment.propertyCostRepayment + payment.propertyCostRepaymentVat + payment.insurance+payment.insuranceVat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
	</c:forEach>
</div>