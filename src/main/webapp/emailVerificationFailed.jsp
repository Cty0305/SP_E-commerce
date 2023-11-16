<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023/11/15
  Time: 下午 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>認證失敗</p>
<form action="controller">
    <input type="hidden" name="action" value="resendEmail">
    <input type="hidden" name="account" value="${account}">
    <input type="submit" name="submit">

</form>

</body>
</html>
