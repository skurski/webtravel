<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container col-md-12">
	<div class="table-responsive">
		<c:if test="${!empty travelObj}">
			<table class="table table-striped">
				<tr>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Email</td>
					<td>Password</td>
					<td>Phone</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>
				<c:forEach items="${travelObj}" var="travel">
					<tr>
						<td><c:out value="${travel.name}" />
						</td>
						<td><c:out value="${travel.location}" />
						</td>
						<td><c:out value="${travel.coordinate}" />
						</td>
						<td><c:out value="${travel.desc}" />
						</td>
						<td><a href="edit?id=${travel.id}">Edit</a></td>
						<td><a href="delete?id=${travel.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>

<%@include file="template/footer.jsp" %>	
