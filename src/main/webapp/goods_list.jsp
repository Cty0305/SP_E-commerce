<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023/10/24
  Time: 上午 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr >
        <th>商品名稱</th>
        <th>商品價格</th>
        <th>加到購物車</th>
    </tr>
    <c:forEach var="goods" items="${goodsList}" varStatus="status">
        <tr>
            <td class="col1">${goods.description}</td>
            <td class="col2">${goods.price}</td>
            <td class="col3">添加到购物车</td>
        </tr>
    </c:forEach>
</table>

<div>
    <ul>

        <li><a href="controller?action=paging&page=prev">«</a></li>
        <c:forEach var="page" begin="1" end="${totalPageNumber}">
            <li><a
                    <c:if test="${page == currentPage}">
                        class="active"
                    </c:if>
                    href="controller?action=paging&page=${page}">${page}</a></li>
        </c:forEach>
        <li><a href="controller?action=paging&page=next">»</a></li>
    </ul>
</div>
</body>
</html>
