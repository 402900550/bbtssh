
//应用监测
function  selectEquipmentSingleSchoolList(page) {
    var json = {
        className:$("#className").val(),
        workStatus:$("#workStatus").val(),
        teacherName: $("#teacherName").val(),
        schoolCode: $("#theSchoolCode").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url:CONTEXT_PATH+"/application/selectEquipmentSingleSchoolList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $("#singleSchoolTable").html(data);

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
}

//分页
function gotoPage() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        selectEquipmentSingleSchoolList(goPage);
    });
}

//查询
function searchEq() {
    $('#doSearch').on('click', function () {
        selectEquipmentSingleSchoolList(1);
    });
}


//电控控制
function doControlOrder() {

    $(document).on('click','.control_on,.control_off',function () {
        var roleName=$("#roleName").val();
        if(roleName=="DISTNCTUSER"){
            return alert("权限不够");
        }
        if(roleName=="COMPANY"){
            return alert("权限不够");
        }
        var order=$(this).attr('order');
        var controlType=$(this).attr('controlType');
        var equipmentNo=$(this).parents('tr').attr('equipmentNo');
        var equipmentType=$(this).parents('tr').attr('equipmentType');
        var schoolCode=$(this).parents('tr').attr('schoolCode');
        var flag;
        if (order==1){
            flag=confirm("确认要开启吗？");
        }
        if (order==0){
            flag=confirm("确认要关闭吗？");
        }
        if (flag){
            $.ajax({
                url:CONTEXT_PATH+'/application/doControlOrder',
                data:{
                    order:order,
                    controlType:controlType,
                    equipmentNo:equipmentNo,
                    equipmentType:equipmentType,
                    schoolCode:schoolCode,
                },
                type:'post',
                dataType:'json',
                success:function (result) {
                    alert(result.message);
                    if (result.success==true){
                        $('#doSearch').trigger('click');
                    }
                }
            })
        }

    })

}



//跳转到作息时间使用设备情况
function lookGradeTimeTable() {
    $(document).on('click','td[name="lookTimeTable"]',function () {
        var gradeId = $(this).attr('gradeId');
        var classId = $(this).attr('classId');
        var schoolCode = $(this).attr('schoolCode');
        var url=CONTEXT_PATH+'/application/forwardGradeTimeTable?gradeId='+gradeId+"&classId="+classId+"&schoolCode="+schoolCode;
        window.open(url);
    });
}







$(function () {
    searchEq();
    $('#doSearch').trigger('click');

    //电控
    doControlOrder();
    gotoPage();

    //跳转页面
    lookGradeTimeTable();
});

