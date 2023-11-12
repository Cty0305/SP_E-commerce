<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023/10/24
  Time: 上午 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form action="controller" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="goods_ID" value="${goods.goods_ID}">
                <td class="col1">${goods.name}</td>
                <td class="col2">${goods.price}</td>
                <td class="col3"><input type="submit" value="添加到購物車"></td>
            </form>
        </tr>
    </c:forEach>
</table>

<div>
    <ul>
        <li><a href="controller?action=paging&page=prev">«</a></li>
        <c:forEach var="page" begin="1" end="${totalPageNumber}">
            <li><a

                    href="controller?action=paging&page=${page}" style="text-decoration: none;
                    <c:if test="${page==currentPage}">font-weight:bold</c:if>
            ">${page}</a></li>
        </c:forEach>
        <li><a href="controller?action=paging&page=next">»</a></li>
    </ul>
</div>
</body>
</html>
