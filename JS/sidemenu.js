// // Page Layout - Sidemenu👇
export function sidemenuClick() {
  $(".list-group-item").click(function () {
    $(".list-group-item").removeClass("list-group-item-active");
    $(this).toggleClass("list-group-item-active");
  });
}
