
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="registration-container">
	<h2>Leasing payments calculator</h2>
	It provides next functions:
	<ul>
		<li>Calculating payments which depend on selected: currency,
			object type, age...</li>
		<li>New user registration</li>
		<li>Users log inning</li>
		<li>There are two roles: USER and ADMIN. User can see and edit
			his profile, ADMIN can see all users and delete them</li>
		<li>USER doesn't have access to list of users and ADMIN doesn't
			have access to USER profile</li>
		<li>All constraints are regulated with JSP-filters</li>
	</ul>
	Used technologies:
	<ul>
		<li>Java</li>
		<li>Servlet API</li>
		<li>JSP</li>
		<li>JSTL and EL</li>
		<li>Apache Tiles</li>
		<li>Tomcat</li>
		<li>Plain JDBC</li>
		<li>MySQL</li>
		<li>Maven</li>
		<li>JUnit</li>
		<li>HTML</li>
		<li>CSS</li>
	</ul>
</div>