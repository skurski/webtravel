<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<div class="container">
	<div class="col-md-12">
	<form:form id="editTravelForm" modelAttribute="gallery" method="post"
			action="uploadFile" class="form-signin form-horizontal">
			<div class="form-group">
				<label class="sr-only control-label" for="path">File</label>
				<div class="col-md-12">
					<form:input path="path" id="path" class="form-control" type="file" />
					<form:errors path="path" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only control-label" for="name">Title</label>
				<div class="col-md-12">
					<form:input path="name" id="name" class="form-control"
						type="text" autofocus="" required="" placeholder="Name" />
					<form:errors path="name" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Add Picture
						»</button>
				</div>
			</div>
		</form:form>
	</div>
</div>


</body>
</html>