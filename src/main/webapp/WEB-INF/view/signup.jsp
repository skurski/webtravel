<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>

<div class="container">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<div>${accountExistsMsg}</div>

		<form:form id="registerForm" modelAttribute="user" method="post"
			action="signup" class="form-signin form-horizontal">
			<h2 class="form-signin-heading">Create account</h2>

			<div class="form-group">
				<label class="sr-only control-label" for="firstName">First
					Name</label>
				<div class="col-md-10">
					<form:input path="firstName" id="firstName" class="form-control"
						autofocus="" required="" placeholder="First Name" />
					<form:errors path="firstName" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="lastName">Last
					Name</label>
				<div class="col-md-10">
					<form:input path="lastName" id="lastName" class="form-control"
						autofocus="" required="" placeholder="Last Name" />
					<form:errors path="lastName" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="email">Email</label>
				<div class="col-md-10">
					<form:input path="email" id="email" class="form-control"
						autofocus="" required="" placeholder="Email" />
					<form:errors path="email" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="password">Password</label>
				<div class="col-md-10">
					<form:input path="password" id="password" class="form-control"
						type="password" autofocus="" required="" placeholder="Password" />
					<form:errors path="password" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label class="sr-only control-label" for="phone">Phone</label>
				<div class="col-md-10">
					<form:input path="phone" id="phone" class="form-control"
						autofocus="" required="" placeholder="Phone" />
					<form:errors path="phone" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-10">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Create
						Account »</button>
				</div>
			</div>
		</form:form>
		<div class="form-group">
			<div class="col-md-10">
				<label> <a href="login">Already have an account - login
						here!</a>
				</label>
			</div>
		</div>
	</div>
	<div class="col-md-3"></div>
</div>

<%@include file="template/footer.jsp"%>


