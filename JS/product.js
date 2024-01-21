export function productAddtocart() {
  $(document).ready(function () {
    const sizeTitle = document.querySelector(".product-spec-title");
    const sizeContainer = document.querySelector(
      ".product-spec-option-container"
    );
    const message = document.querySelector(".message");
    const specLabels = document.querySelectorAll('input[name="size"]');
    const addToCartBtn = document.getElementById("addToCartButton");
    const buyNow = document.getElementById("buynow");

    $(".product-spec-option").click(function () {
      $(this)
        .addClass("product-spec-option-active")
        .siblings()
        .removeClass("product-spec-option-active");
      sizeTitle.style.color = "black";
      sizeContainer.style.border = "none";
      message.style.display = "none";
    });

    specLabels.forEach((label) => {
      label.addEventListener("click", () => {
        const input = label;
        if (input) {
          input.checked = true;
        }
      });
    });

    addToCartBtn.addEventListener("click", function (event) {
      handleButtonClick(event);
    });

    buyNow.addEventListener("click", function (event) {
      handleBuyNowClick(event);
    });

    function handleButtonClick(event) {
      const isSpecSelected = validateSpec();
      if (!isSpecSelected) {
        updateStyleForNoSpecSelected();
        event.preventDefault();
      } else {
        sizeTitle.style.color = "black";
        sizeContainer.style.border = "none";
        message.style.display = "none";
        showCartPopup();
      }
    }

    function handleBuyNowClick(event) {
      const isSpecSelected = validateSpec();
      if (!isSpecSelected) {
        updateStyleForNoSpecSelected();
        event.preventDefault();
      } else {
        sizeTitle.style.color = "black";
        sizeContainer.style.border = "none";
        message.style.display = "none";
        window.location.href =
          "https://cty0305.github.io/SP_E-commerce/checkout";
      }
    }

    function validateSpec() {
      const isAnySelected = Array.from(specLabels).some((label) => {
        const input = label;
        const isChecked = input && input.checked;
        return isChecked;
      });
      return isAnySelected;
    }

    function updateStyleForNoSpecSelected() {
      sizeTitle.style.color = "var(--bs-red)";
      sizeContainer.style.border = "1px solid var(--bs-red)";
      message.style.display = "block";
    }

    // 顯示購物車彈出視窗

    function showCartPopup() {
      $(".header-cart-pop-up").addClass("header-cart-pop-up-show").fadeIn(1000);
      setTimeout(function () {
        $(".overlay").addClass("overlay-active").fadeIn(1000);
      }, 100); // 延遲添加 overlay-active 類別

      setTimeout(function () {
        $(".header-cart-pop-up, .overlay").fadeOut(500);
      }, 6000);
    }

    $(".close, .remove, .cancel").click(function () {
      $(".header-cart-pop-up, .overlay")
        .removeClass("header-cart-pop-up-show overlay-active")
        .fadeOut(500);
    });

    $(document).on("click", function (event) {
      if (
        !$(event.target).closest(".header-cart-pop-up").length &&
        !$(event.target).is(addToCartBtn)
      ) {
        $(".header-cart-pop-up, .overlay").removeClass(
          "header-cart-pop-up-show overlay-active"
        );
      }
    });
  });
}
