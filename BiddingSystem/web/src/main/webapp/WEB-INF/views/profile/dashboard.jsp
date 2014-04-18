<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dash board</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/seller">seller area</a>
	<a href="<%=request.getContextPath()%>/customer">customer area</a>
	<a href="<%=request.getContextPath()%>/admin">admin area</a>
	
	profile	picture <br/>
	first name: ${user.firstName} <br/> 
	last name: ${user.lastName} <br/>
	phone number : ${user.phoneNumber} <br/> 
	address : ${user.address} <br/>
	email :	${user.email} <br/>
	profile : <img src="<%=request.getContextPath()%>/getUserImage/${user.id}" />
	
	<hr />
	
	<h3>Change profile pic</h3>
	<form action="changeProfilePic" method="post" enctype="multipart/form-data">
		<label for="image">New profile pic : </label>
		<input name="image" type="file"/><br/>
		<input value="submit" type="submit"/>
	</form>

</body>
</html>