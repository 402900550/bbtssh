//点击编辑显示保存和取消等效果
function displayOrHideBtn() {
    //编辑按钮
    $('#edit').on('click',function () {
        var gradeId=$('#gradeId').val();
        if (!gradeId){
            alert("请选择年级!");
            return;
        }
        $('#operate .btn').hide();
        $('#save,#cancel').show();

        //改变input的样式
        $('.timeData input').removeClass('schedule_input');
        $('.timeData input').addClass('schedule_input_edit');
        $('.timeData input').attr('disabled',false);
    });
    //取消按钮
    $('#cancel').on('click',function () {

        $('#operate .btn').show();
        $('#save,#cancel').hide();
        $('#gradeId').trigger('change');
    });

    //保存按钮
    $('#save').on('click',function () {
        var arr=getData();
        $.ajax({
            url:CONTEXT_PATH+'/schedule/saveOrEditTime',
            data:{dataStr:JSON.stringify(arr)},
            dataType:'json',
            type:'post',
            success:function (result) {
                alert(result.message);
                if (result.success==true){
                    $('#gradeId').trigger('change');
                    $('#cancel').trigger('click');
                }
            }
        })
    });

};


//获取参数
function getData() {
    var arr=[];
    $('.timeData li').each(function () {
        var scheduleId=$(this).attr('scheduleId')?$(this).attr('scheduleId'):null;
        var timeType=$(this).attr('timeType');
        var schoolCode=$(this).attr('schoolCode');
        var start=$(this).find('input[name="start"]').val();
        var end=$(this).find('input[name="end"]').val();
        var numberCourse=$(this).find('span[name="numberCourse"]').text();
        var data={};
        data.id=scheduleId;
        data.timeType=timeType;
        data.schoolCode=schoolCode;
        data.start=start;
        data.end=end;
        data.numberCourse=numberCourse;
        data.gradeId=$('#gradeId').val();
        arr.push(data);
    });

    return arr;
}

//年级切换
function changeGrade() {

    $('#gradeId').on('change',function () {
        var schoolCode=$(this).attr('schoolCode');
        var gradeId=$(this).val();

        $.ajax({
            url:CONTEXT_PATH+'/schedule/loadScheduleTableBySchoolAndGrade',
            data:{schoolCode:schoolCode,gradeId:gradeId},
            dataType:'html',
            type:'post',
            async:false,
            success:function (result) {
                $('.timeData').html(result);
            }
        });
        if (!schoolCode||!gradeId){
            $('.timeData').html('<div class="grade_time">请选择年级</div>');
        }
    });

};



$(function () {
    //编辑按钮的切换
    displayOrHideBtn();
    //改变年级课表更换
    changeGrade();

});