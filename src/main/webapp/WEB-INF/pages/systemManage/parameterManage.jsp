<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>参数管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />
</head>

<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location">
        <span>系统设置</span> > <span>参数管理</span>
    </div>
    <div class="app_warp">
        <!--这是搜索框开始-->
        <div class="current_position" >系统设置---参数管理<div></div></div>
        <div class="choice">
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <select id="parameterType">
                        <option value="">参数类型</option>
                        <option value="1">品牌</option>
                        <option value="2">规格型号</option>
                    </select>
                </div>
            </div>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input class="input_down" id="parameterValue" type="text" placeholder="请输入参数值"/>
                </div>
            </div>

            <div class="search" id="doSearch">
                <img src="<c:url value="/resources/img/search.png"/> "/>
            </div>
            <sec:authorize access="${currentRole.roleLevel gt TEACHER}">
                <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                    <div class="button" id="addParameter">添加参数</div>
                    <div class="button" id="addParameterType">添加参数类型</div>
                </sec:authorize>
            </sec:authorize>
        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="realTime_data">
            <table class="realTime_table">
                <thead>
                <tr>
                    <th style="width: 150px;">参数类型名称</th>
                    <th style="width: 200px;">参数值</th>
                    <th>备注</th>
                    <th style="width: 100px;">操作</th>
                </tr>
                </thead>
                <tbody id="parameterTable">

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
<script src="<c:url value="/resources/js/systemanage/parameterManage.js"/> "></script>
</html>