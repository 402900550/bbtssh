//加载所有学校类型
var loadSchoolType=function (id) {

    $.ajax({
        url:CONTEXT_PATH+'/common/loadSchoolType',
        dataType:'json',
        type:'post',
        data:{},
        success:function (result) {
            id.empty();
            id.append('<option value="10086">学校类型</option>');
            for (var i=0;i<result.length;i++){
                id.append('<option value='+result[i].id+'>'+result[i].typeName+'</option>');
            }
        }
    })


};

//加载所有学校
var loadSchoolList=function (id) {

    $.ajax({
        url:CONTEXT_PATH+'/common/loadAllSchool',
        dataType:'json',
        type:'post',
        data:{},
        success:function (result) {
            id.empty();
            for (var i=0;i<result.length;i++){
                id.append('<option value='+result[i].name+'>');
            }
        }
    })

};
//加载所有区县
var loadCityList=function (id) {

    $.ajax({
        url:CONTEXT_PATH+'/common/loadAllDistinct',
        dataType:'json',
        type:'post',
        data:{},
        success:function (result) {
            id.empty();
            for (var i=0;i<result.length;i++){
                id.append('<option value='+result[i].name+'  data-id='+result[i].detailCode+'>');
            }
        }
    })

};









