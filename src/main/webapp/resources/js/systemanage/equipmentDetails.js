function changeInputDetails(){

    $('.click_edit').on('click',function(){
        $(this).hide();
        $(".click_keep").show();
        $(".equipmentDetails>li>input").attr('disabled',false);
        $(".equipmentDetails>li>textarea").attr('disabled',false);
    });
    $('.click_keep').on('click',function(){
        $(this).hide();
        $(".click_edit").show();
        $(".equipmentDetails>li>input").attr('disabled',true);
        $(".equipmentDetails>li>textarea").attr('disabled',true);
    });

}

//申请报废
function applicationForScrap() {

    $(document).on('click','#applicationForScrap',function () {
        var accessoryId=$(this).attr('accessoryId');
        var classId=$('#classId').val();
        var flag=confirm("确认要提交给教委进行报废处理吗？");
        if (flag){
            $.ajax({
                url:CONTEXT_PATH+'/operation/applicationForScrap',
                type:'post',
                data:{accessoryId:accessoryId,classId:classId},
                dataType:'json',
                success:function (result) {
                    alert(result.message);
                }
            })
        }
    });

};


$(function(){

    changeInputDetails();

    applicationForScrap();
});
