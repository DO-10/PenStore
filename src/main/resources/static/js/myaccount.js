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

        $.post("/mypage/update", {
            userId: userId,
            newAddress: newAddress
        }, function(response) {
            $("#responseMessage").css("color", "green").text(response);
        }).fail(function() {
            $("#responseMessage").css("color", "red").text("更新失败");
        });
    });
});