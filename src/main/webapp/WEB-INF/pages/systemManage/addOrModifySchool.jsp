<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-27
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p class="small_title"></p>
<input type="hidden" value="${thisSchool.id}" name="schoolId"/>
<input type="hidden" value="" name="doType"/>

<div class="input_warp">
    <p class="input_title">选择区县:</p>
    <select class="input_pop select_pop" name="distinct">
        <option value="10086">选择区县</option>
        <c:forEach items="${distinctList}" var="item">
            <option value="${item.detailCode}"  <c:if test="${item.detailCode==thisSchool.qxm}">selected</c:if>>${item.name}</option>
        </c:forEach>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">学校名称:</p>
    <input name="schoolName" class="input_pop" type="text" placeholder="请输入学校名称" value="${thisSchool.name}"/>
</div>

<div class="input_warp">
    <p class="input_title">学校类型:</p>
    <select class="input_pop select_pop" name="schoolType">
        <option value="10086">请选择学校类型</option>
        <c:forEach items="${schoolType}" var="type">
            <option value="${type.id}" <c:if test="${thisSchool.type==type.id}">selected</c:if> >${type.typeName}</option>
        </c:forEach>
    </select>

</div>
<div class="input_warp">
    <p class="input_title" >学校代码:</p>
    <input name="schoolCode" class="input_pop" type="text" placeholder="请输入学校代码" value="${thisSchool.schoolCode}"/>
</div>

<div class="input_warp">
    <p class="input_title" >学校电话:</p>
    <input name="schoolPhone" class="input_pop" type="text" placeholder="请输入学校电话" value="${thisSchool.telephone}" />
</div>

<div class="input_warp">
    <p class="input_title" >学校地址:</p>
    <input name="schoolAddress" class="input_pop" type="text" placeholder="请输入学校地址" value="${thisSchool.address}"/>
</div>

<div class="input_warp">
    <p class="input_title" >学校简介:</p><textarea name="schoolDescription" class="input_pop textarea_input">${thisSchool.description}</textarea>
</div>

<p class="keep_pop">保存</p>
<p class="close_pop">取消</p>
