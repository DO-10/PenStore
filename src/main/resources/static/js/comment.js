$(document).ready(function () {
    let rating = 0;

    // 初始化评分点击事件
    $('.star-rating span').on('click', function () {
        const value = $(this).data('value');
        rating = value;
        $('.star-rating span').removeClass('active');
        $(this).prevAll().addBack().addClass('active');
    });

    // 提交评价按钮点击事件
    $('#submit-button').on('click', function (event) {
        event.preventDefault(); // 阻止表单默认提交行为

        const content = $('#content').val().trim();
        console.log('submit 方法被调用');

        if (rating === 0) {
            alert('请选择评分');
            return;
        }
        if (!content) {
            alert('请输入内容');
            return;
        }

        const payload = {
            id: generateUUID(), // 生成唯一 ID
            user_id: userId, // 使用注入的用户 ID
            goodsId: goodsId,
            star: rating,
            content: content,
            username: "test_user",
        };

        // 发送 POST 请求
        $.ajax({
            url: '/api/comments',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(payload),
            success: function (response) {
                alert('评论提交成功');
                window.location.href = `/goods/${goodsId}`; // 提交成功后跳转回商品页
            },
            error: function (xhr, status, error) {
                console.error('提交失败:', error);
                alert('评论提交失败，请重试');
            }
        });
    });

    // 生成唯一 ID 的方法
    function generateUUID() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            const r = Math.random() * 16 | 0;
            const v = c === 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }
});