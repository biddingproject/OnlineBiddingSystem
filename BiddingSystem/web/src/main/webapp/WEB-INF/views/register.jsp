<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
	<form:form method="post" action="register" modelAttribute="user">
		<fieldset>
			<div>
				<form:label path="firstName" for="firstName">First Name</form:label>
				<form:input type="text" path="firstName" placeholder="First name"
					required="required" pattern=".{3,}" />
			</div>
			<div>
				<form:label path="lastName" for="lastName">Last Name</form:label>
				<form:input type="text" path="lastName" placeholder="Last name"
					required="required" pattern=".{3,}" />
			</div>
			<div>
				<form:label path="email" for="email">email</form:label>
				<form:input type="email" id="email" path="email"
					placeholder="email address" required="required" />
			</div>
			<div>
				<form:label path="address" for="address">Address</form:label>
				<form:input type="text" id="address" path="address"
					placeholder="address" required="required" />
			</div>
			<div>
				<form:label path="password" for="password"> Password</form:label>
				<form:input type="password" id="password" placeholder="password"
					path="password" value="" required="required" pattern=".{5,50}" />
			</div>
			<div>
				<form:label path="phoneNumber" for="phoneNumber">Phone Number</form:label>
				<form:input type="text" id="phoneNumber" placeholder="phoneNumber"
					path="phoneNumber" required="required" pattern=".{10,12}" />
			</div>
			
			<div>
				<input type="checkbox" name="isSeller">register as seller<br>
				<input type="checkbox" name="isCustomer" checked="checked">register as customer
			</div>
			
			<input type="submit" value="Register">
		</fieldset>
	</form:form>
</body>
</html>