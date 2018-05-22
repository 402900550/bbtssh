//这是升序降序按钮开始
var scendingChange = function() {
    $(".ascending").click(function() {
        dataAnalysisChange(1);
        $(this).hide();
        $(".descending").show();
    });
    $(".descending").click(function() {
        dataAnalysisChange(2);
        $(this).hide();
        $(".ascending").show();
    });
};
//这是升序降序按钮结束

//数据统计
function dataAnalysisChange(ending) {
    var type = $(".rankingTitle .header_display").attr('checkType');
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysisList",
        data:{ending:ending,type:type},
        type:"POST",
        dataType : "html",
        async:false,
        success: function(data) {

            $(".data_display").html(data);
        }
    });
}


//这是温度等数值宽度控制开始
var widthChange = function() {
    for(var i = 0; i < 50; i++) {
        var n = i;
        var tem = $(".temperature>span").eq(n).html();
        var tems = ((tem / (50 - 0)) * 105) + 40;
        $(".temperature").eq(n).css("width", tems);
    }

    for(var i = 0; i < 50; i++) {
        var n = i;
        var hum = $(".humidity>span").eq(n).html();
        var hums = ((hum / (90 - 20)) * 86) + 35;
        $(".humidity").eq(n).css("width", hums);
    }

    for(var i = 0; i < 50; i++) {
        var n = i;
        var pm = $(".PM>span").eq(n).html();
        var pms = ((pm / (300 - 0)) * 100) + 50;
        $(".PM").eq(n).css("width", pms);
    }

    for(var i = 0; i < 50; i++) {
        var n = i;
        var ill = $(".illumination>span").eq(n).html();
        var ills = ((ill / (65535 - 0)) * 95) + 50;
        $(".illumination").eq(n).css("width", ills);
    }

    for(var i = 0; i < 50; i++) {
        var n = i;
        var noise = $(".noise>span").eq(n).html();
        var noises = ((noise / (150 - 0)) * 105) + 40;
        $(".noise").eq(n).css("width", noises);
    }

};
//这是温度等数值宽度控制结束

//这是学校,学科,教师数据交换展示开始
var dataChange = function() {
    $(".rankingTitle li").click(function() {
        $(".rankingTitle li").removeClass("header_display");
        var nameclass=$(this).attr('class');
        $(this).addClass("header_display");
        $('.dataAnalysis_header').hide();
        $('.data_display ul').hide();
        $('ul[headerType="'+nameclass+'"]').show();

    });
};
//这是学校,学科,教师数据交换展示结束
//跳到主页默认选中主页
function checkedIndex() {
    $('.home').addClass('activeli');
    var url=localStorage.getItem('urlLi');
    $('li[url="'+url+'"]').removeClass('activeli');
    $('li[url="'+url+'"]').parents('li').removeClass('activeli');
}
$(function() {

    //首页数据统计
    dataAnalysisChange(2);

    scendingChange();
    widthChange();
    dataChange();
    $(".rankingTitle li").eq(0).trigger('click');
    checkedIndex();

    selectEnvironmentList();
    $('li[qxmId="123623"]').trigger('click');
});

function selectEnvironmentList() {
    $(document).on('click', '.region li', function () {
        $('.region li').removeClass('choice_checked');
        $(this).addClass('choice_checked');
        var qxmId = $(this).attr('qxmId');
        $.ajax({
            url:CONTEXT_PATH+"/environmentList",
            data:{qxmId:qxmId},
            type:"POST",
            dataType : "html",
            async:false,
            success: function(data) {

                $(".science_monitor").html(data);
            }
        });
    })
}


