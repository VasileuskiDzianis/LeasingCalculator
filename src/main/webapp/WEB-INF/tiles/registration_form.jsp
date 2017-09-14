
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="registration-container">
 <form action="<c:url value="/registration"/>" method="POST">
	<div class="form-title">Адрес e-mail:</div><input type="text" name="login" class="input-field"/>
	<br/>
	<div class="form-title">Пароль:</div><input type="password" name="password1" class="input-field"/>
	<br/>
	<div class="form-title">Пароль еще раз:</div><input type="password" name="password2" class="input-field"/>
	<br/>
	<div class="submit-container">
	<input type="submit" value="Зарегистрироваться"/>
	</div>
 </form>
</div>