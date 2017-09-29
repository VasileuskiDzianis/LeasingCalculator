
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="calculator-form-container">
	<form action="calculate" method="POST">

		<div class="form-title">${messages['i18n.label.calc.currency']}:</div>
		<select name="currency" class="input-field">
			<option value="BYN">BYN</option>
			<option value="USD">USD</option>
			<option value="EUR">EUR</option>
			<option value="RUB">RUB</option>
		</select>
		<div class="form-err-message">${calculatorFormModel.currencyMessage}</div>
		<br/>
		<div class="form-title">${messages['i18n.label.calc.objecttype']}:</div>
		<div class = "form-input">
		<input type="radio" name="objecttype" value="CAR" checked="checked" />${messages['i18n.label.calc.objecttype.car']}<br/>
		<input type="radio" name="objecttype" value="LORRY" />${messages['i18n.label.calc.objecttype.lorry']}<br/>
		<input type="radio" name="objecttype" value="TRUCK" />${messages['i18n.label.calc.objecttype.truck']}<br/>
		<input type="radio" name="objecttype" value="BUILDING_MACHINERY" />${messages['i18n.label.calc.objecttype.buildmach']}<br/>
		<input type="radio" name="objecttype" value="FARMING_MACHINERY" />${messages['i18n.label.calc.objecttype.farmmach']}<br/>
		<input type="radio" name="objecttype" value="EQUIPMENT" />${messages['i18n.label.calc.objecttype.equipment']}<br/>
		<input type="radio" name="objecttype" value="REALESTATE" />${messages['i18n.label.calc.objecttype.estate']}<br/>
		</div>
		<div class="form-err-message">${calculatorFormModel.objectTypeMessage}</div>
		<br/>
		
		<div class="form-title">${messages['i18n.label.calc.age']}:</div>
		<select name="age" class="input-field">
			<option value="0">${messages['i18n.label.calc.age.new']}</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
		</select>
		<div class="form-err-message">${calculatorFormModel.ageMessage}</div>
		<br/>
		
		<div class="form-title">${messages['i18n.label.calc.cost']}:</div>
			<input type="text" name="cost" class="input-field"/>
			<div class="form-err-message">${calculatorFormModel.costMessage}</div>
		<br/>
		
		<div class="form-title">${messages['i18n.label.calc.novat']}:</div>
			<input type="checkbox" name="no_vat_on_cost" value="true" class="input-field"/>
		<br/>

		<div class="form-title">${messages['i18n.label.calc.prepay']}:</div>
		<select name="prepay" class="input-field">
			<option value="0.10">10%</option>
			<option value="0.15">15%</option>
			<option value="0.20">20%</option>
			<option value="0.25">25%</option>
			<option value="0.30">30%</option>
			<option value="0.35">35%</option>
			<option value="0.40">40%</option>
		</select>
		<div class="form-err-message">${calculatorFormModel.prepayMessage}</div>
		<br/>
		
		<div class="form-title">${messages['i18n.label.calc.duration']}:</div>
		<select name="duration" class="input-field">
			<option value="12">12 ${messages['i18n.label.calc.duration.month']}.</option>
			<option value="18">18 ${messages['i18n.label.calc.duration.month']}.</option>
			<option value="24">24 ${messages['i18n.label.calc.duration.month']}.</option>
			<option value="30">30 ${messages['i18n.label.calc.duration.month']}.</option>
			<option value="36">36 ${messages['i18n.label.calc.duration.month']}.</option>
			<option value="42">42 ${messages['i18n.label.calc.duration.month']}.</option>
			<option value="48">48 ${messages['i18n.label.calc.duration.month']}.</option>
		</select>
		<div class="form-err-message">${calculatorFormModel.durationMessage}</div>
		<br/>
		
		<div class="form-title">${messages['i18n.label.calc.buyingout']}:</div>
		<select name="byuingoutpercent" class="input-field">
			<option value="0.0">0.0%</option>
			<option value="0.0001">0.01%</option>
			<option value="0.001">0.1%</option>
			<option value="0.01">1%</option>
			<option value="0.05">5%</option>
			<option value="0.10">10%</option>
			<option value="0.15">15%</option>
			<option value="0.20">20%</option>
		</select>
		<div class="form-err-message">${calculatorFormModel.byuingoutMessage}</div>
		<br/>
	
		<div class="form-title">${messages['i18n.label.calc.isurance']}:</div>
			<input type="checkbox" name="include_insurance" value="true" class="input-field"/>
		<br/>
		
		<div class="submit-container">
			<input type="submit" value="${messages['i18n.button.calc.calculate']}" />
		</div>
	</form>
</div>