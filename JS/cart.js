// 刪除購物車商品;
export function deleteCartitem() {
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
}
