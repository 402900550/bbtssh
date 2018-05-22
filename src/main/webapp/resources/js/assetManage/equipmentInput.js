//根据年级id查询班级
function GradeAndClassById() {
    $(document).on('change', '#gradeId', function () {
        var data = {
            gradeId: $("#gradeId").val(),
            schoolCode:$('#schoolCode').val()
        };
        var $this = $('#classId');
        $.ajax({
            url: CONTEXT_PATH + '/classroom/selectGradeAndClassById',
            data: data,
            dataType: 'json',
            type: 'post',
            async:false,
            success: function (result) {
                $this.empty();
                var html = '<option value="">请选择班级</option>';
                for (var i = 0; i < result.length; i++) {
                    html += '<option value=' + result[i].id + '>' + result[i].className + '</option>';
                }
                if (result.length < 1) {
                    html += '<option value="">没有班级!</option>';
                }
                $this.append(html);
                $('#classId').trigger('change');
            }
        })
    });
};

//根据班级加载教学楼教室
function loadBuildingAndClassRoomByClass() {
    $(document).on('change','#classId',function () {
        var classId=$(this).val();

            $.ajax({
            url: CONTEXT_PATH + '/classroom/loadBuildingAndClassRoomByClass',
            data: {classId:classId},
            dataType: 'json',
            type: 'post',
            async:false,
            success: function (result) {
                $('#buildingId').empty();
                $('#roomId').empty();
                var buildingId=result.buildingId;
                var buildingName=result.buildingName;
                var roomId=result.roomId;
                var roomCode=result.roomName;
                var message=result.message;
                var success=result.success;
                if (success==0){
                    return;
                }
                $('#buildingId').append('<option selected value='+buildingId+'>'+buildingName+'</option>');
                $('#roomId').append('<option selected value='+roomId+'>'+roomCode+'</option>');
            }
        })

    })
}

//根据学校的改变改变教学楼和教室
function changeSchool() {
    $('#schoolCode').on('change',function () {
        var schoolCode=$(this).val();
        $.ajax({
            url: CONTEXT_PATH + '/classroom/selectGradeBySchool',
            data: {schoolCode:schoolCode},
            dataType: 'json',
            type: 'post',
            async:false,
            success: function (result) {
                $('#gradeId').empty();
                var html='<option value="">请选择年级</option>';
                for(var i=0;i<result.length;i++){
                    html+='<option value='+result[i].id+'>'+result[i].name+'</option>';
                }
                if (result.length < 1) {
                    html += '<option value="">没有年级!</option>';
                }
                $('#gradeId').append(html);
                $('#gradeId').trigger('change');
            }
        })

    });
}





//获取参数
function getData() {
    var jsonData={};
    var dataArr=[];
    var isComplated=true;
    var arr=$('#euqipmentData table');
    arr.each(function () {
        var data = {};
        $(this).find('select,input').each(function () {
            var attr = $(this).attr('name');
            //特殊处理
            var value = $(this).val();
            data[attr] = value;
        });
        dataArr.push(data);
    });
    jsonData.accessoryData=JSON.stringify(dataArr);
    jsonData.schoolCode=$('#schoolCode').val();
    jsonData.roomId=$('#roomId').val();
    jsonData.typeLong=$('#selectType').val();
    jsonData.personCost=$('#personCost').val();
    jsonData.manager=$('#manager').val();
    jsonData.classId=$('#classId').val();
    jsonData.equipmentNo=$('#equipmentNo').val();
    jsonData.datumCost=$('#datumCost').val();
    jsonData.inputDate=$('#inputDate').val();
    return jsonData;
}


//添加控制器设备
function addControllerEquipment() {
    $(document).on('click', '.opinion_keep', function () {

        var flag=$('#isSaveName:checked').length>0;
        var saveName='';
        if (flag){
            saveName=$('#saveName').val();
            if (!saveName){
                alert('请输入保存名称!');
                return;
            }
        }
        doSaveEquipment(saveName);

    });

}



function doSaveEquipment(saveName) {
    var data=getData();
    if (!$('#selectType').val()){
        alert('请选择配套类型!');
        return;
    }

    if(!$('#schoolCode').val()){
        alert('请选择学校!');
        return;
    }

    if(!data){
        alert('数据不完善!');
        return;
    }
    data.saveName=saveName;
    $.ajax({
        url: CONTEXT_PATH + '/equipment/addControllerEquipment',
        data: data,
        dataType: 'json',
        type: 'post',
        success: function (result) {
            alert(result.message);
            if (result.success==true){
                $('.close_pop').trigger('click');
            }
        }

    });
}



$(function () {
    //改变教学楼 学校等联动改变
    changeSchool();
    GradeAndClassById();
    loadBuildingAndClassRoomByClass();


    //执行新增设备
    addControllerEquipment();

});


