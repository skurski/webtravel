<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container">
	<div class="jumbotron">
			<div>
				<div id="map-canvas" style="height: 400px; width: 100%; border:1px solid #ccc;"></div>
			
				<h1>Welcome to WebTravel, Memorize your trips ...</h1>
				<p class="text-muted">Made with Maven, Spring, Hibernate, Bootstrap...</p>
			</div>
	
			<% if(session.getAttribute("user") == null) { %>
				<a class="btn btn-primary" href="signup">Signup » </a> 
				<a class="btn btn-success" href="login">Login » </a>				
			<% } else { %>
				<a class="btn btn-primary" href="logout">Logout » </a>
			<% } %>
	</div>
</div>

<%@include file="template/footer.jsp" %>	
		
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDC3f0xPLRA5meF2z7Ze3HCBWTjG-St1r4&sensor=false"></script>
	<script src="<c:url value="/resources/assets/js/google_map.js" />"></script>

<script>
	$(document).ready(function() {
		initialize(50.0833333, 19.9166667);
		// google.maps.event.addDomListener(window, 'load', initialize);
	});
</script>