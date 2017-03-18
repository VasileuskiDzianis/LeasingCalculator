<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,by.vls.model.calculator.*,by.vls.web.*" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ include file="header.jsp"%>	
    
	<c:forEach var="str" items="${authform}">
	${str}
	</c:forEach>
	
	<c:forEach var="str" items="${logoutform}">
	${str}
	</c:forEach>
	
	<c:forEach var="str" items="${menu}">
	${str}
	</c:forEach>
	
	<c:forEach var="str" items="${main}">
	${str}
	</c:forEach>

<%@ include file="footer.jsp"%>