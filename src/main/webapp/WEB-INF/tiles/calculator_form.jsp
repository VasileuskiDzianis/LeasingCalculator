
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="calculator-form-container">
	<form action="calculate" method="POST">

		<div class="form-title">Валюта расчёта:</div>
		<select name="currency" class="input-field">
			<option value="byn">BYN</option>
			<option value="usd">USD</option>
			<option value="eur">EUR</option>
			<option value="rub">RUB</option>
		</select>
		<br/>
		<div class="form-title">Тип предмета:</div>
		<div class = "form-input">
		<input type="radio" name="objecttype" value="car" checked="checked" />Легковой автомобиль<br/>
		<input type="radio" name="objecttype" value="lorry" />Грузовой транспорт до 3.5 тонн<br/>
		<input type="radio" name="objecttype" value="truck" />Грузовой транспорт свыше 3.5 тонн<br/>
		<input type="radio" name="objecttype" value="buildingmachines" />Строительная техника<br/>
		<input type="radio" name="objecttype" value="farmingmachinery" />Сельскохозяйственная техника<br/>
		<input type="radio" name="objecttype" value="equipment" />Оборудование<br/>
		<input type="radio" name="objecttype" value="realestate" />Недвижимость<br/>
		</div>
		<br/>
		
		<div class="form-title">Возраст предмета, лет:</div>
		<select name="age" class="input-field">
			<option value="0">новый</option>
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
		<br/>
		
		<div class="form-title">Стоимость:</div>
		<input type="text" name="cost" class="input-field"/>
		<br/>
		
		<div class="form-title">Без НДС:</div>
		<input type="checkbox" name="novatoncostflag" value="1" class="input-field"/>
		<br/>

		<div class="form-title">Аванс:</div>
		<select name="prepay" class="input-field">
			<option value="0.10">10%</option>
			<option value="0.15">15%</option>
			<option value="0.20">20%</option>
			<option value="0.25">25%</option>
			<option value="0.30">30%</option>
			<option value="0.35">35%</option>
			<option value="0.40">40%</option>
		</select>
		<br/>
		
		<div class="form-title">Срок:</div>
		<select name="duration" class="input-field">
			<option value="12">12 мес.</option>
			<option value="18">18 мес.</option>
			<option value="24">24 мес.</option>
			<option value="30">30 мес.</option>
			<option value="36">36 мес.</option>
			<option value="42">42 мес.</option>
			<option value="48">48 мес.</option>
		</select>
		<br/>
		
		<div class="form-title">Выкупная стоимость:</div>
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
		<br/>
	
		<div class="form-title">Включить страхование:</div>
		<input type="checkbox" name="showinsurancecol" value="1" class="input-field"/>
		<br/>
		
		<div class="submit-container">
			<input type="submit" value="Рассчитать" />
		</div>
	</form>
</div>