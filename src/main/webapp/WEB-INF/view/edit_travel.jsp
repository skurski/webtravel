<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>

<div class="container">
	<div class="col-md-12">
		<h2 class="text-muted">Detail information about your trip</h2>
	</div>

	<div class="row">
		<div class="col-md-8">
			<div id="map-canvas" style="height: 400px; width: 100%;"></div>
		</div>

		<div class="col-md-4">
			<form:form id="editTravelForm" modelAttribute="travel" method="post"
				action="edit-tour" class="form-signin form-horizontal">
				<div class="form-group">
					<label class="sr-only control-label" for="name">Title</label>
					<div class="col-md-12">
						<form:input path="name" id="name" class="form-control" type="text"
							autofocus="" required=""
							placeholder="${!empty travel.name ? travel.name : 'Name'}" />
						<form:errors path="name" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="sr-only control-label" for="location">Location</label>
					<div class="col-md-12">
						<form:input path="location" id="location" class="form-control"
							type="text" autofocus="" required=""
							placeholder="${!empty travel.location ? travel.location : 'Location'}" />
						<form:errors path="location" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="sr-only control-label" for="latitude">Latitude</label>
					<div class="col-md-12">
						<form:input path="latitude" id="latitude" class="form-control"
							type="text" autofocus="" required=""
							placeholder="${!empty travel.latitude ? travel.latitude : 'Latitude example: 48.87146'}" />
						<form:errors path="latitude" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="sr-only control-label" for="longitude">Location</label>
					<div class="col-md-12">
						<form:input path="longitude" id="longitude" class="form-control"
							type="text" autofocus="" required=""
							placeholder="${!empty travel.longitude ? travel.longitude : 'Longitude example: 48.87146'}" />
						<form:errors path="longitude" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="sr-only control-label" for="description">Description</label>
					<div class="col-md-12">
						<form:textarea path="description" id="description"
							class="form-control" rows="6" cols="10"
							placeholder="${!empty travel.description ? travel.description : 'Description'}" />
						<form:errors path="longitude" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<button class="btn btn-lg btn-primary btn-block" type="submit">Submit
							changes »</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>


	<div class="row">
		<div class="col-md-12">
			<h2 class="text-muted">Your pictures</h2>
		</div>
		
		<div class="col-md-8">
			<c:if test="${!empty gallerySet}">
				<div id="myCarousel" class="carousel slide">
					<div class="carousel-inner">
						<%
							boolean active = true;
						%>
						<c:forEach items="${gallerySet}" var="gallery">
							<div class="item <%=active ? "active" : ""%>"
								style="height: 600px; width: 800px;">
								<img
									src="${!empty gallery.path ? gallery.path : 'http://placehold.it/1280x500'}"
									alt="" height="600px" width="800px">
								<!-- Opis slajdu -->
								<div class="carousel-caption">
									<h3>${!empty gallery.title ? gallery.title : 'No details on image'}</h3>
								</div>
							</div>
							<%
								active = false;
							%>
						</c:forEach>
					</div>
					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</c:if>
			<c:if test="${empty gallerySet}">
				<div>You don't have any picture</div>
			</c:if>
		</div>

		<div class="col-md-4">
			<div>${invalidFileMsg}</div>
			<form id="uploadFileForm" ModelAttribute="gallery" method="post"
				enctype="multipart/form-data" action="uploadFile"
				class="form-signin form-horizontal">
				<h2 class="text-muted">Upload picture</h2>

				<div class="col-md-12">
					<input name="file" class="" type="file" />
				</div>

				<br />

				<div class="form-group">
					<label class="sr-only control-label" for="name">Title</label>
					<div class="col-md-12">
						<input name="name" class="form-control" type="text" autofocus=""
							required="" placeholder="Name" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<button class="btn btn-lg btn-primary btn-block" type="submit"
							id="uploadFileFormSubmit">Add Picture »</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<%@include file="template/footer.jsp"%>

<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDC3f0xPLRA5meF2z7Ze3HCBWTjG-St1r4&sensor=false"></script>
	<script src="<c:url value="/resources/assets/js/google_map.js" />"></script>

<script>
	$(document).ready(function() {
		var latitude = ${!empty travel.latitude ? travel.latitude : "48.87146"};
		var longitude = ${!empty travel.longitude ? travel.longitude : "2.87146"};
		initialize(latitude, longitude);
		// google.maps.event.addDomListener(window, 'load', initialize);
	});
</script>