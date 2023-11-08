<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023/10/15
  Time: 下午 04:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>customer_reg</title>

    <script>
        function verify(myForm){
            let errMsg="";
            if(myForm.name.value==""){
                errMsg+="名稱不可為空\n";
            }
            if(myForm.price.value==""){
                errMsg+="價格不可為空\n";
            }
            if(myForm.brand.value==""){
                errMsg+="品牌不可為空\n";
            }
            if(isNaN(parseFloat(myForm.price.value))){
                errMsg+="價格請填入數字\n";
            }
            if(isNaN(parseInt(myForm.quantity.value))){
                errMsg+="數量請輸入數字\n";
            }

            if(errMsg==""){
                return true;
            }else {
                alert(errMsg);
                return false;
            }

        }


    </script>


</head>
<body>
<form action="controller" method="post" onsubmit="return verify(this)">
    <input type="text" name="action" value="createGoods" style="display: none">
    <ul>
        <c:forEach var="error" items="${err}">
            <li>${error}</li>
        </c:forEach>
    </ul>
    <table>
        <tr>
            <td>產品名稱</td>
            <td><input type="text" name="name">*</td>
        </tr>
        <tr>
            <td>產品價格</td>
            <td><input type="text" name="price" min="0" step="0.1">*</td>
        </tr>
        <tr>
            <td>產品描述</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td>品牌</td>
            <td><input type="text" name="brand">*</td>
        </tr>
        <tr>
            <td>商品數量</td>
            <td><input type="text" name="quantity" min="1" step="1">*</td>
        </tr>
    </table>
    <input type="submit" value="提交">


</form>
</body>
</html>
