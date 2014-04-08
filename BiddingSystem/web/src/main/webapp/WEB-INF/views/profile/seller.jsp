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
				<form:label path="baseBid" for="baseBid">Base Bid</form:label>
				<form:input type="text" path="baseBid" placeholder="starting bid"
					required="required" />
			</div>
			<div>
				<form:label path="buyItNowPrice" for="buyItNowPrice">Buy it now price</form:label>
				<form:input type="text" path="buyItNowPrice"
					placeholder="buy it now price" required="required" />
			</div>
			<div>
				<form:label path="description" for="description">description</form:label>
				<form:input type="text" id="description" path="description"
					placeholder="description" required="required" />
			</div>
			<div>
				<form:label path="bidStartTimeStamp" for="bidStartTimeStamp">bid start time</form:label>
				<form:input type="text" id="bidStartTimeStamp"
					path="bidStartTimeStamp" placeholder="bid start time"
					required="required" />
			</div>
			<div>
				<form:label path="expireTimeStamp" for="expireTimeStamp">bid end time</form:label>
				<form:input type="text" id="expireTimeStamp" path="expireTimeStamp"
					placeholder="bid end time" required="required" />
			</div>

			<input type="submit" value="save item list">
		</fieldset>
	</form:form>


</body>
</html>