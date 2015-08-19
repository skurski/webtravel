<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		<div>${accountNotExistsMsg}</div>
		<div>${passwordNotMatchMsg}</div>
		
		<form:form id="registerForm" modelAttribute="user" method="post" action="login" class="form-signin">
			<h2 class="form-signin-heading">Login</h2>
			<label class="sr-only" for="email">Email</label>
			<form:input path="email" id="email" class="form-control" type="email"
						autofocus="" required="" placeholder="Email" />
			<label class="sr-only" for="password">Password</label>
			<form:input path="password" id="password" class="form-control" type="password"
						autofocus="" required="" placeholder="Password" />

			<button class="btn btn-lg btn-primary btn-block" type="submit">Login »</button>
		</form:form>
		<a href="signup">Don't have an account - sign up here!</a>
	</div>
	<div class="col-md-4"></div>
</div>

<%@include file="template/footer.jsp" %>	

