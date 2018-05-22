//查询参数
function selectParameterList(page) {
    var json = {
        parameterTypeId: $("#parameterType").val(),
        parameterValue: $("#parameterValue").val(),
        startNum: page,
        pageSize: 5
    }
    $.ajax({
        url: CONTEXT_PATH + "/parameter/selectParameterList",
        data: json,
        type: "POST",
        dataType: "html",
        success: function (data) {

            $("#parameterTable").html(data);

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
        }
    })
};
//查询参数列表
function doSearchParameter() {
    $('#doSearch').on('click', function () {
        selectParameterList(1);
    });
}


//分页
function gotoPage() {
    $(document).on('click', '.ui-pagination-page-item', function () {
        var goPage = +$(this).attr('data-current');
        selectParameterList(goPage);
    });
}


//创建添加类型弹框
function createAddTypeDialog() {
    $(document).on('click','#addParameterType',function () {
        var dialog = new Dialog();
        dialog.showShortDialog();
        var html = ' <p class="small_title">新增参数类型</p>';
        html += '<input type="hidden" value="" name="doType"/>';
        html+='<div class="input_warp"><p class="input_title">类型名称：</p><input class="input_pop" id="typeName" type="text" placeholder="类型名称" /></div>';
        html += '<p class="keep_pop" id="saveParameterType">保存</p><p class="close_pop">取消</p>';
        $('.login').append(html);
    });

}
//创建参数值弹框
function createAddParameterDialog(title, data, type) {
        var dialog = new Dialog();
        dialog.showAreaDialog();
        $.ajax({
            url: CONTEXT_PATH + '/parameter/openParameterWindow',
            data: data,
            dataType: 'html',
            type: 'post',
            async: false,
            success: function (result) {
                $('.login').html(result);
                $('.small_title').text(title);
                $('input[name="doType"]').val(type);
            }
        });

}
//打开修改新增教学楼弹框
function openDialog() {

    $(document).on('click', '#addParameter', function () {
        createAddParameterDialog('新增参数', null, 'add');
    });
    $(document).on('click', 'div[name="modifyParameter"]', function () {
        var parameterId = $(this).attr('parameterId');
        var data = {
            parameterId:parameterId
        };
        createAddParameterDialog('修改参数', data, 'modify');
    });


};



//保存新增类型
//确认保存
function doSaveParameterType() {
    $(document).on('click', '#saveParameterType', function () {
        var value=$('#typeName').val();
        if (!value){
            alert("数据不能为空!");
            return;
        }
        $.ajax({
            url: CONTEXT_PATH + '/parameter/doAddParameterType',
            data: {typeName:$('#typeName').val()},
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('#doSearch').trigger('click');
                    $('.close_pop').trigger('click');
                }
            },
            error:function () {
                alert("添加失败!请检查是否重复")
            }

        })


    });
};


function doSaveParameterValue() {
    $(document).on('click', '#saveParameterValue', function () {
        var data=getData();
        if(!data){
            alert("数据不能为空!");
            return;
        }
        $.ajax({
            url: CONTEXT_PATH + '/parameter/doAddOrModifyParameter',
            data: getData(),
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('#doSearch').trigger('click');
                    $('.close_pop').trigger('click');
                }
            },
            error:function () {
                alert("添加失败!请检查是否重复")
            }

        })


    });
};



//获取参数
function getData() {
    var data = {};
    var flag=true;
    $('.login').find('select,input,textarea').each(function () {
        var attr = $(this).attr('name');
        //特殊处理
        var value = $(this).val();
        data[attr] = value;
    });
    return data;
}

//关闭弹框
function closeDialog() {
    $(document).on('click', '.close_pop', function () {
        var dialog = new Dialog();
        dialog.closeDialog();
    });
}

$(function () {
    doSearchParameter();
    gotoPage();
    $('#doSearch').trigger('click');

    //添加弹框
    createAddTypeDialog();

    closeDialog();
    openDialog();
    //保存
    doSaveParameterType();
    doSaveParameterValue();
});
