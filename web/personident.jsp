<%@ page language="java" contentType="text/html;charset=UTF-8" import="by.vls.model.user.*,by.vls.model.calculator.*,by.vls.web.*,java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp"%>	
	<%request.setCharacterEncoding("UTF-8");%>
	<jsp:useBean id="persona" class="by.vls.model.user.Person">
	<jsp:setProperty name="persona" property="*"/>
	</jsp:useBean>
	<%
	    UserValidator valid = new UserValidator();
        try {
            valid.checkCookies(request, persona);
        } catch (Exception e) {
            e.printStackTrace();
        }
	%>
	
	<c:forEach var="str" items="${menu}">
	${str}
	</c:forEach>
	<p class="identity">
	<%persona.saveProfile();%>
	Ваши персональные данные сохранены и будут использованы при расчёте персональной скидки</br>
	---</br>
	<c:out value="${persona.fullInfo}" escapeXml="false"/></br>
	</p>
  
<%@ include file="footer.jsp"%>