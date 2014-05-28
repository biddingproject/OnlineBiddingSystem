<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kavi
  Date: 5/25/14
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
    <form:form method="post" action="search">
        <fieldset>
            <div>
                <input type="text" id="searchKey" name="searchKey" value="" placeholder="type your search here"
                    required="required" />

                <input type="submit" value="Search">
            </div>
        </fieldset>
    </form:form>
</body>
</html>
