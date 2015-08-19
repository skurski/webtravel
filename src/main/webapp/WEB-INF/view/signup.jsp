<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		<div>${accountExistsMsg}</div>
	
		<form:form id="registerForm" modelAttribute="user" method="post" action="signup" class="form-signin">
			<h2 class="form-signin-heading">Create account</h2>
			
			<label class="sr-only" for="firstName">First Name</label>
			<form:input path="firstName" id="firstName" class="form-control" 
						autofocus="" required="" placeholder="First Name" />
			<form:errors path="firstName" cssClass="error" />
						
			<label class="sr-only" for="lastName">Last Name</label>			
			<form:input path="lastName" id="lastName" class="form-control" 
						autofocus="" required="" placeholder="Last Name" />
			<form:errors path="lastName" cssClass="error" />
			
						
			<label class="sr-only" for="email">Email</label>
			<form:input path="email" id="email" class="form-control" type="email"
						autofocus="" required="" placeholder="Email" />
			<form:errors path="email" cssClass="error" />

			<label class="sr-only" for="password">Password</label>
			<form:input path="password" id="password" class="form-control" type="password"
						autofocus="" required="" placeholder="Password" />
			<form:errors path="password" cssClass="error" />
						
						
			<label class="sr-only" for="phone">Phone</label>
			<form:input path="phone" id="phone" class="form-control" 
						autofocus="" required="" placeholder="Phone" />
			<form:errors path="phone" cssClass="error" />

			<button class="btn btn-lg btn-primary btn-block" type="submit">Create Account »</button>
		</form:form>
		<a href="login">Already have an account - login here!</a>
	</div>
	<div class="col-md-4"></div>
</div>

<%@include file="template/footer.jsp" %>	

