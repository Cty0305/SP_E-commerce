document.addEventListener("DOMContentLoaded", function () {
  const toggleButton = document.getElementById("toggleButton");
  const headerUser = document.querySelector(".header-user-dropdown");

  toggleButton.addEventListener("click", function () {
    headerUser.classList.toggle("show");
  });
});

// Filter-Sort 下拉選單
$(".filter-sort").click(function () {
  $(".filter-sort-menu").toggleClass("filter-sort-menu-show");
});

$(document).on("click", function (event) {
  if (!$(event.target).closest(".filter-sort-controller").length) {
    $(".filter-sort-menu").removeClass("filter-sort-menu-show");
  }
});

$(".filter-sort-option").click(function (e) {
  e.preventDefault();
  var optionText = $(this).text();
  $(".filter-sort-text").text(optionText);
  $(".filter-sort-menu").removeClass("show");
});

// Filter-toggle-sex
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

$(".product-spec-option").click(function () {
  $(".product-spec-option").removeClass("product-spec-option-active");
  $(this).addClass("product-spec-option-active");
});

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
