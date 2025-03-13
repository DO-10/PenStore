$(document).ready(function(){
    $('.tab-list').each(function (){
        var $this = $(this);
        var $tab = $this.find('li.active');
        var $link = $tab.find('a');
        var $panel = $($link.attr('href'));


        $this.on('click', '.tab-control', function (e) {
            e.preventDefault();
            var $link = $(this);
            var id = this.hash;

            if (id && !$link.is('.active')) {
                $panel.removeClass('active');
                $tab.removeClass('active');

                $panel = $(id).addClass('active');
                $tab = $link.parent().addClass('active');
            }
        });
    });

    $("#submitBtn").on('click', function () {
        const userId = $("#userId").val();
        const newAddress = $("#newAddress").val();

        if (!newAddress.trim()) {
            alert("地址不能为空！");
            return;
        }

        $.ajax({
            type: "POST",
            url: "changeAddressServlet",
            data: {
                userId: userId,
                newAddress: newAddress,
            },
            success: function (response) {
                $("#responseMessage").text(response).css("color", "green");
            },
            error: function () {
                $("#responseMessage").text("更新失败，请稍后重试。").css("color", "red");
            },
        });
    });
});