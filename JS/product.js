// Product Page - Product Spec Options👇

export function productAddtocart() {
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
  document.addEventListener("DOMContentLoaded", function () {
    const addToCartBtn = document.getElementById("addToCartButton");
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
        $(".cart-pop-up, .overlay").removeClass(
          "cart-pop-up-show overlay-active"
        );
      }
    });
  });
}
