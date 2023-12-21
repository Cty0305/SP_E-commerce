// Homepage Page - Sort Dropdown👇
export function filterSort() {
  $(".filter-sort").click(function () {
    $(".filter-sort-menu").toggleClass("filter-sort-menu-show");
  });

  $(".filter-sort-option").click(function (e) {
    e.preventDefault();
    var optionText = $(this).text();
    $(".filter-sort-text").text(optionText);
    $(".filter-sort-menu").removeClass("filter-sort-menu-show");
  });

  $(document).on("click", function (event) {
    if (!$(event.target).closest(".filter-sort-controller").length) {
      $(".filter-sort-menu").removeClass("filter-sort-menu-show");
    }
  });
}

// Homepage Page - Sex Toggle Filter👇
export function sextoggle() {
  document.addEventListener("DOMContentLoaded", function () {
    const filterToggleWomen = document.getElementById("filter-toggle-women");
    const filterToggleMen = document.getElementById("filter-toggle-men");

    // 確認元素存在後再綁定事件
    if (filterToggleWomen && filterToggleMen) {
      filterToggleWomen.addEventListener("click", toggleInactiveState);
      filterToggleMen.addEventListener("click", toggleInactiveState);
    } else {
    }

    function toggleInactiveState() {
      if (filterToggleWomen && filterToggleMen) {
        filterToggleWomen.classList.toggle("inactive");
        filterToggleMen.classList.toggle("inactive");
      }
    }
  });
}
