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
  <!--避免Email格式錯誤-->
  <script>
    function verify(myform){
      let errMsg ="";
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
        <div class="login-title fs-3">Sign up to DonDonmart</div>
        <div class="login-content">
          <div class="login-form d-flex flex-column">

            <!--提交Email-->

            <form action="controller" method="post" onsubmit="return verify(this)">
              <input type="text" name="action" value="signUpEmailVerification" style="display: none">
              <div class="login-form-content">
                <div class="input full">
                  <label for="basic-url" class="form-label">Email</label>
                  <input
                    type="email"
                    class="form-control"
                    aria-label="Amount (to the nearest dollar)"
                    name="email"
                  />
                </div>
              </div>
              <input class="btn-cf btn-cf-primary full" type="submit" value="Next">
            </form>

            <p class="alternative-option">
              Already have an account?
              <a
                class="a-inline"
                href="https://cty0305.github.io/SP_E-commerce/login.html"
                >Log in</a
              >
            </p>
          </div>
        </div>
      </div>
    </main>
    <script src="JS/all.js"></script>
  </body>
</html>
