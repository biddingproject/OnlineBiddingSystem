<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/dashboard">dashboard</a> seller
	profile

	<form:form method="post" action="createItemList"
		modelAttribute="itemList">
		<fieldset>

			<div>
				<form:label path="name" for="name">Item list name</form:label>
				<form:input type="text" path="name" placeholder="list name"
					required="required" />
			</div>

			<div>
				<form:label path="baseBid" for="baseBid">Base Bid Name</form:label>
				<form:input type="text" path="baseBid" placeholder="starting bid"
					required="required" />
			</div>
			<div>
				<form:label path="buyItNowPrice" for="buyItNowPrice">Buy it now price</form:label>
				<form:input type="text" path="buyItNowPrice"
					placeholder="buy it now price" required="required" />
			</div>
			<div>
				<form:label path="email" for="email">email</form:label>
				<form:input type="email" id="email" path="email"
					placeholder="email address" required="required" />
			</div>
			<div>
				<form:label path="address" for="address">Address</form:label>
				<form:input type="text" id="address" path="address"
					placeholder="address" required="required" />
			</div>
			<div>
				<form:label path="password" for="password"> Password</form:label>
				<form:input type="password" id="password" placeholder="password"
					path="password" value="" required="required" pattern=".{5,50}" />
			</div>
			<div>
				<form:label path="phoneNumber" for="phoneNumber">Phone Number</form:label>
				<form:input type="text" id="phoneNumber" placeholder="phoneNumber"
					path="phoneNumber" required="required" pattern=".{10,12}" />
			</div>

			<input type="submit" value="Register">
		</fieldset>
	</form:form>


</body>
</html>