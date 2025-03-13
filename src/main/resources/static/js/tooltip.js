$(document).ready(function() {
    var tooltip = $('#tooltip');

    // 当鼠标进入产品区域时
    $('.product').mouseenter(function() {
        // 获取当前产品的描述
        var description = $(this).find('.description').text();

        // 设置悬浮窗的内容
        tooltip.html('<strong>商品描述:</strong> ' + description);

        // 显示悬浮窗
        tooltip.show();
    });

    // 当鼠标离开产品区域时
    $('.product').mouseleave(function() {
        // 隐藏悬浮窗
        tooltip.hide();
    });

    // 当鼠标在产品区域内移动时
    $('.product').mousemove(function(e) {
        // 更新悬浮窗的位置，使其跟随鼠标移动
        var x = e.pageX;
        var y = e.pageY;
        tooltip.css({
            top: y + 10, // 在鼠标下方显示
            left: x + 10 // 在鼠标右侧显示
        });
    });
});