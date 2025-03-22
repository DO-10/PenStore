$(document).ready(function () {
    // 提交回复按钮点击事件
    $('#submit-reply').on('click', function (event) {
        event.preventDefault(); // 阻止表单默认提交行为

        const content = $('#content').val().trim();
        console.log('submit 方法被调用');

        if (!content) {
            alert('请输入内容');
            return;
        }

        const payload = {
            id: generateUUID(), // 生成唯一 ID
            user_id: userId, // 使用注入的用户 ID
            goodsId:goodsId,
            content: content,
            parentId: parentComment.id // 使用父评论的 ID
        };

        // 发送 POST 请求
        $.ajax({
            url: '/api/comments/reply/submit',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(payload),
            success: function (response) {
                alert('回复提交成功');
                window.location.href = `/customerService`;
            },
            error: function (xhr, status, error) {
                console.error('提交失败:', error);
                alert('回复提交失败，请重试');
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