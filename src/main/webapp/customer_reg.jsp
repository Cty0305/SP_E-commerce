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
          if(myForm.account.value==""){
            errMsg+="帳號不可為空\n";
          }
          if(myForm.name.value==""){
            errMsg+="名字不可為空\n";
          }
          if(myForm.password.value==""){
            errMsg+="密碼不可為空\n";
          }
          if(myForm.gender.value==""){
            errMsg+="需選擇性別\n";
          }
          if(myForm.password2.value !== myForm.password.value){
            errMsg+="密碼不相同\n";
          }
          if (!/^09\d{8}$/.test(myForm.phone.value)) {
            errMsg += "電話號碼格式不正確\n";
          }


          if (myForm.birthday.value) {
            const selectedDate = new Date(myForm.birthday.value);
            const currentDate = new Date();
            if (selectedDate > currentDate) {
              errMsg += "出生日期不能是未來\n";
            }
          }
          if(!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(myForm.email.value)){
            errMsg+="請輸入正確的email格式\n";
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
  <input type="text" name="action" value="register" style="display: none">
  <ul>
    <c:forEach var="error" items="${err}">
      <li>${error}</li>
    </c:forEach>
  </ul>
  <table>
    <tr>
      <td>帳號</td>
      <td><input type="text" name="account">*</td>
    </tr>
    <tr>
      <td>姓名</td>
      <td><input type="text" name="name">*</td>
    </tr>
    <tr>
      <td>密碼</td>
      <td><input type="password" name="password">*</td>
    </tr>
    <tr>
      <td>再次輸入密碼</td>
      <td><input type="password" name="password2">*</td>
    </tr>
    <tr>
      <td>出生日期</td>
      <td><input type="date" name="birthday"></td>
    </tr>
    <tr>
      <td>性別</td>
      <td>
        <select name="gender" id="">
          <option value="male">male</option>
          <option value="female">female</option>
          <option value="other">other</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>地址</td>
      <td><input type="text" name="address"></td>
    </tr>
    <tr>
      <td>電話</td>
      <td><input type="text" name="phone"></td>
    </tr>
    <tr>
      <td>Email</td>
      <td><input type="email" name="email">*</td>
    </tr>
  </table>
  <input type="submit" value="提交">


</form>
</body>
</html>
