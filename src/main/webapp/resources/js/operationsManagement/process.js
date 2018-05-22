function submitApproval() {

    $('#doSubmitApp').on('click', function () {
        //处理意见
        var approval = $('#approval').val();
        //处理方式
        var pendingType = $('#pendingType').val();
        //处理单位代码
        var pendingCompany = $('#chooseSchool').val();
        var birthType = $('#birthType').val();
        var taskId=$('#taskId').val();
        var processId=$('#processId').val();
        var accessoryId=$('#chooseEquipment').val();
        $.ajax({
            url: CONTEXT_PATH + '/operation/submitAdvice',
            data: {
                approval: approval,
                pendingType: pendingType,
                pendingCompany: pendingCompany,
                birthType: birthType,
                taskId:taskId,
                processId:processId,
                accessoryId:accessoryId
            },
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success){
                    location.reload();
                }
            }
        })

    });

}


function chooseChoice() {
    $('#pendingType').on('change', function () {
        var type = $(this).val();
        if (type == 'no') {
            $('#chooseCompany').show();
        } else {
            $('#chooseCompany').hide();
        }
    })
}


$(function () {
    submitApproval();
    chooseChoice();
});