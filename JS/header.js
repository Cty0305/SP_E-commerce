// // Page Layout - User Dropdown 👇
export function headerUserDropdown() {
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
}
