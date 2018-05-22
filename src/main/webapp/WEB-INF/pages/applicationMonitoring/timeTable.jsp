<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>设备使用时间</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />
</head>

<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location" style="width: 500px">
        <span>应用监测</span> > <span>实时数据</span> ><span> ${page1[0]}${page1[1]}-设备使用时间</span>
    </div>
    <div class="app_warp">
        <!--这是搜索框开始-->
        <div class="current_position" >${page1[0]}${page1[1]}---设备使用时间<div></div></div>

        <div class="choice">

        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="realTime_data">
            <table class="realTime_table">
                <thead>
                <tr>
                    <th style="width: 200px;">课节数</th>
                    <th>上课时间</th>
                    <th>下课时间</th>
                    <th>设备使用情况</th>
                    <th>开始使用时间</th>
                    <th>结束使用时间</th>
                    <th>设备使用教师</th>
                </tr>
                </thead>
                <tbody id="singleSchoolTable">
                <c:if test="${empty page}">
                    <tr>
                        <td>该年级还未录入上下课时间!</td>
                    </tr>
                </c:if>
                <c:forEach items="${page}" var="timeTable">
                    <tr>
                        <td style="width: 200px;">${timeTable[2]}</td>
                        <td>${timeTable[3]}</td>
                    <td>${timeTable[4]}</td>
                    <c:forEach items="${scheduleClass}" var="schedules">
                        <c:if test="${schedules[0]==timeTable[2]}">
                            <td>使用</td>
                            <td>${schedules[2]}</td>
                            <td>${schedules[3]}</td>
                            <td>${schedules[1]}</td>
                        </c:if>
                    </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div id="pagination2" class="page"></div>

        </div>
        <!--这是监测数据头部结束-->


    </div>
</div>
<!--这是主要内容结束-->

<!--这是版权开始-->
<footer>
    <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->


</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
</html>