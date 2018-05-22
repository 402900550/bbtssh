<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-16
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="small_title"></p>
<input type="hidden" value="${theSchoolCode}" id="theSchoolCode"/>
<input type="hidden" value="${roomId}" id="roomId"/>
<div class="input_warp">
    <p class="input_title">设备号:</p>
    <input name="equipmentNo" class="input_pop" type="text" placeholder="请输入设备号" value="" />
</div>

<p class="keep_pop" id="doSaveBundingEquipments">保存</p>
<p class="close_pop">取消</p>