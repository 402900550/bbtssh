//页面开始加载
$(function () {
    gotoEquipmentInput();
    gotoPage();
    //加载查询
    searchEq(1);
    $('#searchSchoolBtn').trigger('click');
    //加载学校和区县下拉框
    loadSchoolType($('#schoolType'));
    loadCityList($('#distinctList'));
    loadSchoolList($('#schoolList'));

    lookSchoolDetailEquipmentCount();
});


//查询学校列表
function searchEq() {
    $('#searchSchoolBtn').on('click', function () {
        searchEqOp(1);
    });
}
//查询设备
var searchEqOp = function (page) {

    var json = {
        distinctName: $("#distinctName").val(),
        schoolName: $("#schoolName").val(),
        className: $("#schoolType").val(),
        schoolType: $("#schoolType").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url: CONTEXT_PATH + "/equipment/loadEquimentAndClassRoom",
        data: json,
        type: "POST",
        dataType: "html",
        success: function (data) {

            $("#equipmentCountTable").html(data);

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


//添加设备的跳转
function gotoEquipmentInput() {
    $(document).on('click','#addEquipment',function () {
        $('.nav03').find('li[url="assetManage/equipmentInput"]').trigger('click');
    })
}


//查看学校详情
function lookSchoolDetailEquipmentCount(){
    $(document).on('click','div[name="lookSchoolDetail"]',function () {
            var schoolCode=$(this).attr('schoolCode');
            window.open(CONTEXT_PATH+'/equipment/lookSchoolDetailEquipment?schoolCode='+schoolCode);
    });


};



























