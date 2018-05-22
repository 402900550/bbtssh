<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-23
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>教学楼管理</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/building.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />
</head>
<body>
<jsp:include page="../top.jsp"/>

<div class="main_warp">
    <div class="location">
        <span>系统设置</span> > <span>教学楼管理</span>
    </div>


    <!--这是搜索框开始-->
    <div class="choice">
        <div class="select_backgroud">
            <div class="select_backgroud02">
                <input  type="hidden" id = "distinctName"type="text" placeholder="请输入区县" />
                <input  type="hidden" id="schoolCode" type="text" value="${modifySchoolCode}" placeholder="请输入学校"/>
                <input autocomplete="off" id="buildingName" class="input_down" placeholder="输入想要查找的学校楼"/>
            </div>
        </div>
        <div class="search" id="doSearch">
            <img src="<c:url value="/resources/img/search.png"/> "/>
        </div>
    </div>
    <!--这是搜索框结束-->


    <!--这是具体教学楼管理开始-->
    <ul class="building">

    </ul>
    <!--这是具体教学楼管理结束-->
    <div id="pagination2" class="page"></div>
</div>


<!--这是版权开始-->
<footer>
    <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
<script src="<c:url value="/resources/js/systemanage/building.js"/>"></script>
</html>
