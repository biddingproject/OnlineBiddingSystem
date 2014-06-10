<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Description of the item/items you selected
	
	Item Name : ${itemList.name}

	Item Description : ${itemList.description} 
	
	Quantity : ${quantity} 
	
	Buy	item with Paypal <br/>

	<form action="buyItemsWithPaypal" method="post">
		<input type="hidden" name="itemListId" value="${itemList.id}" />
		<input type="hidden" name="quantity" value="${itemList.quantity}" />
		<input type="submit" value="buy with paypal" />
	</form>
	
	<form action="dashboard" method="get">
		<input type="submit" value="cancel and return" />
	</form>

</body>
</html>