//这是学校,学科,教师,班级数据交换展示开始
var dataChange = function() {
	$(".teacher").click(function() {
		$(this).removeClass().addClass("teacher_display");
		$(".school_display").removeClass().addClass("school");
		$(".subject_display").removeClass().addClass("subject");
		$(".grade_display").removeClass().addClass("grade");
		$(".statistics>ul").hide();
		$(".teacher_header").show();
		$(".teacher_show").show();
	});
	$(".subject").click(function() {
		$(this).removeClass().addClass("subject_display");
		$(".school_display").removeClass().addClass("school");
		$(".teacher_display").removeClass().addClass("teacher");
		$(".grade_display").removeClass().addClass("grade");
		$(".statistics>ul").hide();
		$(".subject_header").show();
		$(".subject_show").show();
	});
	$(".grade").click(function() {
		$(this).removeClass().addClass("grade_display");
		$(".school_display").removeClass().addClass("school");
		$(".teacher_display").removeClass().addClass("teacher");
		$(".subject_display").removeClass().addClass("subject");
		$(".statistics>ul").hide();
		$(".grade_header").show();
		$(".grade_show").show();
	});
	$(".statistics_choice>li").eq(0).click(function() {
		$(this).removeClass().addClass("school_display");
		$(".subject_display").removeClass().addClass("subject");
		$(".teacher_display").removeClass().addClass("teacher");
		$(".grade_display").removeClass().addClass("grade");
		$(".statistics>ul").hide();
		$(".school_header").show();
		$(".school_show").show();
	});
};
//这是学校,学科,教师，班级数据交换展示结束

$(function() {
	dataChange();

    loadSchoolList($('#schoolList'));

    //学校排名
    selectSchoolList(1);

    searchList();

    //教师排名
    selectTeacherList(1);

    //班级排名
    selectClassList(1);

    //科目排名
    selectSubjectList(1);

    //跳转页面
    lookSchoolRanksList();
});



//学校排名
function  selectSchoolList(page) {
    var json = {
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysis/selectDataAnalysisSchoolList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $(".school_show").html(data);

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
            $(".statistics li").remove();
        }
    })
}

//点击查询学校使用率
function searchList() {
    $('#query').on('click',function () {
        selectSchoolList(1);
        selectTeacherList(1);
        selectClassList(1);
        selectSubjectList(1);
    });
}

//教师排名
function  selectTeacherList(page) {
    var json = {
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysis/selectDataAnalysisTeacherRanksList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $(".teacher_show").html(data);

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
            $(".statistics li").remove();
        }
    })
}


//班级排名
function  selectClassList(page) {
    var json = {
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysis/selectDataAnalysisClassRanksList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $(".grade_show").html(data);

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
            $(".statistics li").remove();
        }
    })
}

//科目排名
function  selectSubjectList(page) {
    var json = {
        schoolName: $("#schoolName").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysis/selectDataAnalysisSubjectRanksList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $(".subject_show").html(data);

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
            $(".statistics li").remove();
        }
    })
}

//跳转到学校页面
function lookSchoolRanksList() {

    $(document).on('click','[name="lookSchoolRanks"]',function () {
        var schoolCode=$(this).attr('schoolCode');
        var url=CONTEXT_PATH+'/dataAnalysis/forwardSchoolRanksList?schoolCode='+schoolCode;
        window.open(url);
    });




}


