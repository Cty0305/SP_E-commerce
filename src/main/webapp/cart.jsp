<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023/11/6
  Time: 下午 09:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<tabel>
    <c:forEach var="goods" items="${cart}">
        <tr>
            <td class="col1">${goods.name}</td>
            <td class="col2">${goods.price}</td>
            <td class="col3">${goods.quantity}</td>
        </tr>
    </c:forEach>
</tabel>



</body>
</html>
