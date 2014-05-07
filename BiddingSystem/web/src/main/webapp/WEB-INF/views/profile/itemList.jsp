<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/dashboard">dashboard</a>
	customer profile
	<div>
	
	Item list name : ${itemList.name} <br/>
	Item list description :	${itemList.description} <br/>
	
	<c:choose>
		<c:when test="${itemList.unsoldItemCount > '0'}">
		
			Number of remaining items : ${itemList.unsoldItemCount} <br/>
			Sold : ${itemList.soldItemsCount}<br/>
			Buy it now : ${itemList.buyItNowPrice}
			
			<form action="buyItem" method="post">
				<input type="hidden" name="itemListId" value="${itemList.id}"/>
				<input type="submit" value="Buy"/>
			</form>
		</c:when>
		<c:when test="${itemList.unsoldItemCount <= '0'}">
			Item is not available for sale !!!
		</c:when>
	</c:choose>
	</div>
	
</body>
</html>