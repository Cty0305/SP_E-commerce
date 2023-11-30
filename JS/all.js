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
