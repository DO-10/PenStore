
$(document).ready(function(){
    $('#email, #password').on('keyup', function() {
        var email= $(this).val();
        var msg=$('#usernameResult').val();
        var password=$('#password').val();

        $.ajax({
            url: 'login/nameCheck',
            type: 'GET',
            // data: {username: username},
            data: {q:email},
            success: function(response) {
                console.log('success:'+response);
                if (response==='Exist')
                {
                    $('#usernameResult').text('邮箱已注册哦嘻嘻嘻');

                }
                else
                {
                    $('#usernameResult').text('');
                }

                if (username!==''&&msg===''&&password.length!==0)
                {
                    if (password.length<5)
                    {
                        $('#usernameResult').text('密码不应该小于5位哦嘻嘻嘻');
                    }
                }

            },
            error: function(response) {

                console.log('error:'+response);
            }
        });




    });
});