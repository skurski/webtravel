<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container col-md-12">
	<div class="col-md-8">
		<h2 class="text-muted">See details of your travel</h2>
		<div class="table-responsive">
			<c:if test="${empty travelList}">
				${noTravel}
			</c:if>
			<c:if test="${!empty travelList}">
				<table id="tour-table" class="table table-striped table-hover">
					<tr>
						<td>Thumbnail</td>
						<td>Title</td>
						<td>Location</td>
						<td>Edit</td>
						<td>Delete</td>
					</tr>
					<c:forEach items="${travelList}" var="travel">
						<tr>
							<td><img src="<c:out value="${travel.thumbnail}" />"
								height="80" width="140"></td>
							<td style="vertical-align: middle;"><c:out
									value="${travel.name}" /></td>
							<td style="vertical-align: middle;"><c:out
									value="${travel.location}" /></td>
							<td style="vertical-align: middle;"><a
								href="edit-tour?travelId=${travel.id}">Edit</a></td>
							<td style="vertical-align: middle;"><a
								href="delete-tour?travelId=${travel.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>

	<div class="col-md-4">
		<form:form id="addTravelForm" modelAttribute="travel" method="post"
			action="addTravel" class="form-signin form-horizontal">
			<h2 class="text-muted">Add new travel</h2>
			<div class="form-group">
				<label class="sr-only control-label" for="name">Title</label>
				<div class="col-md-12">
					<form:input path="name" id="name" class="form-control"
						type="text" autofocus="" required="" placeholder="Name" />
					<form:errors path="name" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="location">Location</label>
				<div class="col-md-12">
					<form:input path="location" id="location" class="form-control"
						type="text" autofocus="" required="" placeholder="Location" />
					<form:errors path="location" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="latitude">Latitude</label>
				<div class="col-md-12">
					<form:input path="latitude" id="latitude" class="form-control"
						type="text" autofocus="" required="" placeholder="Latitude example: 48.87146 	 " />
					<form:errors path="latitude" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="longitude">Location</label>
				<div class="col-md-12">
					<form:input path="longitude" id="longitude" class="form-control"
						type="text" autofocus="" required="" placeholder="Longitude example: 2.35500 " />
					<form:errors path="longitude" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="description">Description</label>
				<div class="col-md-12">
					<form:textarea path="description" id="description" class="form-control" rows="6" cols="10" placeholder="Description"/>
					<form:errors path="longitude" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Add Travel
						»</button>
				</div>
			</div>
		</form:form>
	</div>
</div>

<%@include file="template/footer.jsp" %>	
