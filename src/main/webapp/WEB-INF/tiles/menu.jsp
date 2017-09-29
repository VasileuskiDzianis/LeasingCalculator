
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu-container">
	<div id="menu-content">
		<a class="menu" href="<c:url value="/" />">${messages['i18n.link.menu.home']}</a> | 
		<a class="menu" href="<c:url value="/calculate" />">${messages['i18n.link.menu.calc']}</a> | 
		<c:if test="${userRole eq 'ADMIN'}">
			<a class="menu" href="<c:url value="/users" />"> ${messages['i18n.link.menu.users']}</a> | 
		</c:if>
		<c:if test="${userRole eq 'USER'}">
			<a class="menu" href="<c:url value="/profile" />">${messages['i18n.link.menu.profile']}</a> | 
		</c:if>
		<c:if test="${userRole eq 'ADMIN' || userRole eq 'USER'}">
			<a class="menu" href="<c:url value="/logout" />"> ${messages['i18n.link.menu.logout']}</a> | 
		</c:if>
		<a class="menu" href="<c:url value="?lang=en"/>">eng</a> | 
		<a class="menu" href="<c:url value="?lang=ru"/>">рус</a>
	</div>
</div>