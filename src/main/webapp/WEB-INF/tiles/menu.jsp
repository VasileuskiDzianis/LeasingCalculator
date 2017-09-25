
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu-container">
	<div id="menu-content">
		<a class="menu" href="<c:url value="/" />">домой</a>
		<a class="menu" href="<c:url value="/calculate" />">калькулятор</a>
		<c:if test="${userRole eq 'ADMIN'}">
			<a class="menu" href="<c:url value="/users" />">пользователи</a>
		</c:if>
		<c:if test="${userRole eq 'USER'}">
			<a class="menu" href="<c:url value="/profile" />">профиль</a>
		</c:if>
		<c:if test="${userRole eq 'ADMIN' || userRole eq 'USER'}">
			<a class="menu" href="<c:url value="/logout" />">выйти</a>
		</c:if>
	</div>
</div>