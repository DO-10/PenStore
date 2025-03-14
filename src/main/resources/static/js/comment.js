class CommentSubmitter {
    constructor() {
        this.rating = 0;
        this.initRating();
    }

    initRating() {
        document.querySelectorAll('.star-rating span').forEach(star => {
            star.addEventListener('click', () => {
                this.setRating(star.dataset.value);
            });
        });
    }

    setRating(rating) {
        this.rating = rating;
        document.querySelectorAll('.star-rating span').forEach((star, index) => {
            star.classList.toggle('active', index < rating);
        });
    }

    async submit() {
        const content = document.getElementById('content').value.trim();

        if (this.rating === 0 && parentId === null) {
            alert('请选择评分');
            return;
        }
        if (!content) {
            alert('请输入内容');
            return;
        }

        const payload = {
            id: crypto.randomUUID(),
            userId: getCurrentUserId(), // 从 Session 获取
            pop: parentId ? '2' : '1',
            goodsId: goodsId,
            star: parentId ? 0 : this.rating,
            content: content,
            parentId: parentId || null
        };

        try {
            const response = await fetch('/api/comments', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                window.location.href = `/goods/${goodsId}`; // 提交成功后跳转回商品页
            }
        } catch (error) {
            console.error('提交失败:', error);
        }
    }
}

// 初始化
document.addEventListener('DOMContentLoaded', () => {
    if (document.querySelector('.comment-container')) {
        new CommentSubmitter();

        // 如果是回复，显示提示
        if (parentId) {
            const indicator = document.createElement('div');
            indicator.className = 'reply-indicator';
            indicator.textContent = '您正在回复一条评论';
            document.querySelector('.comment-form').prepend(indicator);
        }
    }
});

function submitComment() {
    new CommentSubmitter().submit();
}