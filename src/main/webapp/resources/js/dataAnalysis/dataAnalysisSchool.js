//这是学校,学科,教师,班级数据交换展示开始
var dataChange = function() {

	$('.statistics_choice li').on('click',function () {
		$('ul[name="teacher"],ul[name="subject"],ul[name="grade"]').hide();
        $('.statistics_choice li').removeClass('typeChecked');
        var nameclass=$(this).attr('class');
        $('ul[name="'+nameclass+'"]').show();
		$(this).addClass('typeChecked');
    });
};
//这是学校,学科,教师，班级数据交换展示结束

//这是每年,每月,每日课时数折线图转换结束
$(function() {
	dataChange();
    $('.subject').trigger('click');

    selectSubjectList(1);
    selectTeacherList(1);
    selectClassList(1);
});

//教师排名
function  selectTeacherList(page) {
    var json = {
        schoolCode: $("#schoolCode").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysis/selectDataAnalysisTeacherRanks",
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
        schoolCode: $("#schoolCode").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysis/selectDataAnalysisClassRanks",
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
        schoolCode: $("#schoolCode").val(),
        startNumber: page,
        sizeNumber: 6
    }
    $.ajax({
        url:CONTEXT_PATH+"/dataAnalysis/selectDataAnalysisSubjectRanks",
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

