export function checkoutForm() {
  document.addEventListener("DOMContentLoaded", function () {
    const cartNumberInput = document.getElementById("cardNumber");
    const expirationInput = document.getElementById("expiration");

    cartNumberInput.addEventListener("input", function (event) {
      const input = event.target;
      let trimmedValue = input.value.replace(/\s/g, ""); // 移除所有空格
      trimmedValue = trimmedValue.replace(/\D/g, ""); // 移除非數字字符

      // 每輸入4碼後自動添加一個空格
      if (trimmedValue.length > 0) {
        trimmedValue = trimmedValue.match(new RegExp(".{1,4}", "g")).join(" ");
      }

      input.value = trimmedValue;
    });

    expirationInput.addEventListener("input", function (event) {
      const input = event.target;
      let trimmedValue = input.value.replace(/\D/g, ""); // 移除非數字字符

      // 每輸入2碼後自動添加一個" / "
      if (trimmedValue.length > 0) {
        trimmedValue = trimmedValue
          .match(new RegExp(".{1,2}", "g"))
          .join(" / ");
      }

      input.value = trimmedValue;
    });
  });
}
