<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/calculator.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/payments_schedule.css"/>" />
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="login" />
	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="footer" />
</body>
</html>