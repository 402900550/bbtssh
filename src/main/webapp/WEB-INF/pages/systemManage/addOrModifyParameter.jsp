<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-31
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p class="small_title"></p>
<input type="hidden" value="${currentParameter.id}" name="parameterId"/>
<input type="hidden" value="" name="doType"/>
<div class="input_warp">
    <p class="input_title">参数类型：</p>
    <select  class="input_pop select_pop" name="parameterTypeId">
        <option value="">请选择</option>
        <c:forEach items="${parameterTypes}" var="item">
            <option value="${item.id}" <c:if test="${currentParameter.typeId==item.id}">selected</c:if> >${item.name}</option>
        </c:forEach>
    </select>
</div>
<div class="input_warp">
    <p class="input_title">参数值：</p>
    <input class="input_pop"   placeholder="输入值" name="parameterValue" value="${currentParameter.detail}"/>
</div>
<div class="input_warp">
    <p class="input_title">备注：</p>
    <textarea class="input_pop textarea_input"  name="remark">${currentParameter.remark}</textarea>
</div>

<p class="keep_pop" id="saveParameterValue">保存</p>
<p class="close_pop">取消</p>
