<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

	<c:if test="${not empty pageContext.request.userPrincipal}">
		<a href="<%=request.getContextPath()%>/dashboard"><c:out
				value="dashboard" /></a>
		<a href="<%=request.getContextPath()%>/signout"><c:out
				value="signout" /></a>
	</c:if>

	<c:if test="${empty pageContext.request.userPrincipal}">
		<a href="<%=request.getContextPath()%>/login">login</a> Not a member ?
	<a href="<%=request.getContextPath()%>/register">register</a>
	</c:if>

	This is the index page

</body>
</html>