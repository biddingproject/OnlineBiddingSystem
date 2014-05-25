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

	<form action="createItemCategory" method="post">
		<label for="categoryName">Item category name </label><input
			name="categoryName" /> <label for="itemCategoryId">Item
			Category</label> <select name="parentCategory" id="parentCategory">
			<option value="null">no parent</option>
			<c:forEach items="${itemCategoryList}" var="itemCat">
				<option value="${itemCat.id}">${itemCat.categoryName}</option>
			</c:forEach>
		</select> <input type="submit" value="create item category" />
	</form>

</body>
</html>