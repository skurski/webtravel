<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="template/head.jsp" %>	
<%@include file="template/header.jsp" %>

<div class="container col-md-12">
	<div class="table-responsive">
		<c:if test="${!empty userList}">
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
				<c:forEach items="${userList}" var="user">
					<tr>
						<td><c:out value="${user.firstName}" />
						</td>
						<td><c:out value="${user.lastName}" />
						</td>
						<td><c:out value="${user.email}" />
						</td>
						<td><c:out value="${user.password}" />
						</td>
						<td><c:out value="${user.phone}" />
						</td>
						<td><a href="edit?id=${user.id}">Edit</a></td>
						<td><a href="delete?id=${user.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>

<%@include file="template/footer.jsp" %>	
