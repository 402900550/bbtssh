//暂时不使用
// $(function () {
//     $("#txt_search").keyup(function (evt) {
//         ChangeCoords(); //控制查询结果div坐标
//         var k = window.event ? evt.keyCode : evt.which;
//         //输入框的id为#txt_search，这里监听输入框的keyup事件
//         //不为空 && 不为上箭头或下箭头或回车
//         if ($("#txt_search").val() != "" && k != 38 && k != 40 && k != 13) {
//             console.log($("#txt_search").val());
//             $.ajax({
//                 type: 'post',
//                 async: true, //同步执行，不然会有问题
//                 dataType: "json",
//                 url: CONTEXT_PATH+"/common/loadAllSchoolByLikeName",   //提交的页面/方法名
//                 data: {schoolName:$("#txt_search").val()},             //参数（如果没有参数：null）
//
//                 error: function (msg) {//请求失败处理函数
//                     alert("数据加载失败");
//                 },
//                 success: function (data) { //请求成功后处理函数。
//                     /*  var objData = eval("(" + data.userName + ")");   */
//                     console.log(data);
//                     if (data.length > 0) {
//                         var layer = "";
//                         layer = "<table id='aa' style='line-height: 25px;'>";
//                         $.each(data, function (idx, item) {
//                             layer += "<tr class='line'><td class='std'>" + item.name + "</td></tr>";
//                         });
//                         layer += "</table>";
//
//                         //将结果添加到div中
//                         $("#searchresult").empty();
//                         $("#searchresult").append(layer);
//                         $(".line:first").addClass("hover");
//                         $("#searchresult").css("display", "");
//                         //鼠标移动事件
//
//                         $(".line").hover(function () {
//                             $(".line").removeClass("hover");
//                             $(this).addClass("hover");
//                         }, function () {
//                             $(this).removeClass("hover");
//                             //$("#searchresult").css("display", "none");
//                         });
//                         //鼠标点击事件
//                         $(".line").click(function () {
//                             $("#txt_search").val($(this).text());
//                             $("#searchresult").css("display", "none");
//                         });
//                     } else {
//                         $("#searchresult").empty();
//                         $("#searchresult").css("display", "none");
//                     }
//                 }
//             });
//         }
//         else if (k == 38) {//上箭头
//             $('#aa tr.hover').prev().addClass("hover");
//             $('#aa tr.hover').next().removeClass("hover");
//             $('#txt_search').val($('#aa tr.hover').text());
//         } else if (k == 40) {//下箭头
//             $('#aa tr.hover').next().addClass("hover");
//             $('#aa tr.hover').prev().removeClass("hover");
//             $('#txt_search').val($('#aa tr.hover').text());
//         }
//         else if (k == 13) {//回车
//             $('#txt_search').val($('#aa tr.hover').text());
//             $("#searchresult").empty();
//             $("#searchresult").css("display", "none");
//         }
//         else {
//             $("#searchresult").empty();
//             $("#searchresult").css("display", "none");
//         }
//     });
//     $("#searchresult").bind("mouseleave", function () {
//         $("#searchresult").empty();
//         $("#searchresult").css("display", "none");
//     });
// });
// //设置查询结果div坐标
//
// function ChangeCoords() {
//     //    var left = $("#txt_search")[0].offsetLeft; //获取距离最左端的距离，像素，整型
//     //    var top = $("#txt_search")[0].offsetTop + 26; //获取距离最顶端的距离，像素，整型（20为搜索输入框的高度）
//     var left = $("#txt_search").position().left; //获取距离最左端的距离，像素，整型
//     var top = $("#txt_search").position().top + 20; ; //获取距离最顶端的距离，像素，整型（20为搜索输入框的高度）
//     $("#searchresult").css("left", left + "px"); //重新定义CSS属性
//     $("#searchresult").css("top", top + "px"); //同上
// }
//---------

//应用监测
function  selectEquipmenDistinctSchool(page) {
    var json = {
        schoolType:$("#schoolType").val(),
        distinctName: $("#distinctName").val(),
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 7
    }
    $.ajax({
        url:CONTEXT_PATH+"/application/selectEquipmenDistinctSchoolList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $(".app_main").html(data);

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
        selectEquipmenDistinctSchool(goPage);
    });
}

//查询
function searchEq() {
    $('#doSearch').on('click', function () {
        selectEquipmenDistinctSchool(1);
    });
}



//跳转到单个学校
function lookDetailSingleSchool() {

    $(document).on('click','div[name="lookDetail"]',function () {
        var schoolCode=$(this).attr('schoolCode');
        var url=CONTEXT_PATH+'/application/forwardSchoolDetail?schoolCode='+schoolCode;
        window.open(url);
    });




}

//跳转到环境检测
function lookEnvironmentList() {

    $(document).on('click','div[name="lookEnvironment"]',function () {
        var schoolCode=$(this).attr('schoolCode');
        var url=CONTEXT_PATH+'/application/forwardEnvironmentList?schoolCode='+schoolCode;
        window.open(url);
    });




}




$(function () {
    //loadSchoolType($('#schoolType'));
    //loadCityList($('#distinctList'));
    loadSchoolList($('#schoolList'));

    searchEq();
    $('#doSearch').trigger('click');
    //跳转到学校详情
    lookDetailSingleSchool();
    //跳转环境检测
    lookEnvironmentList();
    //分页
    gotoPage();
});
































