document.addEventListener("DOMContentLoaded", function () {
  const toggleButton = document.getElementById("toggleButton");
  const headerUser = document.querySelector(".header-user-dropdown");

  toggleButton.addEventListener("click", function () {
    headerUser.classList.toggle("show");
  });
});

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

// document.addEventListener("DOMContentLoaded", function () {
//   const filterSort = document.getElementById("filte-sort");
//   const filterSortMenu = document.querySelector(".filter-sort-menu");

//   filterSort.addEventListener("click", function () {
//     filterSortMenu.classList.toggle("filter-sort-menu-show");
//   });
// });
$(".filter-sort").click(function () {
  $(".filter-sort-menu").toggleClass("filter-sort-menu-show");
});
