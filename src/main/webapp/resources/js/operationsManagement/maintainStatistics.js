$(function () {

    selectOperationsList();
    $('li[state="1"]').trigger('click');
    //添加异常
    addHandException();

    closeDialog();

    loadClassByGradeAndSchoolCode();

    doSubmitException();

    forwardProcessDetail();

    gotoPageException();
});

//查询待处理异常
function pendingExceptionList() {
    $.ajax({
        url: CONTEXT_PATH + "/operation/pendingExceptionList",
        data: {},
        type: "POST",
        dataType: "html",
        success: function (data) {
            $(".realTime_table").html(data);
        },
        error: function (erro) {
            $(".realTime_data tr").remove();
        }
    });
}

//查询已完成或者全部
function completeOrAllExceptionList(toPage,state) {
    $.ajax({
        url: CONTEXT_PATH + "/operation/completeOrAllExceptionList",
        data: {
            toPage:toPage,
            pageSize:10,
            state:state
        },
        type: "POST",
        dataType: "html",
        success: function (data) {
            $(".realTime_table").html(data);
            $("#pagination2").empty();
            $("#pagination2").pagination({
                currentPage: +$('#currentPage').val(),
                totalPage: +$('#totalPage').val(),
                isShow: true,
                count: 6,
                prevPageText: "< 上一页",
                nextPageText: "下一页 >",
                callback: function (current) {
                }
            });
        },
        error: function (erro) {
            $(".realTime_data tr").remove();
        }
    });
}

//分页
function gotoPageException() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        var state=$('.choice_checked').attr('state');
        completeOrAllExceptionList(goPage,state);
    });
}

function selectOperationsList() {
    $(document).on('click', '.choice_operations ul li', function () {
        $('.choice_operations ul li').removeClass('choice_checked');
        $(this).addClass('choice_checked');
        var state = $(this).attr('state');
        //查询待处理异常
        if (state==='1'){
            pendingExceptionList();
        }
        //查询全部的异常
        if (state==='2'){
            completeOrAllExceptionList(1,2);
        }
        //查询已完成
        if (state==='0'){
            completeOrAllExceptionList(1,0);
        }
    })
}



//手动添加运维单
function addHandException() {

    $(document).on('click', '#addException', function () {
        var dialog = new Dialog();
        dialog.showAreaDialog();
        $.ajax({
            url: CONTEXT_PATH + '/operation/addHandExceptionDialog',
            data: {},
            dataType: 'html',
            type: 'post',
            async: false,
            success: function (result) {
                $('.login').html(result);
            }
        });

    });
}


//关闭弹框
function closeDialog() {
    $(document).on('click', '.close_pop', function () {
        var dialog = new Dialog();
        dialog.closeDialog();
    });
}

//根据年级加载班级
function loadClassByGradeAndSchoolCode() {

    $(document).on('change', '#gradeId', function () {
        var gradeId = $(this).val();
        $.ajax({
            url: CONTEXT_PATH + '/operation/getClassByGradesException',
            data: {gradeId: gradeId},
            dataType: 'json',
            success: function (result) {
                var html = '';
                for (var i = 0; i < result.length; i++) {
                    html += '<option value=' + result[i].id + '>' + result[i].className + '</option>';
                }
                if (result.length < 1) {
                    html += '<option value="">未查询到班级!</option>';
                }
                $('#classId').html(html);
            }
        });
    });


}


//确认提交异常
function doSubmitException() {

    $(document).on('click', '#saveException', function () {
        var data = getData();
        $.ajax({
            url: CONTEXT_PATH + '/operation/doAddHandException',
            data: data,
            dataType: 'json',
            type:'post',
            success: function (result) {
                alert(result.message);
                if (result.success){
                    //刷新
                    $('.close_pop').trigger('click');
                }
            }
        });

    });


}

//获取参数
function getData() {
    var data = {};
    $('.login').find('select,input,textarea').each(function () {
        var attr = $(this).attr('name');
        //特殊处理
        var value = $(this).val();
        data[attr] = value;
    });
    return data;
}



//跳转流程图
function forwardProcessDetail() {

    $(document).on('click','div[name="lookDetailProcess"]',function () {

        var processId=$(this).attr('processId');
        var type=$(this).attr('types');
        var taskId=$(this).attr('taskId');
        window.open(CONTEXT_PATH+'/operation/lookDetailProcess?processId='+processId+'&type='+type+'&taskId='+taskId);
    })

}










