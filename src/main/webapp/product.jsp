<%@ page import="layer.domain.goods.Goods" %>
<%@ page import="layer.survice.goodsServiceImp" %>
<%@ page import="layer.survice.goodsService" %>
<%@ page import="layer.survice.goodsItemService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-tw">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DonDonmart Homepage</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
            integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    ></script>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"
    />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
      integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
      integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
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
        <section class="header-menu">
          <a
            href="/controller?action=cart"
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
        </section>
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
          <div class="product-detail">
            <a
              href="https://cty0305.github.io/SP_E-commerce/homepage"
              class="backlink fs-6"
            >
              <span class="material-symbols-outlined"> arrow_back </span>Back
            </a>
            <div class="product-window-content">
              <div class="product-image-container">
                <img
                  src="https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/78093c5f-19cf-4462-834c-a3e46de38386/dunk-%E4%BD%8E%E7%AD%92%E6%AC%BE-Bgkn3N.png"
                  alt=""
                  class="product-image"
                />
              </div>
              <div class="product-content">
                <form action="controller" method="post">
                <%
                  String goodsId = (String) request.getAttribute("goods_ID");
                  goodsService goodsService = new goodsServiceImp();
                  Goods goods = goodsService.findByPk(goodsId);

                %>
                <h1 class="product-name"><%=goods.getName()%></h1>
                <div class="product-category"><%=goods.getCategory()%></div>
                <div class="product-price"><%=goods.getPrice()%></div>
                  <input type="hidden" name="action" value="add">
                  <input type="hidden" name="name" value="<%=goods.getName()%>">
                  <input type="hidden" name="category" value="<%=goods.getCategory()%>">
                  <input type="hidden" name="price" value="<%=goods.getPrice()%>>">
                  <input type="hidden" name="goods_ID" value="<%=goods.getGoods_ID()%>">

                <!-- 尺寸選擇 -->

                <div class="product-spec">
                  <div class="product-spec-title">選取尺寸</div>
                  <div class="product-spec-option-container">

                     <input type="hidden" name="action" value="add">
                     <input class="btn-cf btn-cf-secondary product-spec-option" type="radio" name="size" value="1" id="S">
                     <label for="S">S</label>
                     <input class="btn-cf btn-cf-secondary product-spec-option" type="radio" name="size" value="2" id="M">
                     <label for="M">M</label>
                     <input class="btn-cf btn-cf-secondary product-spec-option" type="radio" name="size" value="3" id="L">
                     <label for="L">L</label>








                  </div>
                </div>
                <div class="product-action">

                  <input type="submit" name="submit" value="加入購物車">
                  <input type="submit" name="submit" value="立即購買">

                </div>

                </form>

              </div>
            </div>
            <div class="raleted-product">
              <div class="related-product-header">
                <div class="related-product-title fs-6 fw-bolder">
                  你可能也會喜歡
                </div>
              </div>
              <ul
                class="related-product-container owl-carousel owl-theme"
                id="productContainer"
              >

                <c:forEach var="item" items="${goodsList}" varStatus="list">
                  <li class="item">
                    <a
                            class="related-product-card"
                            href="https://cty0305.github.io/SP_E-commerce/product.html"
                    >
                      <img
                              src="https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/78093c5f-19cf-4462-834c-a3e46de38386/dunk-%E4%BD%8E%E7%AD%92%E6%AC%BE-Bgkn3N.png"
                              class="related-product-image"
                              alt=""
                      />
                      <div class="related-product-info">
                        <div class="related-product-info-title">
                          ${item.getName()}
                        </div>
                        <div class="related-product-info-subtitle">${item.getCategory()}</div>
                        <div class="related-product-info-price">$${item.getPrice()}</div>
                      </div>
                    </a>
                  </li>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>


    <script src="resource\js\all.js"></script>
  </body>
</html>
