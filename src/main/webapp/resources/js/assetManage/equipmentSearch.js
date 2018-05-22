
//页面开始加载
$(function () {
    searchEqOp(1);
    loadSchoolList($('#schoolList'));
    gotoPage();
    searchEq();
    $('#doSearch').trigger('click');
    lookSchoolDetailEquipmentCount();
    gotoEquipmentInput();
});


//查询学校列表
function searchEq() {
    $('#doSearch').on('click', function () {
        searchEqOp(1);
    });
}
//查询设备
var searchEqOp = function (page) {

    var json = {
        distinctName: $("#distinctName").val(),
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url: CONTEXT_PATH + "/equipment/loadSchoolAc",
        data: json,
        type: "POST",
        dataType: "html",
        success: function (data) {
            $("#classTable").html(data);
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


//分页
function gotoPage() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        searchEqOp(goPage);
    });
}





//查看学校详情
function lookSchoolDetailEquipmentCount(){
    $(document).on('click','[name="lookAccessDetail"]',function () {
        var accessoryId=$(this).attr('accessoryId');
        window.open(CONTEXT_PATH+'/equipment/lookSchoolDetailEquipmentList?equimentId='+accessoryId);
    });


};

//添加设备的跳转
function gotoEquipmentInput() {
    $(document).on('click','#addEquipment',function () {
        $('.nav03').find('li[url="assetManage/equipmentInput"]').trigger('click');
    })
}














