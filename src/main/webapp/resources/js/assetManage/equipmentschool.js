
//查询学校年级班级设备
function  selectEquipmentSchool(page) {
    var json = {
        schoolCode: $("#schoolCode").val(),
        gradeName: $("#gradeName").val(),
        className:$("#className").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/equipment/selectEquipmentSchoolList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $("#equipmentsTable").html(data);

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
            $(".realTime_data tr").remove();
        }
    })
}

//分页
function gotoPage() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        selectEquipmentSchool(goPage);
    });
}

//查看班级详情
function lookSchoolDetailEquipmentSchoolClass() {
    $(document).on('click', 'div[name="lookSchoolDetail"]', function () {
        var classId = $(this).attr('classId');
        window.open(CONTEXT_PATH + '/equipment/lookSchoolDetailEquipmentSchoolClass?classId=' + classId);
    });
}
//新增设备
function gotoEquipmentInput() {
    $(document).on('click','#addEquipment',function () {
        $('.nav03').find('li[url="assetManage/equipmentInput"]').trigger('click');
    })
}
//查询设备列表
function searchEq() {
    $('#searchSchoolBtn').on('click', function () {
        selectEquipmentSchool(1);
    });
}


$(function () {

    selectEquipmentSchool(1);
    gotoPage();

    searchEq();
    $('#searchSchoolBtn').trigger('click');
    lookSchoolDetailEquipmentSchoolClass();

    gotoEquipmentInput();
});


