// // Page Layout - User Dropdown 👇
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

// // Page Layout - Sidemenu👇
$(".list-group-item").click(function () {
  $(".list-group-item").removeClass("list-group-item-active");
  $(this).toggleClass("list-group-item-active");
});

// // Homepage Page - Sort Dropdown👇
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

$(document).ready(function () {
  const sizeTitle = document.querySelector(".product-spec-title");
  const sizeContainer = document.querySelector(
    ".product-spec-option-container"
  );
  const message = document.querySelector(".message");

  $(".product-spec-option").click(function () {
    $(this)
      .addClass("product-spec-option-active")
      .siblings()
      .removeClass("product-spec-option-active");
    sizeTitle.style.color = "black";
    sizeContainer.style.border = "none";
    message.style.display = "none";
  });
});

// 获取所有带有 name="size" 的标签
const specLabels = document.querySelectorAll('input[name="size"]');
const addToCartBtn = document.getElementById("addToCartButton");

// 添加点击事件监听器到每个标签
specLabels.forEach((label) => {
  label.addEventListener("click", () => {
    const input = label;
    if (input) {
      input.checked = true; // 点击标签时手动选中关联的输入元素
    }
  });
});

// 点击 "Add to Cart" 按钮时执行的操作
addToCartBtn.addEventListener("click", function (event) {
  const isSpecSelected = validateSpec();
  const sizeTitle = document.querySelector(".product-spec-title");
  const sizeContainer = document.querySelector(
    ".product-spec-option-container"
  );
  const message = document.querySelector(".message");

  if (!isSpecSelected) {
    sizeTitle.style.color = "var(--bs-red)";
    sizeContainer.style.border = "1px solid var(--bs-red)";
    message.style.display = "block";
    event.preventDefault(); // 阻止默认行为
  } else {
    sizeTitle.style.color = "black";
    sizeContainer.style.border = "none";
    message.style.display = "none";
    showCartPopup();
  }
});

function validateSpec() {
  // 检查是否有任何一个输入元素被选中
  const isAnySelected = Array.from(specLabels).some((label) => {
    const input = label;
    const isChecked = input && input.checked;
    return isChecked;
  });

  return isAnySelected;
}

// 顯示購物車彈出視窗

function showCartPopup() {
  $(".cart-pop-up").addClass("cart-pop-up-show").fadeIn(1000);
  setTimeout(function () {
    $(".overlay").addClass("overlay-active").fadeIn(1000);
  }, 100); // 延遲添加 overlay-active 類別

  setTimeout(function () {
    $(".cart-pop-up, .overlay").fadeOut(500);
  }, 6000);
}

$(".close, .remove, .cancel").click(function () {
  $(".cart-pop-up, .overlay")
    .removeClass("cart-pop-up-show overlay-active")
    .fadeOut(500);
});

$(document).on("click", function (event) {
  if (
    !$(event.target).closest(".cart-pop-up").length &&
    !$(event.target).is(addToCartBtn)
  ) {
    $(".cart-pop-up, .overlay").removeClass("cart-pop-up-show overlay-active");
  }
});

// 刪除購物車商品;
$(document).ready(function () {
  $(".cart-card-delete-item").click(function (event) {
    event.stopPropagation(); // 防止事件冒泡到 document
    $(".pop-up").addClass("pop-up-show");
    setTimeout(function () {
      $(".overlay").addClass("overlay-active");
    }, 100); // 延遲添加 overlay-active 類別
  });

  $(document).on("click", function (event) {
    if (
      !$(event.target).closest(".pop-up").length &&
      !$(event.target).hasClass("cart-card-delete-item")
    ) {
      $(".pop-up").removeClass("pop-up-show");
      $(".overlay").removeClass("overlay-active");
    }
  });

  $(".close, .remove, .cancel").click(function () {
    $(".pop-up").removeClass("pop-up-show");
    $(".overlay").removeClass("overlay-active");
  });
});

$(".owl-carousel").owlCarousel({
  loop: false, // 循環播放
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
      items: 4, // 螢幕大小為 1000 以上 顯示 5 個項目
    },
  },
});
