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
    <meta charset="utf-8" />
    <title>设备统计-班级</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />
</head>
<body>
<!--这是没引用完导航，到时候替换便是-->
<jsp:include page="../top.jsp"/>
<!--这是没引用完导航，到时候替换便是-->
<!--这是主要内容开始-->
<div class="main_warp">
    <div class="app_warp">
        <!--这是搜索框开始-->
        <div class="current_position" >设备统计---班级<div></div></div>
        <sec:authorize access="${currentRole.roleLevel gt TEACHER}">
            <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                <div class="choice">
                    <div class="button" id="addEquipment">添加设备</div>
                </div>
            </sec:authorize>
        </sec:authorize>
        <!--这是搜索框结束-->

        <!--这是监测数据头部开始-->
        <div class="realTime_data">
            <table class="realTime_table">
                <thead>
                <tr>
                    <th>设备名称</th>
                    <th>规格型号</th>
                    <th>单价</th>
                    <th>配备日期</th>
                    <th>运行总时间</th>
                    <th>维修次数</th>
                    <th>设备状态</th>
                    <th>维护倒计时</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="classEquipmentTable">
                </tbody>
            </table>

            <div id="pagination2" class="page"></div>

        </div>
        <!--这是监测数据头部结束-->


    </div>


    <!--这是下面的折线图数据展示结束-->

    <!--这是版权开始-->
    <footer>
        <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
    </footer>
    <!--这是版权结束-->
</div>
<input type="hidden" id="classId" value="${classId}"/>
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/diagram.js"/> "></script>
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/assetManage/equipmentclass.js"/> "></script>


</html>

