<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-tw">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document Setting Page</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    />
    <link rel="stylesheet" href="resource/css/all.css">
  </head>
  <body>
  <script>
    function verify(myform){
      let msg="";
      if(myform.email.value==""){
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

    <ul>
     <c:forEach var="error" items="${err}">
       <li>${error}</li>
      </c:forEach>
    </ul>

    <nav class="navbar justify-content-center align-items-center">
      <div class="header d-flex justify-content-between align-items-center">
        <h1 class="brand">
          <a
            href="https://cty0305.github.io/SP_E-commerce/homepage"
            class="header-logo-container d-flex flex-column justify-content-center align-items-center"
          >
            <img
              src="https://github.com/Cty0305/SP_E-commerce/blob/main/Resource/Image/Brand-Logo.jpg?raw=true"
              class="header-logo"
            />
          </a>
          <span class="brand-name visually-hidden">DonDonmart</span>
        </h1>
      </div>
    </nav>
    <main class="main-setting">
      <div class="authentication-form">
        <div class="login-title fs-3">Login to DonDonmart</div>
        <div class="login-content">
          <div class="login-form d-flex flex-column">

            <form action="controller" method="post" onsubmit="return verify(this)">
              <input type="hidden" name="action" value="login">
              <div class="login-form-content">
                <div class="input full">
                  <label for="basic-url" class="form-label">Account</label>
                  <input
                          type="text"
                          class="form-control"
                          aria-label="Amount (to the nearest dollar)"
                          name="account"
                  />
                </div>
                <div class="input full">
                  <label for="basic-url" class="form-label">Password</label>
                  <input
                          type="password"
                          class="form-control"
                          aria-label="Amount (to the nearest dollar)"
                          name="password"
                  />
                </div>
              </div>

              <input class="btn-cf btn-cf-primary btn btn-primary full" type="submit" value="login">

            </form>

            <p class="alternative-option">
              Don't have an account?
              <a
                class="a-inline"
                href="https://cty0305.github.io/SP_E-commerce/signup.html"
                >Sign up</a
              >
            </p>
          </div>
        </div>
      </div>
    </main>
    <script src="resource/all.js"></script>
  </body>
</html>
