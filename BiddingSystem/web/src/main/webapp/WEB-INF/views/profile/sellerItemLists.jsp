<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/dashboard">dashboard</a> my item
	lists
	<br />
	<table>
		<tr>
			<th>Item List Name</th>
			<th>Buy it now price</th>
			<th>Number of items</th>
			<th>Description</th>
			<th></th>
		</tr>

		<c:forEach items="${itemLists}" var="itemList">
			<td>${itemList.name}</td>
			<td>${itemList.buyItNowPrice}</td>
			<td>${itemList.numberOfItems}</td>
			<td>${itemList.description}</td>
		</c:forEach>
	</table>
</body>
</html>