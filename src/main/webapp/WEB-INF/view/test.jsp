<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


			<!-- UPLOAD THUMBNAIL -->
			<form:form id="uploadFileForm" method="post" enctype="multipart/form-data"
											action="uploadFile" class="form-signin form-horizontal">
				<div class="form-group">
					<label class="sr-only control-label" for="file">Upload thumbnail</label>
					<div class="col-md-10">
						<form:input path="class="form-control" type="file" autofocus="" placeholder="Thumbnail" />
						<form:input class="form-control" type="file" autofocus="" required="" placeholder="Thumbnail" />
					</div>
				</div>
				<div class="form-group">
					<label class="sr-only control-label" for="file">File name</label>
					<div class="col-md-10">
						<form:input class="form-control" type="text" autofocus="" required="" placeholder="File name" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-10">
						<button class="btn btn-lg btn-primary btn-block" type="submit">Upload thumbnail
							»</button>
					</div>
				</div>
			</form:form>


</body>
</html>