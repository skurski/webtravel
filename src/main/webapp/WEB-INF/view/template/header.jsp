<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
			<div class="navbar-collapse collapse navbar-responsive-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/webtravel">Home</a></li>
					<% if(session.getAttribute("user") == null) { %>
						<li><a href="signup">Signup</a></li>
						<li><a href="login">Login</a></li>					
					<% } else { %>
						<li><a href="edit-account">Account</a></li>
						<li><a href="tour-list">List of tours</a></li>
						<li><a href="logout">Logout</a></li>
					<% } %>
				</ul>
			</div>
	</div>
</div>

