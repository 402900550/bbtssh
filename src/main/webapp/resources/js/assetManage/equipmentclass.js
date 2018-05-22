
//查询学校年级班级设备
function  selectEquipmentClassRoom(page) {
    var json = {
        classId: $("#classId").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url:CONTEXT_PATH+"/equipment/selectEquipmentClassRoomList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $("#classEquipmentTable").html(data);

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
        selectEquipmentClassRoom(goPage);
    });
}

//查看设备详情
function lookSchoolDetailEquipmentList() {
    $(document).on('click', 'div[name="lookAccessDetail"]', function () {
        var equimentId = $(this).attr('equimentId');
        window.open(CONTEXT_PATH + '/equipment/lookSchoolDetailEquipmentList?equimentId=' + equimentId);
    });
}

//新增设备
function gotoEquipmentInput() {
    $(document).on('click','#addEquipment',function () {
        $('.nav03').find('li[url="assetManage/equipmentInput"]').trigger('click');
    })
}


$(function () {

    selectEquipmentClassRoom(1);
    gotoPage();

    lookSchoolDetailEquipmentList();

    gotoEquipmentInput();
});


