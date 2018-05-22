/**
 * Created by traveksky on 2017/6/16.
 */


//查询教学楼
function selectBuilding(page) {
    var json = {
        schoolCode: $("#schoolCode").val(),
        buildingName: $("#buildingName").val(),
        startNumber: page,
        sizeNumber: 7
    }
    $.ajax({
        url:CONTEXT_PATH+"/building/selectTeachBuilding",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $('.building').html(data);
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


        }
    })
};

//分页
function gotoPage() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        selectBuilding(goPage);
    });
}

//查询教学楼
function searchBuilding() {
    $('#doSearch').on('click',function () {
        selectBuilding(1);
    });
}


//删除教学楼
function deleteBuilding() {
    $(document).off('click','.delete_building').on('click','.delete_building',function () {
        var flag=confirm('确认要删除此教学楼吗?');
        if (!flag){
            return;
        }
        var buildingId=$(this).attr('buildingId');
        var operateSchoolCode=$(this).parents('li').attr('schoolCode');
        $.ajax({
            url:CONTEXT_PATH+'/building/deleteBuilding',
            data:{buildingId:buildingId,operateSchoolCode:operateSchoolCode},
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

//新增教学楼 打开弹窗
//创建新增教学楼弹框
function creatDialog(title,schoolCode,buildingId,type) {
    var dialog=new Dialog();
    dialog.showShortDialog();
    $.ajax({
        url:CONTEXT_PATH+'/building/loadBuildingById',
        data:{schoolCode:schoolCode,buildingId:buildingId},
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

//打开修改新增教学楼弹框
function openDialog() {

    $(document).on('click','.building_add,.add_building',function () {
        var schoolCode=$('#schoolCode').val();
        creatDialog('新增教学楼',schoolCode,null,'add');
    });
    $(document).on('click','.modify_building',function () {
        var schoolCode=$('#schoolCode').val();
        var buildingId=$(this).attr('buildingId');
        creatDialog('修改教学楼',schoolCode,buildingId,'modify');
    })


};
//关闭弹框
function closeDialog() {
    $(document).on('click','.close_pop',function () {
        var dialog=new Dialog();
        dialog.closeDialog();
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

//确认保存教学楼
function doSaveBuilding() {
    $(document).on('click','.keep_pop',function () {
        var data=getData();
        $.ajax({
            url:CONTEXT_PATH+'/building/doSaveOrUpdateBuilding',
            data:data,
            dataType:'json',
            type:'post',
            success:function (result) {
                alert(result.message);
                if (result.success==true){
                    $('#doSearch').trigger('click');
                    $('.close_pop').trigger('click');
                }
            }

        })



    });
};


//跳转到教室管理
function forwardClassroom() {

    $(document).on('click','span[name="lookClassRoom"]',function () {
        var buildingId=$(this).attr('buildingId');
        window.open(CONTEXT_PATH+'/building/forwardClassroomManage?buildingId='+buildingId);
    })

}





$(function () {
    searchBuilding();
    gotoPage();
    $('#doSearch').trigger('click');
    deleteBuilding();
    openDialog();
    closeDialog();
    doSaveBuilding();
    forwardClassroom();
});




