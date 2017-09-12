
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="payments-schedule">
	<div id="payments-schedule-parameters-container">
		<fmt:setLocale value="ru_RU" />
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">Предмет
				лизинга:</div>
			<div class="payments-schedule-parameter-item-value">
				${paymentsSchedule.leaseObject.leaseObjectType}</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">Контрактная
				стоимость:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber value="${paymentsSchedule.leaseObject.price}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">НДС на
				стоимость:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber value="${paymentsSchedule.leaseObject.vat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">Cтоимость c
				НДС:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber
					value="${paymentsSchedule.leaseObject.price + paymentsSchedule.leaseObject.vat}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">Авансовый
				платеж:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber type="percent"
					value="${paymentsSchedule.prepaymentPercentage}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">Выкупной
				платеж:</div>
			<div class="payments-schedule-parameter-item-value">
				<fmt:formatNumber type="percent"
					value="${paymentsSchedule.buyingOutPercentage}"
					minFractionDigits="2" maxFractionDigits="2" />
			</div>
		</div>
		<div class="payments-schedule-parameter-item">
			<div class="payments-schedule-parameter-item-title">Валюта
				графика:</div>
			<div class="payments-schedule-parameter-item-value">${paymentsSchedule.currency}</div>
		</div>
	</div>

	<div class="payments-list-row">
		<div class="payments-list-col">Дата</div>
		<div class="payments-list-col">Назначение</div>
		<div class="payments-list-col">Задолженность</div>
		<div class="payments-list-col">Лизинговая ставка</div>
		<div class="payments-list-col">НДС на лизинговую ставку</div>
		<div class="payments-list-col">Погашение стоимости</div>
		<div class="payments-list-col">НДС на погашение стоимости</div>
		<div class="payments-list-col">Страхование</div>
		<div class="payments-list-col">НДС на страховнаие</div>
	</div>
	<c:forEach var="payment" items="${paymentsSchedule.monthlyPayments}">
		<div class="payments-list-row">
			<div class="payments-list-col"><fmt:formatDate value="${payment.paymentDate}" pattern="d MMM y"/></div>
			<div class="payments-list-col">${payment.paymentType}</div>
			<div class="payments-list-col"><fmt:formatNumber value="${payment.remainingDebt}" minFractionDigits="2" maxFractionDigits="2" /></div>
			<div class="payments-list-col"><fmt:formatNumber value="${payment.leaseMargin}" minFractionDigits="2" maxFractionDigits="2" /></div>
			<div class="payments-list-col"><fmt:formatNumber value="${payment.leaseMarginVat}" minFractionDigits="2" maxFractionDigits="2" /></div>
			<div class="payments-list-col"><fmt:formatNumber value="${payment.leaseObjectCostRepayment}" minFractionDigits="2" maxFractionDigits="2" /></div>
			<div class="payments-list-col"><fmt:formatNumber value="${payment.leaseObjectCostRepaymentVat}" minFractionDigits="2" maxFractionDigits="2" /></div>
			<div class="payments-list-col"><fmt:formatNumber value="${payment.insurance}" minFractionDigits="2" maxFractionDigits="2" /></div>
			<div class="payments-list-col"><fmt:formatNumber value="${payment.insuranceVat}" minFractionDigits="2" maxFractionDigits="2" /></div>
		</div>
	</c:forEach>
</div>