<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 09:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p class="small_title">添加异常</p>

<div class="input_warp">
    <p class="input_title">异常年级：</p>
    <select  class="input_pop select_pop" id="gradeId">
        <option value="">请选择</option>
        <c:forEach items="${grades}" var="gra">
            <option value="${gra.id}">${gra.name}</option>
        </c:forEach>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">异常班级：</p>
    <select  class="input_pop select_pop" name="classId" id="classId">
        <option value="">请选择</option>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">处理单位：</p>
    <select  class="input_pop select_pop" name="schoolCode">
        <option value="">请选择</option>
        <c:forEach items="${schools}" var="sss">
            <option value="${sss.schoolCode}">${sss.name}</option>
        </c:forEach>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">异常类型：</p>
    <select  class="input_pop select_pop" name="exceptionType">
        <option value="维修设备">维修设备</option>
        <option value="更换设备">更换设备</option>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">异常描述：</p>
    <textarea class="input_pop textarea_input" name="exceptionDescription"></textarea>
</div>

<p class="keep_pop" id="saveException">保存</p>
<p class="close_pop" id="cancelException">取消</p>