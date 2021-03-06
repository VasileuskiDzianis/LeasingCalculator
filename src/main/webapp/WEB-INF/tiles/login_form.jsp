
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${userRole eq 'ANONYMOUS' || userRole eq null}">
<div id="logination-container">
	<div id="logination-content">
		<form action="<c:url value="/login"/>" method="post" class="form-container">
			<div class="logination-title">${messages['i18n.label.login.login']}:</div>
			<input type="email" name="login" value="${loginationFormModel.login}"  />
			<div class="logination-title">${messages['i18n.label.login.password']}:</div>
			<input type="password" name="password" 
				class="form-field" /> 
			<input type="submit" value="${messages['i18n.button.login.signin']}" />
		</form>
		
		<div class="reg-link-container">
			<a href="<c:url value="/registration"/>" class="auth_reg" >${messages['i18n.link.login.register']}</a>
			<div class="form-err-message">${loginationFormModel.mainMessage}</div>
		</div>
	</div>
</div>
</c:if>