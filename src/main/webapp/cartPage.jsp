<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023/11/9
  Time: 下午 11:30
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
        <tr>
            <td>產品名稱</td>
            <td>數量</td>
            <td>價格</td>
            <td>總價</td>
        </tr>
        <c:forEach var="cartItem" items="${cartList}" varStatus="loop">
            <tr>
                <td>${cartItem.getGoods().getName()}</td>
                <td><input type="number" min="0" step="1" value="${cartItem.getQuantity()}" id="quantityInput${loop.index}" onKeyDown="return validDataInput(event)"></td>
                <td id ="price${loop.index}" data-price="${cartItem.getGoods().getPrice()}">${cartItem.getGoods().getPrice()}</td>
                <td><input type="text" id="totalPrice${loop.index}" value= ${cartItem.getQuantity() * cartItem.getGoods().getPrice()} readonly></td>
            </tr>
            <script>
                const quantityInput${loop.index} = document.getElementById('quantityInput${loop.index}');

                //限制數字輸入
                function validDataInput(event){
                    const keyCode = event.which || event.keyCode;
                    if ((keyCode >= 48 && keyCode <= 57) || keyCode === 8 || (keyCode >= 37 && keyCode <= 40) || keyCode === 190) {
                        return true;
                    } else {
                        return false;
                    }
                }


                // 監聽器
                quantityInput${loop.index}.addEventListener("input",updateTotalPrice${loop.index});

                function updateTotalPrice${loop.index}() {
                    const quantityInput = document.getElementById(`quantityInput${loop.index}`);
                    const totalPriceElement = document.getElementById(`totalPrice${loop.index}`);
                    const priceElement = document.getElementById("price${loop.index}");
                    const quantity = parseInt(quantityInput.value) || 0;
                    const price = parseFloat(priceElement.getAttribute("data-price"));
                    console.log(price);
                    console.log(quantity);
                    const total = quantity * price;
                    console.log(total);
                    totalPriceElement.value = total;


                }
            </script>

        </c:forEach>
        
        
    </table>

    <div>
        <ul>
            <li><a href="controller?action=cart_paging&page=prev">«</a></li>
            <c:forEach var="page" begin="1" end="${totalPageNumber}">
                <li><a

                        href="controller?action=cart_paging&page=${page}" style="text-decoration: none;
                <c:if test="${page==currentPage}">font-weight:bold</c:if>
                        ">${page}</a></li>
            </c:forEach>
            <li><a href="controller?action=cart_paging&page=next">»</a></li>
        </ul>
    </div>



</body>
</html>
