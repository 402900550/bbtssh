//页面开始加载
$(function () {
    loadSchoolType($('#schoolType'));
    loadCityList($('#distinctList'));
    loadSchoolList($('#schoolList'));
    //查询学校
    openModifyDialog();
    openAddDialog();
    searchSchoolList();
    $('#doSearch').trigger('click');
    deleteSchool();
    gotoPage();
    closeDialog();
    doSaveSchool();
    buildingManage();
});



//查询学校
function selectSchool(page) {
    var json = {
        schoolType: $("#schoolType").val(),
        distinctName: $("#distinctName").val(),
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 3
    }
    $.ajax({
        url:CONTEXT_PATH+"/school/selectSchoolList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $(".school_data").html(data);

            $("#pagination2").empty();
            $("#pagination2").pagination({
                currentPage:+$('#currentPage').val(),
                totalPage:+$('#totalPage').val(),
                isShow: true,
                count: 6,
                prevPageText: "< 上一页",
                nextPageText: "下一页 >",
                callback: function(current) {
                }
            });
        },
        error:function(erro){
            $(".school_thing li").remove();
        }
    })
};
//查询学校列表
function searchSchoolList() {
    $('#doSearch').on('click',function () {
        selectSchool(1);
    });
}


//分页
function gotoPage() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        selectSchool(goPage);
    });
}



//删除学校
function deleteSchool() {
    $(document).off('click','div[name="deleteSchool"]').on('click','div[name="deleteSchool"]',function () {
        var flag=confirm('确认要删除此学校吗?');
        if (!flag){
            return;
        }
        var schoolId=$(this).attr('schoolId');
        var operateSchoolCode=$(this).parent('li').attr('schoolCode');
        $.ajax({
            url:CONTEXT_PATH+'/school/deleteSchool',
            data:{schoolId:schoolId,operateSchoolCode:operateSchoolCode},
            dataType:'json',
            type:'post',
            success:function (result) {
                alert(result.message);
                if (result.success==true){
                    $('#doSearch').trigger('click');
                }
            }
        })
    });
};

//打开编辑学校的弹框
function openModifyDialog() {
    $(document).on('click','div[name="modifySchool"]',function () {
        var schoolId=$(this).attr('schoolId');
        creatDialog('修改学校',schoolId,'modify');

    })
}
//打开添加学校的弹框
function openAddDialog() {
    $(document).on('click','#addSchoolBtn',function () {
        creatDialog('新增学校',null,'add');

    })
}
//关闭弹框
function closeDialog() {
    $(document).on('click','.close_pop',function () {
       var dialog=new Dialog();
       dialog.closeDialog();
    });
}
//创建弹框
function creatDialog(title,schoolId,type) {
    var dialog=new Dialog();
    dialog.showAreaDialog();
    $.ajax({
        url:CONTEXT_PATH+'/school/loadSchoolById',
        data:{schoolId:schoolId},
        dataType:'html',
        type:'post',
        async:false,
        success:function (result) {
            $('.login').html(result);
            $('.small_title').text(title);
            $('input[name="doType"]').val(type);
        }
    });
}

//获取参数
function getData() {
    var data={};
    $('.login').find('select,input,textarea').each(function () {
        var attr=$(this).attr('name');
        //特殊处理
        var value=$(this).val();
        data[attr]=value;
    });
    return data;
}

//确认保存学校
function doSaveSchool() {
    $(document).on('click','.keep_pop',function () {
        var data=getData();
        $.ajax({
            url:CONTEXT_PATH+'/school/doSaveOrUpdateSchool',
            data:data,
            dataType:'json',
            type:'post',
            success:function (result) {
                alert(result.message);
                if (result.success==true){
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



//跳转到教学楼修改
function buildingManage() {

    $(document).on('click','div[name="schoolName"]',function () {
        var schoolCode=$(this).attr('schoolCode');
        window.open(CONTEXT_PATH+'/building/forwardBuildingManage?schoolCode='+schoolCode);
    })

}





















