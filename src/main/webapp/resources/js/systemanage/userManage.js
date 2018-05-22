// 编辑按钮
function changeInputStatus() {
    //编辑
    $(".use_change").click(function(){
        $(this).hide();
        $(".use_keep").show();
        $(".use_cancel").show();
        $(".user_input").attr('disabled', false).css("background","rgba(0, 0, 0, 0.1)");
        $(".user_select").attr('disabled', false).css("background","rgba(0, 0, 0, 0.1)");
        $("#roleId").hide();
    });
    $(".use_keep").click(function(){
        saveUserInfo();
        $(this).hide();
        $(".use_cancel").hide();
        $(".use_change").show();
        $(".user_input").attr('disabled', true).css("background","rgba(0, 0, 0, 0)");
        $(".user_select").attr('disabled', true).css("background","rgba(0, 0, 0, 0)");
    });
    $(".use_cancel").click(function(){
        loadUserInfo();
        $(this).hide();
        $(".use_keep").hide();
        $(".use_change").show();
        $(".user_input").attr('disabled', true).css("background","rgba(0, 0, 0, 0)");
        $(".user_select").attr('disabled', true).css("background","rgba(0, 0, 0, 0)");
    });

}
//获取参数
function getData() {
    var data={};
    $('.basic').find('select,input,textarea').each(function () {
        var attr=$(this).attr('name');
        //特殊处理
        var value=$(this).val();
        data[attr]=value;
    });
    return data;
}
//加载个人信息
function loadUserInfo() {

    $.ajax({
        url: CONTEXT_PATH + '/user/loadUserInfo',
        dataType: 'html',
        type: 'post',
        data: {},
        async: false,
        success: function (result) {
            $('.basic').html(result);
        }
    });
};
//保存用户信息
function saveUserInfo() {

    var data = getData();
    $.ajax({
        url:CONTEXT_PATH+'/user/addOrUpdateUser',
        type:'post',
        data:data,
        dataType:'json',
        async:false,
        success:function (result) {
            alert(result.message);
            if (result.success==true){
                loadUserInfo();
            }
        }
    })

}

//查询用户信息
function  selectUser(page) {
    var json = {
        schoolName: $("#schoolName").val(),
        distinctName: $("#distinctName").val(),
        userName: $("#userName").val(),
        startNumber: page,
        sizeNumber: 10
    }
    $.ajax({
        url:CONTEXT_PATH+"/user/selectUserList",
        data:json,
        type:"POST",
        dataType : "html",
        success: function(data) {

            $("#userLists").html(data);

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
            $(".userData tr").remove();
        }
    })
}

//点击查询用户列表
function searchUserList() {
    $('#doSearch').on('click',function () {
        selectUser(1);
    });
}

//分页
function gotoPage() {
    $(document).on('click','.ui-pagination-page-item',function () {
        var goPage=+$(this).attr('data-current');
        selectUser(goPage);
    });
}




//创建弹框加载用户数据
function creatDialog(title,userId) {
    var dialog=new Dialog();
    dialog.showShortDialog();
    $.ajax({
        url:CONTEXT_PATH+'/user/loadUserById',
        data:{userId:userId},
        dataType:'html',
        type:'post',
        async:false,
        success:function (result) {
            $('.login').html(result);
            $('.small_title').text(title);
        }
    });
}

//打开编辑用户的弹框
function openModifyDialog() {
    $(document).on('click','div[name="modifyUser"]',function () {
        var userId=$(this).attr('userId');
        creatDialog('修改用户',userId);

    })
}
//关闭弹框
function closeDialog() {
    $(document).on('click','.close_pop',function () {
        var dialog=new Dialog();
        dialog.closeDialog();
    });
}

//确认保存用户
function saveUser() {
    $(document).on('click','#modifyKeep',function () {
        updateUser($(this));
    })
}

//修改用户
function updateUser($this) {
    var json = {
        userId: $("#userId").val(),
        schoolCode: $("#schoolCode").val(),
        realName:$("#realName").val(),
        sex:$("#sex").val(),
        birthday:$("#userBirthday").val(),
        educational:$("#educational").val(),
        professional:$("#professional").val(),
        subjectId:$("#subjectId").val(),
        userRoleId:$("#userRoleId").val(),
        icNo:$('#icardNo').val(),
    }
    $.ajax({
        url:CONTEXT_PATH+'/user/updateUserList',
        data:json,
        type:"POST",
        dataType:'json',
        async:false,
        success:function (result) {
            alert(result.message);
            if (result.success==true){
                var dialog=new Dialog();
                dialog.closeDialog();
                selectUser(1);
            }
        }
    })
}

//删除用户
function deleteUser() {
    $(document).off('click','div[name="deleteUser"]').on('click','div[name="deleteUser"]',function () {
        var flag=confirm('确认要删除此用户吗?');
        if (!flag){
            return;
        }
        var userId=$(this).attr('userId');
        var operateSchoolCode=$(this).parents('tr').attr('schoolCode');
        $.ajax({
            url:CONTEXT_PATH+'/user/deleteUserById',
            data:{userId:userId,operateSchoolCode:operateSchoolCode},
            dataType:'json',
            type:'post',
            success:function (result) {
                alert(result.message);
                if (result.success==true){
                    selectUser(1);
                }
            }
        })
    });
};


//打开增加用户的弹框
function addOpenModifyDialog() {
    $(document).on('click','#addUser',function () {
        var dialog=new Dialog();
        dialog.showShortDialog();
        $.ajax({
            url:CONTEXT_PATH+'/user/SchoolNameAndSubjectName',
            data:{},
            dataType:'html',
            type:'post',
            async:false,
            success:function (result) {
                $('.login').html(result);
            }
        });
    })
}

//确认增加用户
function saveUserAdd() {
    $(document).on('click','#addkeep',function () {
        var json = {
            schoolCode: $("#schoolCode").val(),
            realName:$("#realName").val(),
            userName:$("#loginName").val(),
            sex:$("#sex").val(),
            birthday:$("#userBirthday").val(),
            educational:$("#educational").val(),
            professional:$("#professional").val(),
            subjectId:$("#subjectId").val(),
            userRoleId:$("#userRoleId").val(),
            icNo:$('#icardNo').val()
        }
        $.ajax({
            url:CONTEXT_PATH+'/user/addUserList',
            data:json,
            type:"POST",
            dataType:'json',
            async:false,
            success:function (result) {
                alert(result.message);
                if (result.success==true){
                    var dialog=new Dialog();
                    dialog.closeDialog();
                    selectUser(1);
                }
            }
        })
    })
}



$(function () {

    loadCityList($('#distinctList'));
    loadSchoolList($('#schoolList'));

    loadUserInfo();
    changeInputStatus();

    //查询用户
    gotoPage();
    selectUser(1);
    searchUserList();
    $('#doSearch').trigger('click');

    openModifyDialog();
    closeDialog();
    saveUser();
    //删除用户
    deleteUser();
    //增加用户
    addOpenModifyDialog();
    saveUserAdd();
});


