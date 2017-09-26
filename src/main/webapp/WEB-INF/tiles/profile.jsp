
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="registration-container">
 <form action="<c:url value="/profile"/>" method="POST">
	<div class="form-title">${messages['label.profile.firstname']}:</div>
	<input type="text" name="firstName" class="input-field" value="${profileFormModel.firstName}"/>
	<div class="form-err-message">${profileFormModel.firstNameMessage}</div>
	<br/>
	<div class="form-title">${messages['label.profile.lastname']}:</div>
	<input type="text" name="lastName" class="input-field" value="${profileFormModel.lastName}"/>
	<div class="form-err-message">${profileFormModel.lastNameMessage}</div>
	<br/>
	<div class="form-title">${messages['label.profile.age']}:</div>
	<input type="text" name="age" class="input-field" value="${profileFormModel.age}"/>
	<div class="form-err-message">${profileFormModel.ageMessage}</div>
	<br/>
	<input type="hidden" name="userId" value="${profileFormModel.userId}"/>
	<input type="hidden" name="detailsId" value="${profileFormModel.detailsId}"/>
	<div class="submit-container">
	<input type="submit" value="${messages['button.profile.save']}"/> <div class="form-err-message">${profileFormModel.mainMessage}</div>
	</div>
 </form>
</div>