<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${cartItems}" var="cartItem">
		<table>
			<tr>
				<form method="post" action="removeItemFromCart">
					<input type="hidden" name="cartItemId" value="${cartItem.id}" />
				<td>Item : ${cartItem.itemList.name}</td>
				<td>Item Price : ${cartItem.itemList.buyItNowPrice}</td>
				<td>Quantity : ${cartItem.quantity}</td>
				<td><input type="submit" value="remove" /></td>
				</form>
			</tr>
		</table>
	</c:forEach>

	<form action="clearCart" method="post">
		<input type="submit" value="clear cart" />
	</form>

	<form action="checkOutCart" method="post">
		<input type="submit" value="check out cart" />
	</form>
	<a href="#">continue shopping</a>
</body>
</html>