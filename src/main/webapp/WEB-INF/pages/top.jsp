<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css"/>">
</head>
<body>

<header>
    <p class="userName">欢迎您!&nbsp; <span>${currentUser.realName}</span></p>
    <p class="waybill">待处理运维单：&nbsp;(<span>${pendingCount}</span>)</p>
    <b>I</b>
    <div class="cancellation_warp">
        <img src="<c:url value="/resources/img/cancellation.png"/>" class="cancellation" />
        <p class="cancellation02">注销</p>
    </div>
    <div class="logo">
        <img src="<c:url value="/resources/img/logos.png"/>" />
        <p class="logoTitle">物联网智慧教室管理系统</p>
    </div>
    <nav id="navigationBar">
        <div class="homeLeft"></div>
        <div class="home">
            <div id="backIndex">首页</div>
        </div>
        <div class="navLeft"></div>
        <ul>
            <li>
                <div>应用监测</div>
                <ul>
                    <li url="applicationMonitoring/applicationMain" ><p>·实时数据</p></li>
                    <li url="applicationMonitoring/usedRecord" ><p>·设备使用记录</p></li>
                </ul>
            </li>
            <li>
                <div>运维管理</div>
                <ul class="nav02">
                    <li url="operationsManagement/maintainStatistics"><p>·运维统计</p></li>
                </ul>
            </li>
            <li>
                <div>资产管理</div>
                <ul class="nav03">
                    <li url="assetManage/equipmentCount" forwardType="equipmentCount" ><p>·设备统计</p></li>
                    <sec:authorize access="${currentRole.roleLevel gt DISTNCTUSER}">
                        <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                            <li url="assetManage/equipmentInput" forwardType="equipmentInput" ><p>·设备录入</p></li>
                        </sec:authorize>
                    </sec:authorize>
                    <li url="assetManage/equipmentSearch" ><p>·设备查询</p></li>
                </ul>
            </li>
            <li>
                <div>数据分析</div>
                <ul class="nav04">
                    <li></li>
                    <li></li>
                    <li></li>
                    <li url="dataAnalysis/dataAnalysis"><p>·应用统计</p></li>
                </ul>
            </li>
            <li>
                <div>系统设置</div>
                <ul class="nav05">
                    <li url="systemManage/school"><p>·学校管理</p></li>
                    <li url="systemManage/classManage"><p>·班级管理</p></li>
                    <li url="systemManage/userManage"><p>·用户管理</p></li>
                    <li url="systemManage/parameterManage"  forwardType="parameter" ><p>·参数管理</p></li>
                    <li url="systemManage/schedule" forwardType="schedule" ><p>·作息时间</p></li>
                </ul>
            </li>
            <li>
                <div>下载信息</div>
            </li>
        </ul>
        <div class="navRight"></div>
    </nav>
</header>
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/index/top.js"/>"></script>
<script>
    var CONTEXT_PATH='<%=request.getContextPath()%>';
</script>
</html>
