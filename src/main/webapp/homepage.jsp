<%@ page import="java.util.ArrayList" %>
<%@ page import="layer.domain.goods.Goods" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-tw">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DonDonmart Homepage</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    />
    <link rel="stylesheet" href="resource/css/all.css">

  </head>
  <body>
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
        <section class="header-auth-btn-container">
          <div class="header-auth-btn">
            <a
              class="btn-cf btn-cf-secondary"
              href="/login_mart.jsp"
            >
              Log in
            </a>
            <a
              class="btn-cf btn-cf-primary"
              href="/signUpEmailVerification_mart.jsp"
            >
              Sign up
            </a>
          </div>
        </section>
        <!-- <section class="header-menu">
          <a
            href="https://cty0305.github.io/SP_E-commerce/cart.html"
            class="header-cart"
          >
            <span class="material-symbols-outlined"> shopping_bag </span>Cart: 2
          </a>
          <div class="header-user-controller">
            <button type="button" class="header-user" id="toggleButton">
              <span class="header-user-name"> Alan Chou </span>
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
        </section> -->
      </div>
    </nav>
    <div class="main">
      <div class="main-content">
        <div class="sidemenu">
          <div class="sidemenu-content">
            <h3 class="sidemenu-title">Explore</h3>
            <ul class="list-group">
              <li class="list-group-item">
                <a href="#" class="category-tab">
                  <img
                    width="36"
                    height="36"
                    src="https://img.icons8.com/fluency/96/flash-on.png"
                    alt="flash-on"
                    class="category-icon"
                  />New In
                </a>
              </li>
              <li class="list-group-item">
                <a href="#" class="category-tab">
                  <img
                    width="36"
                    height="36"
                    src="https://img.icons8.com/emoji/48/coat-emoji.png"
                    alt="coat-emoji"
                    class="category-icon"
                  />Clothing
                </a>
              </li>
              <li class="list-group-item">
                <a href="#" class="category-tab">
                  <img
                    width="36"
                    height="36"
                    src="https://img.icons8.com/external-photo3ideastudio-flat-photo3ideastudio/64/external-shoes-clothes-photo3ideastudio-flat-photo3ideastudio.png"
                    alt="external-shoes-clothes-photo3ideastudio-flat-photo3ideastudio"
                    class="category-icon"
                  />Shoes
                </a>
              </li>
              <li class="list-group-item">
                <a href="#" class="category-tab">
                  <img
                    width="36"
                    height="36"
                    src="https://img.icons8.com/offices/40/necklace.png"
                    alt="necklace"
                    class="category-icon"
                  />Accessories
                </a>
              </li>
              <li class="list-group-item">
                <a href="#" class="category-tab">
                  <img
                    width="36"
                    height="36"
                    src="https://img.icons8.com/plasticine/100/tracksuit.png"
                    alt="tracksuit"
                    class="category-icon"
                  />Activewaer
                </a>
              </li>
              <li class="list-group-item">
                <a href="#" class="category-tab">
                  <img
                    width="36"
                    height="36"
                    src="https://img.icons8.com/color/48/gift--v1.png"
                    alt="gift--v1"
                    class="category-icon"
                  />Gift & Living
                </a>
              </li>
              <li class="list-group-item">
                <a href="#" class="category-tab">
                  <img
                    width="36"
                    height="36"
                    src="https://img.icons8.com/color/48/light.png"
                    alt="light"
                    class="category-icon"
                  />Inspiration
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="shopping-aria">
          <div class="shopping-aria-header">
            <div class="category">
              <img
                width="36"
                height="36"
                src="https://img.icons8.com/fluency/96/flash-on.png"
                alt="flash-on"
              />
              <h3 class="category-name">New In</h3>
            </div>
            <div class="filter-set">
              <div class="filter-sort-controller">
                <button class="filter-sort" type="button" id="filter-sort">
                  <span class="filter-icon material-symbols-outlined">
                    sort
                  </span>
                  <div class="filter-sort-text">商品排序</div>
                  <span class="material-symbols-outlined">
                    keyboard_arrow_down
                  </span>
                </button>
                <ul class="filter-sort-menu-show">
                  <li class="filter-sort-option"><a href="controller?action=sortList&sortBy=NewestToOldest">時間：由新到舊</a></li>
                  <li class="filter-sort-option"><a href="controller?action=sortList&sortBy=OldestToNewest">時間：由舊到新</a></li>
                  <li class="filter-sort-option"><a href="controller?action=sortList&sortBy=LowestToHighest">價格：由低至高</a></li>
                  <li class="filter-sort-option"><a href="controller?action=sortList&sortBy=HighestToLowest">價格：由高至低</a></li>
                </ul>
              </div>
              <div class="filter-toggle">
                <button
                  class="filter-button filter-toggle-women"
                  type="button"
                  id="filter-toggle-women"
                >
                  <span class="filter-icon imoji-women"
                    >&#x1F64B;&#x200D;&#x2640;&#xFE0F;</span
                  >
                  Women
                </button>
                <button
                  class="filter-button filter-toggle-men inactive"
                  type="button"
                  id="filter-toggle-men"
                >
                  <span class="filter-icon imoji-men"
                    >&#x1F64B;&#x200D;&#x2642;&#xFE0F;</span
                  >
                  Men
                </button>
              </div>
            </div>
          </div>



          <!--產品列表-->
          <div class="product-window">

            <%
              ArrayList<Goods> goodsList = (ArrayList<Goods>) request.getAttribute("goodsList");
            %>

            <c:forEach var="i" begin="1" end="<%= (int)Math.ceil(goodsList.size()/4.0) %>" varStatus="status1">
              <div class="product-row">

                <c:choose>
                  <c:when test="${status1.index == Math.ceil(goodsList.size()/4.0)}">
                    <c:forEach var="j" begin="${(status1.index -1)*4 +1}" end="${goodsList.size()}" varStatus="status2">



                      <a href="controller?action=product&goods_ID=${goodsList[j-1].getGoods_ID()}" class="product-clickable">
                        <div class="product-card">
                          <div class="product-card-image-container">
                            <img src="https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/78093c5f-19cf-4462-834c-a3e46de38386/dunk-%E4%BD%8E%E7%AD%92%E6%AC%BE-Bgkn3N.png" alt="" class="product-card-image"/>
                          </div>
                          <div class="product-card-info">
                            <div class="product-card-name">
                              <c:out value="${goodsList[j-1].getName()}" />
                            </div>
                            <div class="product-card-content">
                              <span class="product-card-sex">Women</span>
                              <span class="product-card-price"><c:out value="${goodsList[j-1].getPrice()}" /></span>
                            </div>
                          </div>
                        </div>
                      </a>
                    </c:forEach>
                  </c:when>
                  <c:otherwise>
                    <c:forEach var="j" begin="${(status1.index -1)*4 +1}" end="${(status1.index-1)*4+4}" varStatus="status2">

                      <a href="controller?action=product&goods_ID=${goodsList[j-1].getGoods_ID()}" class="product-clickable">
                        <div class="product-card">
                          <div class="product-card-image-container">
                            <img src="https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/78093c5f-19cf-4462-834c-a3e46de38386/dunk-%E4%BD%8E%E7%AD%92%E6%AC%BE-Bgkn3N.png" alt="" class="product-card-image"/>
                          </div>
                          <div class="product-card-info">
                            <div class="product-card-name">
                              <c:out value="${goodsList[j-1].getName()}" />
                            </div>
                            <div class="product-card-content">
                              <span class="product-card-sex">Women</span>
                              <span class="product-card-price"><c:out value="${goodsList[j-1].getPrice()}" /></span>
                            </div>
                          </div>
                        </div>
                      </a>
                    </c:forEach>
                  </c:otherwise>
                </c:choose>

              </div>
            </c:forEach>

          </div>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://github.com/Cty0305/SP_E-commerce/blob/e6f011a3987b8e0d535f10a91f4bbfc103e2d893/JS/all.js"></script>




    <script>
      // 取得按鈕元素
      var myButton = document.getElementById('filter-sort');

      // 監聽 click 事件
      myButton.addEventListener('click', function() {
        // 在這裡執行當按鈕被點擊時的動作
        console.log('Button clicked!');
        // 你可以在這裡添加更多的程式碼，處理 click 事件
      });
    </script>
  </body>
</html>
