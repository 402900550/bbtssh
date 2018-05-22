<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>作息时间</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/schedule.css"/> ">
</head>
<body>
<!--这是没引用完导航，到时候替换便是-->
<jsp:include page="../top.jsp"/>
<!--这是没引用完导航，到时候替换便是-->

<div class="classBackdrop">
    <div class="location">
        <span>系统设置</span> > <span>作息时间表</span>
    </div>
    <div class="schedule_warp">

        <%--这是题目开始--%>
        <p class="statisticsTitle"><span>${school.name}<c:if test="${school==null}">没有学校</c:if></span>-作息时间表</p>
        <p class="statistics_triangle"></p>
        <%--这是题目结束--%>
        <!--这是作息时间选择开始-->
        <div class="choice" id="operate">
            <div class="select_backgroud">
                <div class="select_backgroud02">

                    <select id="gradeId" schoolCode="${school.schoolCode}">
                        <c:if test="${empty grades}">
                            <option value="">没有年级</option>
                        </c:if>
                        <option value="">选择年级</option>
                        <c:forEach items="${grades}" var="gra">
                            <option value="${gra[0]}">${gra[1]}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <sec:authorize access="${currentRole.roleLevel gt TEACHER}">
                <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                    <p class="btn" id="edit">编辑</p>
                    <p class="btn" id="save" style="display: none">保存</p>
                    <p class="btn" id="cancel" style="display: none">取消</p>
                </sec:authorize>
            </sec:authorize>
        </div>
        <!--这是作息时间选择结束-->

        <!--这是作息时间表开始-->
        <div class="timeData">
            <div class="grade_time">请选择年级</div>

            <!--这是作息时间表结束-->
        </div>
    </div>
    <!--这是版权开始-->
    <footer>
        <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
    </footer>
    <!--这是版权结束-->
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
<script src="<c:url value="/resources/js/systemanage/schedule.js"/> "></script>
</html>

