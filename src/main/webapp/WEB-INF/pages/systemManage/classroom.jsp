<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>教室管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />

</head>
<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location">
        <span>系统设置</span> > <span>教室管理</span>
    </div>
    <div class="app_warp">
        <div class="current_position" >${currentSchool.name}---${currentBuilding.buildingName}---教室列表<div></div></div>
        <!--这是搜索框开始-->
        <div class="choice">
            <input type="hidden" id="currentSchoolCode" value="${currentSchool.schoolCode}" />
            <input id="currentBuildingId" class="shoolNames inputName" type="hidden" value="${currentBuilding.id}"/>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input id="classroomCode" autocomplete="off" class="input_down" placeholder="输入教室编号"/>
                </div>
            </div>
            <div class="search" id="doSearch">
                <img src="<c:url value="/resources/img/search.png"/> "/>
            </div>
            <sec:authorize access="${currentRole.roleLevel eq SCHOOLADMIN}">
                <sec:authorize access="${currentUser.schoolCode eq currentSchool.schoolCode}">
                    <div class="button" id="addRoom">添加教室/办公室</div>
                </sec:authorize>
            </sec:authorize>
        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="realTime_data">
            <table class="realTime_table">
                <thead>
                <tr>
                    <th>区县</th>
                    <th>学校名称</th>
                    <th>教学楼</th>
                    <th>教室/办公室编号</th>
                    <th>类型</th>
                    <th>使用班级</th>
                    <th style="width: 240px">已绑定设备号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="classroomTable">

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
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/common/bundingEquipment.js"/> "></script>
<script src="<c:url value="/resources/js/systemanage/classroom.js"/> "></script>
<!--这是分页按钮js部分开始-->
<!--这是分页按钮js部分结束-->
</html>

