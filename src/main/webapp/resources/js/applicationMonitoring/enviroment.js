
//应用监测
function  selectEnvironmentDistinctSchool(page) {
    var json = {
        schoolCode:$("#schoolCode").val(),
        className: $("#className").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url:CONTEXT_PATH+"/application/lodeEnvironmentList",
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
        },
        error:function(erro){
            $(".science li").remove();
        }
    })
}

//分页
function gotoPage() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        selectEnvironmentDistinctSchool(goPage);
    });
}

//查询
function searchEq() {
    $('#doSearch').on('click', function () {
        selectEnvironmentDistinctSchool(1);
    });
}


$(function () {

    selectEnvironmentDistinctSchool(1);
    gotoPage();
    searchEq();
    $('#doSearch').trigger('click');
});
