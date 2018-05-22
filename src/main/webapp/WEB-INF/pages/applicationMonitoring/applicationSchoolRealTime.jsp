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
    <div class="location" style="width: 350px">
        <span>应用监测</span> > <span>实时数据</span> > <span>${theSchool.name}</span>
    </div>
    <div class="app_warp">
        <div class="current_position" >${theSchool.name}---实时状态监测<div></div></div>
        <!--这是搜索框开始-->
        <div class="choice">
            <input type="hidden" value="${theSchool.schoolCode}" id="theSchoolCode"/>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input class="input_down" id="className" type="text" placeholder="输入班级或年级"/>
                </div>
            </div>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <select id="workStatus">
                        <option value="">控制器状态</option>
                        <option value="1">开</option>
                        <option value="0">关</option>
                    </select>
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
                    <th>设备号</th>
                    <th>ip</th>
                    <th>班级</th>
                    <th>教师</th>
                    <th>学科</th>
                    <th>控制器状态</th>
                    <th>电脑</th>
                    <th>投影</th>
                    <th>展台</th>
                    <th>电灯</th>
                    <th>插座</th>
                    <th>空调</th>
                    <th>风扇</th>
                    <th>功耗</th>
                </tr>
                </thead>
                <tbody id="singleSchoolTable">

                </tbody>
            </table>

            <div id="pagination2" class="page fl"></div>

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

<input  value="${currentRole.roleName}" type="hidden" id="roleName"/>
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
<script src="<c:url value="/resources/js/applicationMonitoring/realTime.js"/> "></script>
</html>