
//应用监测
function  selectUsedRecord(page) {
    var json = {
        className:$("#className").val(),
        teacherName: $("#teacherName").val(),
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url:CONTEXT_PATH+"/application/selectUsedRecord",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $("#totalTable").html(data);

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
        selectUsedRecord(goPage);
    });
}

//查询
function searchEq() {
    $('#doSearch').on('click', function () {
        selectUsedRecord(1);
    });
}

$(function () {
    loadSchoolList($('#schoolList'));
    searchEq();
    $('#doSearch').trigger('click');
    gotoPage();
});