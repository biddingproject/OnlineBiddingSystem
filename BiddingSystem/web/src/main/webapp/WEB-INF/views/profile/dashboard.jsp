<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dash board</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/seller">seller area</a>
	<a href="<%=request.getContextPath()%>/customer">customer area</a>
	<a href="<%=request.getContextPath()%>/admin">admin area</a> profile
	picture
	<br /> first name: ${user.firstName}
	<br /> last name: ${user.lastName}
	<br /> phone number : ${user.phoneNumber}
	<br /> address : ${user.address}
	<br /> email : ${user.email}
	<br />
	<c:if test="${not empty user.profilePicture}">
	profile : <img
			src="<%=request.getContextPath()%>/getUserImage/${user.id}" />
	</c:if>
	<c:if test="${empty user.profilePicture}">
	profile : <img
			src="<%=request.getContextPath()%>/images/download_big.png" />
	</c:if>

	<hr />

	<h3>Change profile pic</h3>
	<form action="changeProfilePic" method="post"
		enctype="multipart/form-data">
		<label for="image">New profile pic : </label> <input name="image"
			type="file" /><br /> <input value="submit" type="submit" />
	</form>

	<hr />

	<h3>Change password</h3>
	<form action="changePassword" method="post">
		<label for="oldPassword">Old password : </label> <input
			name="oldPassword" type="password" /><br /> <label
			for="newPassword">New password : </label> <input name="newPassword"
			type="password" /><br /> <label for="confirmPassword">Confirm
			password : </label> <input name="confirmPassword" type="password" /><br />
		<input value="submit" type="submit" />
	</form>

	<hr />

	<h3>Edit your information</h3>

	<form:form method="post" action="updateUserInformation"
		modelAttribute="user">
		<fieldset>
			<div>
				<form:label path="firstName" for="firstName">First Name</form:label>
				<form:input type="text" path="firstName" placeholder="First name"
					required="required" pattern=".{3,}" value="${user.firstName}" />
			</div>
			<div>
				<form:label path="lastName" for="lastName">Last Name</form:label>
				<form:input type="text" path="lastName" placeholder="Last name"
					required="required" pattern=".{3,}" value="${user.lastName}" />
			</div>

			<div>
				<form:label path="address" for="address">Address</form:label>
				<form:input type="text" id="address" path="address"
					placeholder="address" required="required" value="${user.address}" />
			</div>

			<div>
				<form:label path="phoneNumber" for="phoneNumber">Phone Number</form:label>
				<form:input type="text" id="phoneNumber" placeholder="phoneNumber"
					path="phoneNumber" required="required" pattern=".{10,12}"
					value="${user.phoneNumber}" />
			</div>

			<input type="submit" value="update">
		</fieldset>
	</form:form>

</body>
</html>