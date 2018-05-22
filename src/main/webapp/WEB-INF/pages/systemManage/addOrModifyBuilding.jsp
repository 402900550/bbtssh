<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 09:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p class="small_title"></p>
<input type="hidden" value="${currentBuilding.id}" name="buildingId"/>
<input type="hidden" value="${currentSchoolCode}" name="schoolCode"/>
<input type="hidden" value="" name="doType"/>

<div class="input_warp">
    <p class="input_title">教学楼名称：</p>
    <input name="buildingName" class="input_pop" type="text" placeholder="请输入教学楼名称" value="${currentBuilding.buildingName}"/>
</div>


<p class="keep_pop">保存</p>
<p class="close_pop">取消</p>