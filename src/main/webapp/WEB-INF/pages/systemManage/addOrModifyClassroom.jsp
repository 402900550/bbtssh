<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-31
  Time: 09:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p class="small_title"></p>
<input type="hidden" value="${buildingId}" id="buildingId" name="buildingId"/>
<input type="hidden" value="${currentRoom.id}" name="roomId"/>
<input type="hidden" value="" name="doType"/>
<div class="input_warp">
    <p class="input_title" >房间类型:</p>
    <select name="roomType" class="input_pop select_pop" >
        <option value="1" <c:if test="${currentRoom.roomType==1}">selected</c:if> >教室</option>
        <option value="2" <c:if test="${currentRoom.roomType==2}">selected</c:if> >办公室</option>
    </select>
</div>

<div class="input_warp">
    <p class="input_title">房间编号:</p>
    <input name="roomCode" class="input_pop" type="text" placeholder="请输入房间编号" value="${currentRoom.roomCode}"/>
</div>





<div class="input_warp">
    <p class="input_title" >年级绑定:</p>
    <select name="gradeId" id="gradeId" class="input_pop select_pop" >
        <option value=""  >请选择年级</option>
        <c:forEach items="${gradeS}" var="gd">
            <option value="${gd.id}" <c:if test="${classGradeId.gradeId==gd.id}">selected</c:if> >${gd.name}</option>
        </c:forEach>
    </select>
</div>


<div class="input_warp">
    <p class="input_title" >班级绑定:</p>
    <select name="classId" class="input_pop select_pop" >
        <option value=""  >请先选择年级</option>
        <c:if test="${classGradeId.id!=null}">
            <option value="${classGradeId.id}"  <c:if test="${classGradeId.id!=null}">selected</c:if> >${classGradeId.className}</option>
        </c:if>
        <c:forEach items="${classes}" var="ca">
            <option value="${ca.id}">${ca.className}</option>
        </c:forEach>
    </select>
</div>



<p class="keep_pop" id="saveClassroom">保存</p>
<p class="close_pop">取消</p>
