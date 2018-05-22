//查询教室
var searchClassroomOp = function (page) {

    var json = {
        buildingId: $("#currentBuildingId").val(),
        roomCode: $("#classroomCode").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url: CONTEXT_PATH + "/classroom/selectClassroomList",
        data: json,
        type: "POST",
        dataType: "html",
        success: function (data) {

            $("#classroomTable").html(data);

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

//查询学校列表
function searchRoomList() {
    $('#doSearch').on('click', function () {
        searchClassroomOp(1);
    });
}


//分页
function gotoPage() {
    $(document).on('click', '.ui-pagination-page-item', function () {
        var goPage = +$(this).attr('data-current');
        searchClassroomOp(goPage);
    });
}

//添加 修改教室
//创建新增弹框
function creatAddModifyDialog(title, roomId, buildingId, type) {
    var dialog = new Dialog();
    dialog.showShortDialog();
    $.ajax({
        url: CONTEXT_PATH + '/classroom/loadClassroomById',
        data: {buildingId: buildingId, roomId: roomId},
        dataType: 'html',
        type: 'post',
        async: false,
        success: function (result) {
            $('.login').html(result);
            $('.small_title').text(title);
            $('input[name="doType"]').val(type);
            $('#doSearch').trigger('click');
        }
    });
}

//打开修改新增教学楼弹框
function openDialog() {

    $(document).on('click', '#addRoom', function () {
        var buildingId = $('#currentBuildingId').val();
        creatAddModifyDialog('新增教室', null, buildingId, 'add');
    });
    $(document).on('click', 'div[name="modifyClassroom"]', function () {
        var roomId = $(this).attr('roomId');
        var buildingId = $('#currentBuildingId').val();
        creatAddModifyDialog('修改教室', roomId, buildingId, 'modify');
    });


};
//关闭弹框
function closeDialog() {
    $(document).on('click', '.close_pop', function () {
        var dialog = new Dialog();
        dialog.closeDialog();
    });
}


//获取参数
function getData() {
    var data = {};
    $('.login').find('select,input,textarea').each(function () {
        var attr = $(this).attr('name');
        //特殊处理
        var value = $(this).val();
        data[attr] = value;
    });
    return data;
}

//确认保存
function doSaveClassroom() {
    $(document).on('click', '#saveClassroom', function () {
        var data = getData();
        data.schoolCode=$('#currentSchoolCode').val();
        $.ajax({
            url: CONTEXT_PATH + '/classroom/updateClassRoom',
            data: data,
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('.queryss').trigger('click');
                    $('.close_pop').trigger('click');
                    $('#doSearch').trigger('click');
                }
            }

        })


    });
};


//删除教室
function deleteClassRoom() {
    $(document).off('click', 'div[name="deleteClassRoom"]').on('click', 'div[name="deleteClassRoom"]', function () {
        var flag = confirm('确认要删除此教室吗?');
        if (!flag) {
            return;
        }
        var roomId = $(this).attr('roomId');
        var operateSchoolCode = $(this).parents('tr').attr('schoolCode');
        $.ajax({
            url: CONTEXT_PATH + '/classroom/deleteClassRoomById',
            data: {roomId: roomId, operateSchoolCode: operateSchoolCode},
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('#doSearch').trigger('click');
                }
            }
        })
    });
};

//根据年级id查询班级
function GradeAndClassById() {
    $(document).on('change', '#gradeId', function () {
        var data = {
            gradeId: $("#gradeId").val(),
            buildingId: $("#buildingId").val()
        };
        var $this = $('select[name="classId"]');
        $.ajax({
            url: CONTEXT_PATH + '/classroom/selectGradeAndClassById',
            data: data,
            dataType: 'json',
            type: 'post',
            success: function (result) {
                console.log(result);
                $this.empty();
                var html = '';
                for (var i = 0; i < result.length; i++) {
                    html += '<option value=' + result[i].id + '>' + result[i].className + '</option>';
                }
                if (result.length < 1) {
                    html += '<option value="">未查询到闲置班级!</option>';
                }
                $this.append(html);
            }
        })
    });
};


$(function () {
    gotoPage();
    searchRoomList();
    $('#doSearch').trigger('click');


    //新增 修改教室调用
    openDialog();
    closeDialog();
    doSaveClassroom();
    GradeAndClassById();
    //删除教室
    deleteClassRoom();


    //绑定设备弹框
    openBundingWindow();
    //改变设备类型加载不同的设备选择
    changePeibeileixing();
    //执行设备绑定
    doSaveBundingEquipments();
    //修改绑定弹框
    modifyBundingEquipment();
    //执行修改绑定
    doModifyBundingEquipments();
});