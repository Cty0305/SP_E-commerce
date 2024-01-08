// export function loginStatus() {
//   let isLoggedIn = false;

//   document.addEventListener("DOMContentLoaded", function () {
//     const loginForm = document.getElementById("loginForm");

//     loginForm.addEventListener("submit", function (event) {
//       event.preventDefault(); // 阻止表单默认提交行为

//       // 在这里执行登录逻辑，验证用户的邮箱和密码

//       // 假设登录验证成功后
//       isLoggedIn = true;
//       console.log("User is logged in:", isLoggedIn);
//     });
//   });
// }

// 控制登录状态下的头部显示
// export function header() {
//   loggedinHeader.style.display = "none";
//   loggedoutHeader.style.display = "flex";

//   const loggedinHeader = document.getElementById("loggedin-header");
//   const loggedoutHeader = document.getElementById("loggedout-header");

//   if (loggedinHeader && loggedoutHeader) {
//     if (isLoggedIn) {
//       loggedinHeader.style.display = "flex";
//       loggedoutHeader.style.display = "none";
//     } else {
//       loggedinHeader.style.display = "none";
//       loggedoutHeader.style.display = "flex";
//     }
//   } else {
//     console.error("One or more header elements not found.");
//     // 这里可以添加适当的错误处理，比如给出一个警告或其他反馈
//   }
// }
