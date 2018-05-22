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
    <title>运维统计</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />
</head>
<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location">
        <span>运维管理</span> > <span>运维统计</span>
    </div>
    <div class="app_warp">
        <div class="current_position" >运维管理---运维统计<div></div></div>
        <!--这是搜索框开始-->
        <div class="choice_operations">
            <ul>
                <li state="1" >
                    待处理
                </li>
                <li state="0" >
                    已完成
                </li>
                <li state="2">全部</li>

            </ul>
            <sec:authorize access="${currentRole.roleName eq 'SCHOOLADMIN'}">
                <div class="the_add" id="addException">添加</div>
            </sec:authorize>
        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="realTime_data" style="height: 650px;overflow-y: scroll;">
            <table class="realTime_table">

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
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/operationsManagement/maintainStatistics.js"/> "></script>

<!--这是分页按钮js部分结束-->
</html>

