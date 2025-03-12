$(document).ready(function() {
    $('#getVerificationCode').on('click', function () {


        // 获取表单数据
        var email = $('#email').val();

        // 使用 jQuery 的 Ajax 发送请求
        $.ajax({
            url: '/verify', // 后端接口 URL
            type: 'POST',
            data: {
                email: email,
            },
            success: function (response) {
                // 假设后端返回验证码信息
                var verificationCode = response.verificationCode;

                // 显示验证码信息
                $('#verificationCodeMessage').text("验证码已发送: " + verificationCode);
            },
            error: function (xhr, status, error) {
                console.log('请求失败：' + error);
            }
        });
    });
});