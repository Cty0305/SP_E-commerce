// Page Layout - User Dropdown 👇
document.addEventListener("DOMContentLoaded", function () {
  const toggleButton = document.getElementById("toggleButton");
  const headerUser = document.querySelector(".header-user-dropdown");

  toggleButton.addEventListener("click", function () {
    headerUser.classList.toggle("show");
  });
});


$(document).on("click", function (event) {
  if (!$(event.target).closest(".header-user-controller").length) {
    $(".header-user-dropdown").removeClass("show");
  }
});

// Page Layout - Sidemenu👇
$(".list-group-item").click(function () {
  $(".list-group-item").removeClass("list-group-item-active");
  $(this).toggleClass("list-group-item-active");
});

// Homepage Page - Sort Dropdown👇
$(".filter-sort").click(function () {
  $(".filter-sort-menu").toggleClass("filter-sort-menu-show");
});

$(".filter-sort-option").click(function (e) {
  e.preventDefault();
  var optionText = $(this).text();
  $(".filter-sort-text").text(optionText);
  $(".filter-sort-menu").removeClass("show");
});

$(document).on("click", function (event) {
  if (!$(event.target).closest(".filter-sort-controller").length) {
    $(".filter-sort-menu").removeClass("filter-sort-menu-show");
  }
});

// Homepage Page - Sex Toggle Filter👇
document.addEventListener("DOMContentLoaded", function () {
  const filterToggleWomen = document.getElementById("filter-toggle-women");
  const filterToggleMen = document.getElementById("filter-toggle-men");

  filterToggleWomen.addEventListener("click", toggleInactiveState);
  filterToggleMen.addEventListener("click", toggleInactiveState);

  function toggleInactiveState() {
    filterToggleWomen.classList.toggle("inactive");
    filterToggleMen.classList.toggle("inactive");
  }
});

// Product Page - Product Spec Options👇
$(".product-spec-option").click(function () {
  $(".product-spec-option").removeClass("product-spec-option-active");
  $(this).addClass("product-spec-option-active");
});

// Product Page - Add To Cart Button👇
$(".add-to-cart").click(function () {
  if ($(this).text() === "加入購物車") {
    $(this).animate({ opacity: 0 }, 500, function () {
      $(this).text("✅ 已加入至購物車").animate({ opacity: 1 }, 500);
    });
  } else {
    $(this).animate({ opacity: 0 }, 500, function () {
      $(this).text("加入購物車").animate({ opacity: 1 }, 500);
    });
  }
});

// Product Page - Related Product Carousel👇
$(".owl-carousel").owlCarousel({
  loop: true, // 循環播放
  margin: 12, // 外距 10px
  nav: true, // 顯示點點
  dots: false,
  responsive: {
    0: {
      items: 1, // 螢幕大小為 0~600 顯示 1 個項目
    },
    600: {
      items: 3, // 螢幕大小為 600~1000 顯示 3 個項目
    },
    1000: {
      items: 3.5, // 螢幕大小為 1000 以上 顯示 5 個項目
    },
  },
});
