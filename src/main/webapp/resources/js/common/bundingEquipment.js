
//绑定修改设备弹框
function openBundingWindow() {
    $(document).on('click', 'div[name="bundingEquipment"]', function () {
        var dialog = new Dialog();
        dialog.showLongDialog();
        var roomId = $(this).attr('roomId');
        var schoolCode=$(this).parents('tr').attr('schoolCode');
        $.ajax({
            url: CONTEXT_PATH + '/classroom/bundingAccessory',
            data: {roomId: roomId,schoolCode:schoolCode},
            dataType: 'html',
            async: false,
            success: function (result) {
                $('.login').html(result);
                $('.small_title').text('设备绑定');
                $('input[name="doType"]').val('bunding');
            }
        });
    });
}
//改变配备类型
function changePeibeileixing() {

    $(document).on('change', 'select[name="developType"]', function () {
        var developType = $(this).val();
        var $this = $(this).parents('div.input_warp');
        $.ajax({
            url: CONTEXT_PATH + '/classroom/loadEquipmentsByDevelopType',
            data: {developType: developType},
            dataType: 'json',
            async: false,
            success: function (result) {

                var devlops = result.develops;
                var accessories = result.accessories;
                $('div[deve="deve"]').remove();
                var html = '';
                for (var i = 0; i < devlops.length; i++) {
                    html += '<div deve="deve" class="input_warp">';
                    html += '<p  class="input_title" developId=' + devlops[i].id + '>' + devlops[i].name + ':</p>';
                    html += '<select name="chooseEq" class="input_pop select_pop">';
                    html += '<option value="">请选择设备</option>';
                    for (var j = 0; j < accessories.length; j++) {
                        var equipmentType = accessories[j].equipmentType;
                        if (equipmentType == devlops[i].id) {
                            html += '<option value=' + accessories[j].id + '>ID:' + accessories[j].id + '.' + devlops[i].name + '</option>';

                        }
                    }
                    html += '</select></div>';

                }
                $this.after(html);

            }
        });

    });

}

//执行绑定设备
function doSaveBundingEquipments() {

    $(document).on('click', '#doSaveBundingEquipments', function () {
        var developType = $('select[name="developType"]').val();
        var personCost = $('input[name="personCost"]').val();
        var manager = $('input[name="manager"]').val();
        var schoolCode=$('#theSchoolCode').val();
        var equipmentNo = $('input[name="equipmentNo"]').val();
        if (!equipmentNo) {
            alert('请输入设备号!');
            return;
        }
        var ids = '';
        $('select[name="chooseEq"]').each(function () {
            if ($(this).val()) {
                ids += $(this).val() + ',';
            }
        });
        ids = ids.substring(0, ids.length - 1);
        var roomId = $('#roomId').val();

        $.ajax({
            url: CONTEXT_PATH + '/classroom/doBundingEquipments',
            dataType: 'json',
            data: {
                roomId: roomId,
                ids: ids,
                equipmentNo: equipmentNo,
                developType: developType,
                personCost: personCost,
                manager: manager,
                schoolCode:schoolCode
            },
            async: false,
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('.close_pop').trigger('click');
                    $('#doSearch').trigger('click');
                }
            }
        });

    });

}
//修改绑定设备信息
function modifyBundingEquipment() {
    $(document).on('click', 'div[name="modifyBundingEquipment"]', function () {
        var dialog = new Dialog();
        dialog.showLongDialog();
        var roomId = $(this).attr('roomId');
        var schoolCode=$(this).parents('tr').attr('schoolCode');
        $.ajax({
            url: CONTEXT_PATH + '/classroom/modifyAccessory',
            data: {roomId: roomId,schoolCode:schoolCode},
            dataType: 'html',
            async: false,
            success: function (result) {
                $('.login').html(result);
                $('.small_title').text('修改绑定');
                $('input[name="doType"]').val('modify');
            }
        });
    });
}
//执行修改设备绑定
function doModifyBundingEquipments() {

    $(document).on('click', '#doModifyBundingEquipments', function () {
        var developType = $('select[name="developType"]').val();
        var personCost = $('input[name="personCost"]').val();
        var manager = $('input[name="manager"]').val();
        var schoolCode=$('#theSchoolCode').val();

        var equipmentNo = $('input[name="equipmentNo"]').val();
        if (!equipmentNo) {
            alert('请输入设备号!');
            return;
        }
        var ids = '';
        $('select[name="chooseEq"]').each(function () {
            if ($(this).val()) {
                ids += $(this).val() + ',';
            }
        });
        ids = ids.substring(0, ids.length - 1);
        var roomId = $('#roomId').val();

        $.ajax({
            url: CONTEXT_PATH + '/classroom/modifyBundingAccessory',
            dataType: 'json',
            data: {
                roomId: roomId,
                ids: ids,
                equipmentNo: equipmentNo,
                developType: developType,
                personCost: personCost,
                manager: manager,
                schoolCode:schoolCode,
            },
            async: false,
            type: 'post',
            success: function (result) {
                alert(result.message);
                if (result.success == true) {
                    $('.close_pop').trigger('click');
                    $('#doSearch').trigger('click');
                }
            }
        });

    });

}


