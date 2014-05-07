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
	Buy it now price : ${itemList.buyItNowPrice} <br/>
	Number of items : ${itemList.numberOfItems}</div>
</body>
</html>