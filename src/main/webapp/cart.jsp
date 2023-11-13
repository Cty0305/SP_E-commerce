<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      console.log('${item.getCart_ID()}');

      function confirmDelete(k){
        console.log("確定刪除?");
        const isConfirmed = confirm("確定要刪除嗎?");


        if(isConfirmed){
          deleteItem(k);
        }else {

        }
      }
      function deleteItem(n){
        window.location.href = "controller?action=deleteCartItem&cart_ID="+ n;
      }
    </script>
    <nav class="navbar justify-content-center align-items-center">
      <div class="header d-flex justify-content-between align-items-center">
        <h1 class="brand">
          <a
            href="https://cty0305.github.io/SP_E-commerce/"
            class="header-logo-container d-flex flex-column justify-content-center align-items-center"
          >
            <img
              src="https://github.com/Cty0305/SP_E-commerce/blob/main/Resource/Image/Brand-Logo.jpg?raw=true"
              class="header-logo"
            />
          </a>
          <span class="brand-name visually-hidden">DonDonmart</span>
        </h1>

        <!--搜尋框 -->
        <form
          class="search-container d-flex mb-3 mb-lg-0 align-items-center"
          role="search"
        >
          <input
            type="search"
            class="search form-control border-0 py-3"
            placeholder="Search for items, brands and ispiration ..."
            aria-label="Search"
          />
          <!-- <span class="material-symbols-outlined icon-search text-body-secondary">
              search
              </span> -->
        </form>



        <!--資訊欄  -->
        <section class="d-flex justify-content-between gap-3">
          <!-- 購物車按鈕-->
          <button
            type="button"
            class="header-cart d-flex btn btn-primary bg-transparent text-black border-0 align-items-center gap-2 py-3"
          >
            <span class="material-symbols-outlined"> shopping_cart </span>Cart:
            2
          </button>

          <!-- 帳號按鈕-->
          <button
            type="button"
            class="header-user btn btn-primary bg-transparent text-black border-0 py-3 d-flex align-items-center gap-1 justify-content-between"
          >
            <img
              src="https://github.com/Cty0305/SP_E-commerce/blob/main/Resource/Image/Avatar.jpg?raw=true"
              alt=""
              class="avatar"
            />
            Hello Mr. Coleman
            <span class="material-symbols-outlined"> keyboard_arrow_down </span>
          </button>
          <!-- <div class="header-user rounded-3 border">
            <ul class="header-user">
              <li class="option mb-3">
                <a href=""
                  ><span class="material-symbols-outlined me-2"> settings </span
                  >Setting</a
                >
              </li>
              <li class="option mb-3">
                <a href=""
                  ><span class="material-symbols-outlined me-2"> logout </span
                  >Log out</a
                >
              </li>
            </ul>
          </div> -->
        </section>
      </div>
    </nav>
    <main>
      <div class="cart-main">
        <div class="cart-item">
          <div class="cart-title fs-4">購物車</div>

          <!--設定總價統計變數-->
          <c:set var="totalPrice" value="0" />


          <c:forEach var="item" items="${cartList}" varStatus="loop">
            <a class="cart-card">
              <div class="cart-pd-cover">
                <img
                  src="https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/78093c5f-19cf-4462-834c-a3e46de38386/dunk-%E4%BD%8E%E7%AD%92%E6%AC%BE-Bgkn3N.png"
                  alt=""
                  class="cart-pd-cover-image"
                />
              </div>
              <div class="cart-card-info">
                <div class="cart-card-title">${item.getGoods().getName()}</div>
                <div class="cart-card-subtitle">${item.getGoods().getCategory()}</div>
                <div class="cart-card-spec">白/白/黑</div>
                <div class="cart-card-qty">
                  數量${item.getGoods().getQuantity()}
                  <span class="material-symbols-outlined">
                    keyboard_arrow_down
                  </span>
                </div>
                <span><button onclick="console.log('Button clicked!'); confirmDelete('<c:out value="${item.getCart_ID()}"/>')"> delete </button></span>
              </div>
              <div class="cart-card-price" id="cartItemPrice${loop.index}">$${item.getGoods().getPrice() * item.getGoods().getQuantity()}</div>
              <c:set var="totalPrice" value="${totalPrice + item.getGoods().getPrice() * item.getGoods().getQuantity()}"/>
            </a>
          </c:forEach>



        </div>



        <div class="cart-payment">
          <div class="cart-payment-title fs-4">摘要</div>
          <div class="cart-payment-content">
            <div class="cart-payment-total">總計</div>
            <div class="cart-payment-total-num">
              $${totalPrice}
            </div>
          </div>
          <div class="cart-favorite">會員結帳</div>
        </div>
      </div>
    </main>
  </body>
</html>
