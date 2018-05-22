<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>学校管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />
</head>

<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location">
        <span>系统设置</span> > <span>学校管理</span>
    </div>
    <div class="app_warp">
        <!--这是搜索框开始-->
        <div class="current_position" >系统设置---学校管理<div></div></div>
        <div class="choice">
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input class="input_down" id="distinctName" type="text" disabled value="沙坪坝区"/>
                </div>
            </div>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <select id="schoolType">
                        <option value="10086">学校类型</option>
                    </select>
                </div>
            </div>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input id="schoolName" autocomplete="off" list="schoolList" class="input_down" placeholder="请输入想要查找的学校">
                    <datalist id="schoolList"></datalist>
                </div>
            </div>

            <div class="search" id="doSearch">
                <img src="<c:url value="/resources/img/search.png"/> "/>
            </div>
            <sec:authorize access="${currentRole.roleLevel gt SCHOOLADMIN}">
                <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                    <div class="button" id="addSchoolBtn">添加学校</div>
                </sec:authorize>
            </sec:authorize>
        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="school_data">
        </div>
        <div id="pagination2" class="page"></div>
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
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/systemanage/school.js"/> "></script>
</html>