<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/15 0015
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>数据分析</title>
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
        <div class="select_backgroud">
            <div class="select_backgroud02">
                <input class="input_down" placeholder="沙坪坝区" selected disabled />
            </div>
        </div>
        <div class="select_backgroud">
            <div class="select_backgroud02">
                <input id="schoolName"  autocomplete="off"  list="schoolList" class="input_down" placeholder="请输入想要查找的学校" />
                <div style="overflow: auto"><datalist id="schoolList"></datalist></div>
            </div>
        </div>
        <div class="search">
            <img id="query" src="<c:url value="/resources/img/search.png"/>" />
        </div>
    </div>
    <!--这是搜索框结束-->

    <!--这是数据统计开始-->
    <div class="data_statistics">
        <p class="statisticsTitle"><span>沙坪坝区</span>-数据统计</p>
        <p class="statistics_triangle"></p>
        <div class="statistics_warp">
            <ul class="statistics_choice">
                <li class="school_display">
                    学校
                </li>
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
                <ul class="school_header">
                    <li>排名</li>
                    <li>学校名称</li>
                    <li>使用率</li>
                </ul>

                <ul class="subject_header">
                    <li>排名</li>
                    <li>学科名称</li>
                    <li>使用课时数</li>
                </ul>
                <ul class="teacher_header">
                    <li>排名</li>
                    <li>学校</li>
                    <li>姓名</li>
                    <li>使用率</li>
                </ul>
                <ul class="grade_header">
                    <li>排名</li>
                    <li>学校</li>
                    <li>班级</li>
                    <li>使用率</li>
                </ul>
                <ul class="school_show">
                </ul>

                <ul class="subject_show">

                </ul>
                <ul class="teacher_show">

                </ul>
                <ul class="grade_show">

                </ul>
            </div>
        </div>
    </div>
    <!--这是数据统计开始-->

    <!--这是数据概况开始-->
    <div class="data_survey">
        <p class="statisticsTitle"><span>沙坪坝区</span>-数据概况</p>
        <p class="statistics_triangle"></p>
        <div class="myChart_warp">
            <div id='myChart_four'>
                <p class="circular">运维</p>
            </div>
            <div id='myChart_five' class="myChart_five">
                <p class="circular" style="margin-left: 144px;">使用</p>
            </div>
            <%--<div id='myChart_six' class="myChart_six">--%>
                <%--<p class="circular class_use">使用</p>--%>
            <%--</div>--%>
            <div class="money">投入资金</div>
            <div id='myChart_seven' class="asset_graph"></div>
        </div>
    </div>

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
<script src="<c:url value="/resources/js/myChart_four.js"/> "></script>
<script src="<c:url value="/resources/js/myChart_five.js"/> "></script>
<script src="<c:url value="/resources/js/myChart_six.js"/> "></script>
<script src="<c:url value="/resources/js/myChart_seven.js"/> "></script>
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/dataAnalysis/dataAnalysis.js"/> "></script>
</html>
