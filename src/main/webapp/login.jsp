
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script>
        function verify(myform){
            let msg="";
            if(myform.account.value==""){
                msg+="請輸入帳號\n";
            }
            if(myform.password.value==""){
                msg+="請輸入密碼\n";
            }

            if(msg==""){
                return true;
            }else{
                alert(msg);
                return false;
            }
        }
    </script>
</head>
<body>
<ul>
    <c:forEach var="error" items="${err}">
        <li>${error}</li>
    </c:forEach>
</ul>
<form action="controller" method="post" onsubmit="return verify(this)">
    <input type="text" name="action" value="login" style="display: none">
    <table>
        <tr>
            <td>帳號</td>
            <td><input type="text" name="account"></td>
        </tr>
        <tr>
            <td>密碼</td>
            <td><input type="password" name="password"></td>
        </tr>
    </table>
    <input type="submit">


</form>
</body>
</html>
