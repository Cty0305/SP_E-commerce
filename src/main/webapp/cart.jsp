<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="layer.domain.goods.Cart" %>
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
    <script src="resource/JS/all.js"></script>

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
    <nav class="navbar">
      <div class="header">
        <h1 class="brand">
          <a
            href="https://cty0305.github.io/SP_E-commerce/homepage"
            class="header-logo-container"
          >
            <img
              src="https://github.com/Cty0305/SP_E-commerce/blob/main/Resource/Image/Brand-Logo.jpg?raw=true"
              class="header-logo"
            />
          </a>
          <span class="brand-name">DonDonmart</span>
        </h1>
        <!-- <section class="header-auth-btn-container">
          <div class="header-auth-btn">
            <a
              class="btn-cf btn-cf-secondary"
              href="https://cty0305.github.io/SP_E-commerce/login.html"
            >
              Log in
            </a>
            <a
              class="btn-cf btn-cf-primary"
              href="https://cty0305.github.io/SP_E-commerce/signup.html"
            >
              Sign up
            </a>
          </div>
        </section> -->
        <%
          int cartNum = ((ArrayList)request.getAttribute("cartList")).size();
        %>
        <section class="header-menu">
          <a
            href="controller?action=cart"
            class="header-cart"
          >
            <span class="material-symbols-outlined"> shopping_bag </span>Cart: ${cartList.size()}
          </a>
          <div class="header-user-controller">
            <button type="button" class="header-user" id="toggleButton">
              <span class="header-user-name"> <%=request.getSession().getAttribute("loggedInCustomerAccount")%> </span>
              <span class="material-symbols-outlined">
                keyboard_arrow_down
              </span>
            </button>
            <ul class="header-user-dropdown">
              <li class="header-user-dropdown-option">
                <a href="https://cty0305.github.io/SP_E-commerce/profile.html"
                  ><span class="material-symbols-outlined me-2"> settings </span
                  >Setting</a
                >
              </li>
              <li class="header-user-dropdown-option">
                <a href=""
                  ><span class="material-symbols-outlined me-2"> logout </span
                  >Log out</a
                >
              </li>
            </ul>
          </div>
        </section>
      </div>
    </nav>
    <div class="main">
      <div class="main-content">
        <div class="cart">
          <h3 class="cart-title">購物車</h3>


          <c:set var="totalPrice" value="0" />
          <c:set var="deliverExp" value="300" />


          <div class="cart-card-container">
          <c:forEach var="item" items="${cartList}" varStatus="loop">
              <a class="cart-card">
                <div class="cart-card-image-container">
                  <img
                    src="https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/78093c5f-19cf-4462-834c-a3e46de38386/dunk-%E4%BD%8E%E7%AD%92%E6%AC%BE-Bgkn3N.png"
                    alt=""
                    class="cart-card-image"
                  />
                </div>
                <div class="cart-card-info">
                  <div class="cart-card-title">${item.getGoods().getName()}</div>
                  <div class="cart-card-subtitle">Shoes</div>
                  <div class="cart-card-spec">${item.transSizeToText(item.getGoodsItem().getSize())}</div>
                  <select class="cart-card-qty optionClass" data-price="${item.getGoods().getPrice()}">
                    <c:forEach begin="1" end="10" step="1" varStatus="option">

                      <c:choose>
                        <c:when test="${option.index == item.getQuantity()}">
                          <option value="${option.index}"  selected>${option.index}</option>
                        </c:when>
                        <c:otherwise>
                          <option  value="${option.index}">${option.index}</option>
                        </c:otherwise>
                      </c:choose>


                    </c:forEach>
                  </select>
                  <span class="cart-card-delete-item material-symbols-outlined">
                    <button onclick="console.log('Button clicked!'); confirmDelete('<c:out value="${item.getCart_ID()}"/>')"> delete </button>
                  </span>
                </div>
                <div class="cart-card-price">$<span class="itemPrice" id="itemPrice${loop.index}">${item.getQuantity() * item.getGoods().getPrice()}</span></div>
                <c:set var="totalPrice" value="${totalPrice+item.getGoods().getPrice()*item.getQuantity()}"></c:set>
              </a>
          </c:forEach>
          </div>
        </div>




        <div class="calculator">
          <h3 class="calculator-title">摘要</h3>
          <div class="calculator-content">
            <div class="calculator-subtotal">
              <div class="calculator-subtotal-title">小計</div>
              <div class="calculator-subtotal-price">$<span id="totalPrice">${totalPrice}</span></div>
            </div>
            <div class="calculator-freight">
              <div class="calculator-freight-title">預估運費與手續費</div>
              <div class="calculator-freight-price" >$<span id="deliverExp">${deliverExp}</span></div>
            </div>
          </div>
          <div class="calculator-result">
            <div class="calculator-total">總計</div>
            <div class="calculator-total-num" >$<span id="totalPriceNum">${totalPrice+deliverExp}</span></div>
          </div>
        </div>
      </div>
    </div>

  <!-- ... 其他 HTML 代码 ... -->

  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      // 获取所有带有特定类名的选择框
      var selectElements = document.querySelectorAll(".optionClass");
      var totalPriceElement = document.getElementById("totalPrice");
      var itemPrice = document.getElementById("itemPrice");
      var totalPriceNum = document.getElementById("totalPriceNum");
      var deliverExp = document.getElementById("deliverExp");
      // 对每个选择框添加事件监听器
      selectElements.forEach(function(selectElement) {

        selectElement.addEventListener("change", function () {
          // 输出调试信息
          console.log("Select changed!");
          // 重新计算总价
          var total = 0;

          // 遍历所有选择框，累加它们的值
          selectElements.forEach(function(element) {
            var selectedValue = parseInt(element.value);
            var price = parseInt(element.getAttribute("data-price"));
            //获取包含 itemPrice 类的父元素
            var parentElement = element.closest('.cart-card');

            // 从父元素中选择相应的 itemPrice 元素
            var itemPriceElement = parentElement.querySelector('.itemPrice');

            itemPriceElement.innerHTML = selectedValue * price;
            // console.log("price="+price);
            // console.log(selectedValue+"is selectvalue")
            total += selectedValue * price;
          });

          // // 输出调试信息
          // console.log("Total price: " + total);

          var subtotal = parseInt(deliverExp.innerHTML) + total;

          // 更新总价显示
          totalPriceElement.innerHTML = total.toFixed(2);
          totalPriceNum.innerHTML = subtotal.toFixed(2);
        });
      });
    });
  </script>

</body>
</html>
