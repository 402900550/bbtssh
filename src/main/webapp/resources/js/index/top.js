
var forwardOp=function () {

    $('#navigationBar li').on('click',function (e) {
        e = e || window.event;
        if(e.stopPropagation) { //W3C阻止冒泡方法
            e.stopPropagation();
        } else {
            e.cancelBubble = true; //IE阻止冒泡方法
        }
        var forwardUrl=$(this).attr('url');
        localStorage.setItem('urlLi',forwardUrl);
        var type=$(this).attr('forwardType');
        if (forwardUrl){
            window.location.href=CONTEXT_PATH+'/forward/forwardByUrl?forwardUrl='+forwardUrl+'&type='+type;
        }

    })


};

var back=function () {

    $('#backIndex').on('click',function () {
        window.location.href=CONTEXT_PATH+'/index';
    });
};



//注销
var logout=function () {
    $(document).on('click','.cancellation02',function () {
        window.location.href=CONTEXT_PATH+'/logout';
    })
};

//限制输入数字
function limitNumber() {

    $(document).on('keyup','.number',function () {

        var value=$(this).val();
        if (!/^\d+$/.test(value)){
            alert('只能输入数字 !');
            value=value.replace(/[^\d]+/g,'');
            $(this).val(value);
        }

    })


};

//默认选中样式
function checkedLi() {
    var url=localStorage.getItem('urlLi');
    $('li[url="'+url+'"]').addClass('activeli');
    $('li[url="'+url+'"]').parents('li').addClass('activeli');
}


//跳转到运维管理
function forwardPending() {

    $(document).on('click','.waybill',function () {
        $('li[url="operationsManagement/maintainStatistics"]').trigger('click');
    })
}

$(function() {
    forwardOp();
    back();
    logout();

    limitNumber();
    checkedLi();
    //跳转到待处理运维单
    forwardPending();

});




