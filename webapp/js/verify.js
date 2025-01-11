document.getElementById("getVerificationCode").addEventListener("click", function() {

    var email = document.querySelector('input[id="email"]').value;  // 获取邮箱字段的值
    if (!email) {
        alert("请输入有效的邮箱地址！");
        return;
    }

    // 发送 AJAX 请求获取验证码
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "sendVerificationCodeServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function() {
        if (xhr.status === 200) {
            // 假设返回的结果为一个成功信息
            alert("验证码已发送到您的邮箱，请查收！");
        } else {
            alert("验证码发送失败，请稍后再试！");
        }
    };
    xhr.send("email=" + encodeURIComponent(email) + "&action=register");

});