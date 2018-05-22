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
    <title>设备查询</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />

</head>
<body>
<!--这是没引用完导航，到时候替换便是-->
<jsp:include page="../top.jsp"/>
<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location">
        <span>资产管理</span> > <span>设备查询</span>
    </div>
    <div class="app_warp">
        <!--这是搜索框开始-->
        <div class="current_position" >资产管理---设备查询<div></div></div>
        <div class="choice">
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input id="distinctName" class="input_down" type="text" placeholder="请输入区县" disabled value="沙坪坝区"/>
                </div>
            </div>
            <div class="select_backgroud">
                <div class="select_backgroud02">
                    <input  id="schoolName" class="input_down" type="text" placeholder="请输入学校" list="schoolList"/>
                    <datalist id="schoolList"></datalist>
                </div>
            </div>

            <div class="search" id="doSearch">
                <img src="<c:url value="/resources/img/search.png"/> "/>
            </div>
            <sec:authorize access="${currentRole.roleLevel gt TEACHER}">
                <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                    <div class="button" id="addEquipment">添加设备</div>
                </sec:authorize>
            </sec:authorize>

        </div>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="realTime_data">
            <table class="realTime_table">
                <thead>
                <tr>
                    <th>学校</th>
                    <th>设备名称</th>
                    <th>规格型号</th>
                    <th>数量</th>
                    <th>单价</th>
                    <th>配备日期</th>
                    <%--<th>操作</th>--%>
                </tr>
                </thead>
                <tbody id="classTable">
                </tbody>
            </table>
            <!--这是班级设备查找结束-->

            <!--这是分页按钮开始-->
            <div id="pagination2" class="page"></div>
            <!--这是分页按钮结束-->
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
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/assetManage/equipmentSearch.js"/> "></script>
</html>