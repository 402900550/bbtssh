<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>应用监测</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />
</head>

<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location" style="width: 420px">
        <span>应用监测</span> > <span>实时数据</span> > <span>${schoolName  .name}-环境监测</span>
    </div>
    <div class="app_warp">
        <!--这是搜索框开始-->
        <div class="current_position" >${schoolName.name}---环境监测<div></div></div>
        <div class="choice">
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input class="input_down" id="className" type="text" placeholder="输入班级或年级"/>
                    <input type="hidden" id="schoolCode" value="${schoolCode}"/>
                </div>
            </div>

            <div class="search" id="doSearch">
                <img src="<c:url value="/resources/img/search.png"/> "/>
            </div>
        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="realTime_data">
            <table class="realTime_table">
                <thead>
                <tr>
                    <th style="width: 200px;">教学楼-房间编号</th>
                    <th>班级</th>
                    <th>温度</th>
                    <th>湿度</th>
                    <th>PM2.5</th>
                    <th>照度</th>
                    <th>噪音</th>
                </tr>
                </thead>
                <tbody id="singleSchoolTable">

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
<script src="<c:url value="/resources/js/applicationMonitoring/enviroment.js"/> "></script>
</html>