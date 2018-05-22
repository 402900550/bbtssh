<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-27
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<p class="small_title">添加用户</p>
<input type="hidden" id="userId" value="${userList.id}" />

<div class="input_warp">
    <p class="input_title">学校名称:</p>
    <select class="input_pop select_pop" id="schoolCode"  <c:if test="${currentRole.roleLevel lt EDUCATION}">disabled</c:if> >
        <c:forEach items="${schoolName}" var="sn">
            <c:if test="${currentRole.roleLevel eq HUANFANGADMIN}">
                <option value="${sn.schoolCode}" <c:if test="${userList.schoolCode==sn.schoolCode}">selected</c:if> >${sn.name}</option>
            </c:if>
            <c:if test="${currentRole.roleLevel eq DISTNCTADMIN}">
                <option value="${sn.schoolCode}" <c:if test="${userList.schoolCode==sn.schoolCode}">selected</c:if> >${sn.name}</option>
            </c:if>
            <c:if test="${currentRole.roleLevel eq SCHOOLADMIN}">
                <c:if test="${userList.schoolCode==sn.schoolCode}"><option value="${sn.schoolCode}">${sn.name}</option></c:if>
            </c:if>
        </c:forEach>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">用户名:</p><input class="input_pop"   id="loginName" type="text" class="school_all"/>
</div>

<div class="input_warp">
    <p class="input_title">姓名:</p><input class="input_pop"   id="realName" type="text" class="school_all"/>
</div>

<div class="input_warp">
    <p class="input_title">IC卡号:</p><input class="input_pop" id="icardNo" />
</div>

<div class="input_warp">
    <p class="input_title">性别:</p>
    <select id="sex" class="input_pop" >
        <option value="1" >男</option>
        <option value="2" >女</option>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">出生年月:</p>
    <input class="input_pop input_date" type="date" id="userBirthday" />
</div>

<div class="input_warp">
    <p class="input_title">学历:</p><input class="input_pop" id="educational" type="text" />
</div>

<div class="input_warp">
    <p class="input_title">专业:</p><input class="input_pop" id="professional" type="text" />
</div>

<div class="input_warp">
    <p class="input_title">学科:</p>
    <select class="input_pop select_pop" id="subjectId"  >
        <c:forEach items="${subject}" var="sub">
            <option value="${sub.id}">${sub.subjectName}</option>
        </c:forEach>
    </select>
</div>



<div class="input_warp">
    <p class="input_title" >角色:</p>
    <select class="input_pop select_pop" id="userRoleId"  >
        <c:forEach items="${userRoleId}" var="urd">
            <c:if test="${currentRole.roleLevel eq DISTNCTADMIN}">
                <c:if test="${urd.roleName eq 'TEACHER'}"><option value="${urd.id}">${urd.description}</option></c:if>
                <c:if test="${urd.roleName eq 'DISTNCTUSER'}"><option value="${urd.id}">${urd.description}</option></c:if>
                <c:if test="${urd.roleName eq 'SCHOOLADMIN'}"><option value="${urd.id}">${urd.description}</option></c:if>
                <c:if test="${urd.roleName eq 'COMPANY'}"><option value="${urd.id}">${urd.description}</option></c:if>
            </c:if>
            <c:if test="${currentRole.roleLevel eq SCHOOLADMIN}">
                <c:if test="${urd.roleName eq 'TEACHER'}"><option value="${urd.id}">${urd.description}</option></c:if>
            </c:if>
            <c:if test="${currentRole.roleLevel eq HUANFANGADMIN}">
                <option value="${urd.id}">${urd.description}</option>
            </c:if>
        </c:forEach>
    </select>
</div>

<p class="keep_pop" id="addkeep">保存</p>
<p class="close_pop">取消</p>