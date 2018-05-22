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
    <div class="location">
        <span>应用监测</span> > <span>实时数据</span>
    </div>
    <div class="app_warp">
        <div class="current_position" >沙坪坝区---实时数据<div></div></div>
        <!--这是搜索框开始-->
        <div class="choice">
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <select disabled id="distinctName">
                        <option value="沙坪坝区">沙坪坝区</option>
                    </select>
                </div>
            </div>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input id="schoolName" autocomplete="off" list="schoolList" class="input_down" placeholder="请输入想要查找的学校"/>
                    <datalist id="schoolList"></datalist>
                </div>
            </div>
            <div class="search" id="doSearch">
                <img src="<c:url value="/resources/img/search.png"/> "/>
            </div>
        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <ul class="app_header">
            <li class="app_county">区县</li>
            <li>学校名称</li>
            <li>班级总数</li>
            <li>监测数</li>
            <li>正在使用数</li>
            <li>使用率</li>
            <li class="control">操作</li>
            <div class="line"></div>
        </ul>

        <!--这是监测数据头部结束-->

        <!--这是监测具体数据开始-->
        <div class="app_main">

        </div>
        <div id="pagination2" class="page" style="position: relative"></div>

        <!--这是监测具体数据结束-->


        <!--这是图表数据展示开始-->
        <div id='myChart_fourteen' class="Monitor_chart"></div>

        <!--这是图表数据展示结束-->
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
<script src="<c:url value="/resources/js/diagram.js"/> "></script>
<script src="<c:url value="/resources/js/applicationMonitoring/applicationMain.js"/> "></script>
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/myChart_fourteen.js"/> "></script>

<!--这是树形图js部分开始-->
<script>




</script>

</html>