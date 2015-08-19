<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container col-md-12">
	<div class="jumbotron">
		<div>
			<h1>Welcome to Spring & Hibernate App!</h1>
			<p>Below are some options for you to test...</p>
		</div>

		<% if(session.getAttribute("userId") == null) { %>
			<a class="btn btn-primary" href="signup">Signup » </a> 
			<a class="btn btn-success" href="login">Login » </a>				
		<% } else { %>
			<a class="btn btn-primary" href="logout">Logout » </a>
		<% } %>
	</div>
</div>

<%@include file="template/footer.jsp" %>	
		