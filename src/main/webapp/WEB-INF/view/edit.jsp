<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container col-md-12">
	<c:url var="userRegistration" value="saveUser.html" />
	<form:form id="registerForm" modelAttribute="user" method="post"
		action="update">
		<table>
			<tr>
				<td><form:label path="firstName">First Name</form:label></td>
				<td><form:input path="firstName" value="${userObj.firstName}" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last Name</form:label></td>
				<td><form:input path="lastName" value="${userObj.lastName}" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" value="${userObj.email}" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">Phone</form:label></td>
				<td><form:input path="phone" value="${userObj.phone}" /></td>
			</tr>
		</table>
		<button class="btn btn-primary" type="submit">Update »</button>
	</form:form>
</div>

<%@include file="template/footer.jsp" %>	

