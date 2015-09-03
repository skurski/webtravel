<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>


<div class="container">
	<div class="col-md-3"></div>
	<div class="col-md-6">

		<form:form id="registerForm" modelAttribute="user" method="post"
			action="edit-account" class="form-signin form-horizontal">
			<h2 class="form-signin-heading">Update account</h2>

			<div class="form-group">
				<label class="sr-only control-label" for="firstName">First
					Name</label>
				<div class="col-md-10">
					<form:input path="firstName" id="firstName" class="form-control"
						autofocus="" required="" placeholder="${userObj.firstName}" value="${userObj.firstName}"/>
					<form:errors path="firstName" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="lastName">Last
					Name</label>
				<div class="col-md-10">
					<form:input path="lastName" id="lastName" class="form-control"
						autofocus="" required="" placeholder="${userObj.lastName}" value="${userObj.lastName}"/>
					<form:errors path="lastName" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="email">Email</label>
				<div class="col-md-10">
					<form:input path="email" id="email" class="form-control"
						autofocus="" required="" placeholder="${userObj.email}" value="${userObj.email}"/>
					<form:errors path="email" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="password">Password</label>
				<div class="col-md-10">
					<form:input path="password" id="password" class="form-control"
						type="password" autofocus="" required="" placeholder="${userObj.password}" value="${userObj.password}"/>
					<form:errors path="password" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="phone">Phone</label>
				<div class="col-md-10">
					<form:input path="phone" id="phone" class="form-control"
						autofocus="" required="" placeholder="${userObj.phone}" value="${userObj.phone}" />
					<form:errors path="phone" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-10">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Update
						Account »</button>
				</div>
			</div>
		</form:form>
	</div>
	<div class="col-md-3"></div>
</div>

<%@include file="template/footer.jsp" %>	

