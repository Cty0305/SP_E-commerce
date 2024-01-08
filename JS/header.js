// 处理用户下拉菜单交互功能
export function headerUserDropdown() {
  document.addEventListener("DOMContentLoaded", function () {
    const toggleButton = document.getElementById("toggleButton");
    const headerUser = document.querySelector(".header-user-dropdown");

    if (toggleButton && headerUser) {
      toggleButton.addEventListener("click", function () {
        headerUser.classList.toggle("show");
      });
    } else {
      console.error("Toggle button or header user dropdown element not found.");
      // 这里可以添加适当的错误处理，比如给出一个警告或其他反馈
    }
  });

  $(document).on("click", function (event) {
    if (!$(event.target).closest(".header-user-controller").length) {
      $(".header-user-dropdown").removeClass("show");
    }
  });
}
