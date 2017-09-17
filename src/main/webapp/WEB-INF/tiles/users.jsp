
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="users-container">
	<c:forEach var="user" items="${users}" varStatus="status">
		<form action="<c:url value="/users"/>" method="post">
			<div class="line-item">
				<div class="item-content-column">Логин: ${user.login} Имя: ${user.userDetails.firstName} Фамилия: ${user.userDetails.lastName}</div>
				<div class="item-action-column">
					<input type="hidden" name="user_for_deletion_id" value="${user.id}"/>
					<input type="submit" value="удалить"/>
				</div>
			</div>
		</form>
	</c:forEach>
</div>