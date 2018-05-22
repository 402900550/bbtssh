<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/18 0018
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>学校详情数据</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dataAnalysis.css"/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css"/> " />
</head>

<body>
<!--这是导航栏开始-->
<jsp:include page="../top.jsp"/>
<!--这是导航栏结束-->

<!--这是主要内容开始-->
<div class="main_warp">

    <!--这是搜索框开始-->
    <div class="choice">
    </div>
    <!--这是搜索框结束-->

    <!--这是数据统计开始-->
    <div class="data_statistics">
        <p class="statisticsTitle"><span>${schoolName.name}</span>-数据统计</p>
        <p class="statistics_triangle"></p>
        <div class="statistics_warp">
            <ul class="statistics_choice">
                <li class="subject">
                    学科
                </li>
                <li class="teacher">
                    老师
                </li>
                <li class="grade">
                    班级
                </li>
            </ul>
            <div class="line"></div>
            <div class="statistics">
                <ul class="subject_header" name="subject">
                    <li>排名</li>
                    <li>学科名称</li>
                    <li>使用课时</li>
                </ul>
                <ul class="teacher_header" name="teacher">
                    <li>排名</li>
                    <li>学校</li>
                    <li>姓名</li>
                    <li>使用率</li>
                </ul>
                <ul class="grade_header" name="grade">
                    <li>排名</li>
                    <li>学校</li>
                    <li>班级</li>
                    <li>使用率</li>
                </ul>
                <ul class="subject_show" name="subject">

                </ul>
                <ul class="teacher_show" name="teacher">

                </ul>
                <ul class="grade_show" name="grade">

                </ul>
            </div>
        </div>
    </div>
    <!--这是数据统计开始-->

    <!--这是数据概况开始-->
    <div class="data_survey">
        <p class="statisticsTitle"><span>${schoolName.name}</span>-数据概况</p>
        <p class="statistics_triangle"></p>
        <div class="myChart_warp">
            <div id='myChart_eight'>
                <p class="circular">运维</p>
            </div>
            <div id='myChart_nine' class="myChart_nine">
                <p class="circular">使用</p>
            </div>
            <%--<div id='myChart_ten' class="myChart_ten">--%>
                <%--<p class="circular class_use">使用</p>--%>
            <%--</div>--%>
            <div id='myChart_eleven' class="asset_graph eleven"></div>
            <ul class="control_chart">
                <li class="year" newDate="1">
                    <div></div>
                    每年
                </li>
                <li class="month" newDate="2">
                    <div></div>
                    每月
                </li>
                <li class="day" newDate="3">
                    <div></div>
                    每日
                </li>
            </ul>
        </div>
    </div>
<input type="hidden" id="schoolCode" value="${schoolCode}"/>
    <!--这是数据概况结束-->
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
<script src="<c:url value="/resources/js/diagram.js"/> "></script>
<script src="<c:url value="/resources/js/myChart_eight.js"/>"></script>
<script src="<c:url value="/resources/js/myChart_nine.js"/>"></script>
<script src="<c:url value="/resources/js/myChart_ten.js"/>"></script>
<script src="<c:url value="/resources/js/myChart_eleven.js"/>"></script>
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/dataAnalysis/dataAnalysisSchool.js"/> "></script>
</html>
