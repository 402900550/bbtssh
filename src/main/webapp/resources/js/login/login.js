/**
 * Created by traveksky on 2017/6/16.
 */



var loginFun = function () {
    $('#loginBtn').on('click', function () {
        var userName = $('input[name="username"]').val();
        var password = $('input[name="password"]').val();
        $('#errorUser,#errorPassword,#errorCode').text('');

        if (!userName) {
            alert('请输入用户名');
            return;
        }
        if (!password) {
            alert('请输入密码');
            return;
        }
        var data = {
            userName: userName,
            password: password
        }
        $.ajax({
            url: CONTEXT_PATH + '/login',
            type: 'post',
            data: data,
            success: function (result) {
                console.log(result);
                $('#errorUser,#errorPassword,#errorCode').text('');
                if ('notRegister' === result) {
                    alert('该用户不存在');
                } else if ('Bad credentials' === result) {
                    alert('密码错误');
                } else if ('loginSuccess' === result) {
                    location.href = CONTEXT_PATH + '/index';
                }

            }
        })


    });
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
            $("#loginBtn").click();
        }
    };
}

var  isEnablePressed=function() {

    $(".password").bind("input propertychange", function() {
        var v1 = $('.user').val();
        var v2 = $('.password').val();
        if(v1 && v2 && v1!='请输入用户名'&&v2!='请输入密码') {
            $(".login").attr('disabled', false).css("background", "#173a50");
        } else {
            $(".login").attr('disabled', true).css("background", "#6b818f");
        }
    });

    $(".user").bind("input propertychange", function() {
        var v1 = $('.user').val();
        var v2 = $('.password').val();
        if(v1 && v2 && v1!='请输入用户名'&&v2!='请输入密码') {
            $(".login").attr('disabled', false).css("background", "#173a50");
        } else {
            $(".login").attr('disabled', true).css("background", "#6b818f");
        }
    });

}

var changeInputValue=function() {

    $('.user,.password').on('focus', function() {

        if($(this).val() == '请输入用户名' || $(this).val() == '请输入密码' || !$(this).val()) {
            $(this).val('');
            $(this).css('color', 'black');
        };

    });

    $('.user').on('blur', function() {

        if($(this).val() == '' || $(this).val() == '请输入用户名') {
            $(this).val('请输入用户名');
            $(this).css('color', 'gray');
        }

    });

    $('.password').on('blur', function() {

        if($(this).val() == '' || $(this).val() == '请输入密码') {
            $(this).val('请输入密码');
            $(this).css('color', 'gray');
        }

    });
}


$(function () {
    loginFun();
    isEnablePressed();
    changeInputValue();

});

