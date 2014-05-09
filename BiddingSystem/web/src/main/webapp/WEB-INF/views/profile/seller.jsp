<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/dashboard">dashboard</a>
	<a href="<%=request.getContextPath()%>/getMyItemLists">my item
		lists</a> seller profile
	<hr />

	<form:form method="post" action="createItemList"
		modelAttribute="itemList">
		<h3>Create Item List</h3>
		<c:choose>
			<c:when test="${itemCategoryListSize!='0' }">
			
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
					<form:label path="numberOfItems" for="numberOfItems">Number of items</form:label>
					<form:input type="text" id="numberOfItems" path="numberOfItems"
						placeholder="number of items" required="required" min="1" />
				</div>
	
				<div>
					<form:label path="isBiddable" for="isBiddable">Eligible for bididng</form:label>
					<form:checkbox path="isBiddable" name="isBiddable" checked="checked" />
				</div>
	
				<div>
					<label for="bidStartTimeStamp">Bid start time</label> <input
						type="datetime-local" name="bidStartTime">
				</div>
				<div>
					<label for="expireTimeStamp">Bid end time</label> <input
						type="datetime-local" name="expireTime">
				</div>
	
				<div>
					<label for="itemCategoryId">Item Category</label> <select
						name="itemCategoryId" id="itemCategoryId">
						<c:forEach items="${itemCategoryList}" var="itemCategory">
							<option value="${itemCategory.id}">${itemCategory.categoryName}</option>
						</c:forEach>
					</select>
				</div>
	
				<input type="hidden" id="timeOffset" name="timeOffset"
					value="getTimeOffset();"> <input type="submit"
					value="create item list">
			</fieldset>
			
			</c:when>
			
			<c:when test="${itemCategoryListSize == '0' }">
				No item categories are defined !!!
			</c:when>
			
		</c:choose>
		
		
	</form:form>
	<script type="text/javascript">
		var dateObj = new Date();
		var timeOffset = dateObj.getTimezoneOffset();
		var elem = document.getElementById("timeOffset");
		elem.value = timeOffset;
	</script>

</body>
</html>