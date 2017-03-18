<%@ page language="java" contentType="text/html;charset=UTF-8" import="by.vls.model.user.*,by.vls.model.calculator.*,by.vls.web.*,java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp"%>	
	<%request.setCharacterEncoding("UTF-8");%>
	<jsp:useBean id="company" class="by.vls.model.user.Company">
	<jsp:setProperty name="company" property="*"/>
	</jsp:useBean>
	<%
	    UserValidator valid = new UserValidator();
        try {
            valid.checkCookies(request, company);
        } catch (Exception e) {
            e.printStackTrace();
        }
	%>
	
	<c:forEach var="str" items="${menu}">
	${str}
	</c:forEach>
	<p class="identity">
	<%company.saveProfile();%>
	Данные о вашей компании сохранены и будут использованы при расчёте персональной скидки</br>
	---</br>
	<c:out value="${company.fullInfo}" escapeXml="false"/></br>
	</p>
  
<%@ include file="footer.jsp"%>