//查询教室
var searchClassOp = function (page) {

    var json = {
        distinctName: $("#distinctName").val(),
        schoolName: $("#schoolName").val(),
        className: $("#className").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url: CONTEXT_PATH + "/class/selectClassList",
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

//查询学校列表
function searchClassList() {
    $('#doSearch').on('click', function () {
        searchClassOp(1);
    });
}


//分页
function gotoPage() {
    $(document).on('click', '.ui-pagination-page-item', function () {
        var goPage = +$(this).attr('data-current');
        searchClassOp(goPage);
    });
}


//新增 修改 班级
function creatAddModifyDialog(title, data, type) {
    var dialog = new Dialog();
    dialog.showLongDialog();
    $.ajax({
        url: CONTEXT_PATH + '/class/loadClassById',
        data: data,
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

    $(document).on('click', '#addClasses', function () {
        creatAddModifyDialog('新增班级', null, 'add');
    });
    $(document).on('click', 'div[name="modifyClass"]', function () {
        var classId = $(this).parents('tr').attr('classId');
        var data = {
            classId:classId
        };
        creatAddModifyDialog('修改班级', data, 'modify');
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

//弹框上面的函数 主要是动态加载数据

//根据学校改变 改变教室
function changeClassroom() {
    $(document).on('change','select[name="buildingId"]',function () {
        var buildingId=$(this).val();
        var $this=$('select[name="roomId"]');
        $.ajax({
            url:CONTEXT_PATH+'/classroom/loadClassroomByBuilding',
            dataType:'json',
            data:{buildingId:buildingId},
            async:false,
            success:function (result) {
                $this.empty();
                var html='';
                for(var i=0;i<result.length;i++){
                    html+='<option value='+result[i].id+'>'+result[i].roomCode+'</option>';
                }
                if (result.length<1){
                    html+='<option value="">未查询到教室!</option>';
                }
                $this.append(html);
            }
        });

    })
}


//确认保存
function doSaveClasses() {
    $(document).on('click', '#saveClass', function () {
        var data = getData();
        $.ajax({
            url: CONTEXT_PATH + '/class/doSaveClass',
            data: data,
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('.close_pop').trigger('click');
                    $('#doSearch').trigger('click');
                }
            },
            error:function () {
                alert("操作失败!请检查数据是否重复!");
            }

        })


    });
};

//删除班级
function deleteClass() {
    $(document).on('click', 'div[name="deleteClass"]', function () {
        var comfir=confirm('确认要删除吗!');
        if (!comfir){
            return;
        }
        var classId = $(this).parents('tr').attr('classId');
        $.ajax({
            url: CONTEXT_PATH + '/class/doDeleteClasses',
            data: {classId:classId},
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('.queryss').trigger('click');
                    $('#doSearch').trigger('click');
                }
            }

        })
    });
}



$(function () {
    //加载学校和区县下拉框
    loadCityList($('#distinctList'));
    loadSchoolList($('#schoolList'));

    //分页查询班级
    searchClassList();
    gotoPage();
    $('#doSearch').trigger('click');


    //新增 修改班级
    openDialog();
    closeDialog();

    //加载弹框动态数据
    changeClassroom();


    //保存班级
    doSaveClasses();
    //删除班级
    deleteClass();


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