$(document).ready(function() {
    $('#searchInput').on('input', function () {
        const searchValue = $(this).val();
        if (searchValue.length > 0) { // 如果输入框有内容
            $.ajax({
                url: 'goods/searchInputServlet',
                type: 'GET',
                data: {q: searchValue},
                dataType: 'json',
                success: function (data) {
                    const suggestions = $('#suggestions');
                    suggestions.empty(); // 清空之前的内容
                    if (data.length > 0) {
                        suggestions.show(); // 显示建议列表
                        data.forEach(item => {
                            // console.log(data);
                            suggestions.append(`<div>${item.name}</div>`);
                        });
                    } else {
                        suggestions.hide(); // 如果没有数据则隐藏列表
                    }
                },
                error: function (error) {
                    console.error('Error:', error);
                }
            });
        } else {
            $('#suggestions').hide(); // 清空建议列表并隐藏
        }
    });

    $('#suggestions').on('click', 'div', function () {
        const selectedText = $(this).text();
        $('#searchInput').val(selectedText); // 将选中的文本设置为输入框的值
        $('#suggestions').hide(); // 隐藏建议列表
    });

    $('#searchInput').on('blur', function () {
        setTimeout(() => {
            $('#suggestions').hide(); // 输入框失去焦点时隐藏建议列表
        }, 100);
    });

    $('.slider').each(function () {
        var $this = $(this);
        var $group = $this.find('.slide-group');
        var $slides = $this.find('.slide');
        var buttonArray = [];
        var currentIndex = 0;
        var timeout;
        console.log('Slider:', $this);
        console.log('Slides:', $slides);

        function move(newIndex) {
            var animateLeft, slideLeft;
            advance();
            if ($group.is(':animated') || currentIndex === newIndex) {
                return;
            }
            buttonArray[currentIndex].removeClass('active');
            buttonArray[newIndex].addClass('active');
            if (newIndex > currentIndex) {
                slideLeft = '100%';
                animateLeft = '-100%';
            } else {
                slideLeft = '-100%';
                animateLeft = '100%';
            }
            $slides.eq(newIndex).css({left: slideLeft, display: 'block'});
            $group.animate({left: animateLeft}, function () {
                $slides.eq(currentIndex).css({display: 'none'});
                $slides.eq(newIndex).css({left: 0});
                $group.css({left: 0});
                currentIndex = newIndex;
            });
        }

        function advance() {
            clearTimeout(timeout);
            timeout = setTimeout(function () {
                if (currentIndex < ($slides.length - 1)) {
                    move(currentIndex + 1);
                } else {
                    move(0);
                }
            }, 4000);
        }
        console.log('Slide-buttons container:', $this.find('.slide-buttons'));
        $.each($slides, function (index) {
            console.log('Creating button for slide', index);

            var $button = $('<button type="button" class="slide-btn">&bull;</button>');
            if (index === currentIndex) {
                $button.addClass('active');
            }
            console.log('Generated button:', $button[0]);
            console.log('Appending to:', $this.find('.slide-buttons')[0]);
            $button.on('click', function () {
                move(index);
            }).appendTo($this.find('.slide-buttons'));
            buttonArray.push($button);
        });

        advance();


    });



});