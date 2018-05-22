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
<input type="hidden" value="${classes.id}" name="classId"/>
<input type="hidden" value="" name="doType"/>

<div class="input_warp">
    <p class="input_title" >选择学校:</p>
    <select name="schoolCode" class="input_pop select_pop" <c:if test="${currentRole.roleLevel lt EDUCATION}">disabled</c:if> >
        <c:forEach items="${allAchool}" var="as">
            <option value="${as.schoolCode}" <c:if test="${school.schoolCode == as.schoolCode}">selected</c:if> >${as.name}</option>
        </c:forEach>
    </select>
</div>


<div class="input_warp">
    <p class="input_title">选择年级:</p>
    <select name="gradeId" class="input_pop select_pop" >
        <option value="">选择年级</option>
        <c:forEach items="${gradeList}" var="item">
            <option value="${item.id}"  <c:if test="${item.id==gradeId}">selected</c:if> >${item.name}</option>
        </c:forEach>
    </select>
</div>


<div class="input_warp">
    <p class="input_title" >选择班级号:</p>
    <input class="input_pop" name="className" maxlength="3" value="${classes.className}" placeholder="输入班级(如一班,二班)"/>
</div>

<div class="input_warp">
    <p class="input_title" >选择教学楼:</p>
    <select name="buildingId" class="input_pop select_pop" >
        <option value="">选择教学楼</option>
        <c:forEach items="${buildingList}" var="item">
            <option value="${item.id}" <c:if test="${item.id==building.id}">selected</c:if> >${item.buildingName}</option>
        </c:forEach>
    </select>
</div>

<div class="input_warp">
    <p class="input_title" >设置教室编号:</p>
    <select name="roomId" class="input_pop select_pop" >
        <option value="">选择教室</option>
        <c:if test="${classroom.roomCode!=null}"><option selected value="${classroom.id}">${classroom.roomCode}</option></c:if>
        <c:forEach items="${classroomList}" var="room">
            <option value="${room.id}">${room.roomCode}</option>
        </c:forEach>
    </select>

</div>



<p class="keep_pop" id="saveClass">保存</p>
<p class="close_pop">取消</p>
