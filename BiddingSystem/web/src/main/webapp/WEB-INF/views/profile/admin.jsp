<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/dashboard">dashboard</a>

	<form action="createItemCategory" method="post">
		<label for="categoryName">Item category name </label><input
			name="categoryName" /> 
		<label for="parentCategory">Parent
			Category</label><input name="parentCategory" /> <input type="submit"
			value="create item category" />
	</form>

</body>
</html>