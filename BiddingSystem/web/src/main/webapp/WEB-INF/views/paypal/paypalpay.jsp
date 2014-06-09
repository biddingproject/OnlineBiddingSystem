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
	<a href="<%=request.getContextPath()%>/payPal" data-paypal-button="true"> <img
		src="//www.paypalobjects.com/en_US/i/btn/btn_xpressCheckout.gif"
		alt="Check out with PayPal" />
	</a>
</body>
</html>