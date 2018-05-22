<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>首页</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/index.css"/>">

</head>
<body>
<jsp:include page="top.jsp"/>
<div class="main_warp">

    <!--这是环境监测开始-->
    <div class="environmental">
        <p class="dataTitle">环境监测</p>
        <p class="triangle-topright"></p>
        <ul class="region">
            <li qxmId="123623" >
                <div style="background: #7925d0;"></div>
                <span>主城区</span>
            </li>
            <li qxmId="500106123623">
                <div style="background: #e37363;"></div>
                <span>西部地区</span>
            </li>
            <li qxmId="500106111226" >
                <div style="background: #7abdaf;"></div>
                <span>歌乐山片区</span>
            </li>
        </ul>
        <div class="science_monitor">

        </div>
    </div>
    <!--这是环境监测结束-->

    <!--这是资产管理开始-->
    <div class="asset_management">
        <p class="managementTitle">
            <span>沙坪坝区—</span>资产管理
        </p>
        <p class="management_left"></p>
        <p class="management_right"></p>
        <ul class="management_date">
            <li>
                监测班级数/监测率<br />
                <span>${page1[5]}/${page1[6]*100}%</span>
            </li>
            <li>
                资产总额/设备套数
                <span>${page1[1]+page1[2]}元/${page1[5]}套</span>
            </li>
            <li>
                完好数/完好率
                <span>${page2[2]}/${page2[1]*100}%</span>
            </li>
            <li style="width: 80px;">
                使用率
                <span>
                    <c:if test="${theEmploy==0}">
                        0%
                    </c:if>
                     <c:if test="${theEmploy!= 0}">
                         ${theEmploy*100}%
                     </c:if>
                </span>
            </li>
        </ul>
        <!--这是下面的树形图数据展示开始-->
        <div class="money">投入资金</div>
        <div id='myChart' class="asset_graph"></div>
        <!--这是下面的树形图数据展示结束-->
    </div>
    <!--这是资产管理结束-->

    <!--这是运维管理开始-->
    <div class="maintenance">
        <p class="dataTitle">运维管理</p>
        <p class="triangle-topright"></p>
        <div class="maintenance_graph" id='myChart_two'></div>
    </div>
    <!--这是运维管理结束-->

    <!--这是数据统计开始-->
    <div class="dataAnalysis">
        <p class="dataTitle">数据统计</p>
        <p class="triangle-topright"></p>
        <ul class="rankingTitle">
            <li class="school" checkType="school">
                学校
            </li>
            <li class="subject" checkType="subject">
                学科
            </li>
            <li class="teacher" checkType="teacher">
                老师
            </li>
        </ul>
        <ul class="dataAnalysis_header" headerType="school">
            <li>排名</li>
            <li>学校名称</li>
            <li>使用率</li>
        </ul>

        <ul class="dataAnalysis_header" headerType="subject">
            <li>排名</li>
            <li>学科</li>
            <li>使用课时数</li>
        </ul>
        <ul class="dataAnalysis_header" headerType="teacher">
            <li>排名</li>
            <li>学校</li>
            <li>姓名</li>
            <li>使用率</li>
        </ul>
        <div class="data_display">


        </div>
        <div class="ascending" ></div>
        <div class="descending" ></div>
    </div>

    <!--这是数据统计结束-->

    <!--这是应用监测开始-->
    <div class="monitor">
        <p class="dataTitle">应用监测</p>
        <p class="triangle-topright"></p>
        <div class="monitor_graph" id='myChart_three'></div>
    </div>
    <!--这是应用监测结束-->
</div>
<!--这是主要内容结束-->

<!--这是版权开始-->
<footer>
    <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/index/top.js"/>"></script>
<script src="<c:url value="/resources/js/index/index.js"/>"></script>
<script src="<c:url value="/resources/js/diagram.js"/>"></script>
<script src="<c:url value="/resources/js/myChart.js"/> "></script>
<script src="<c:url value="/resources/js/myChart_two.js"/>"></script>
<script src="<c:url value="/resources/js/myChart_three.js"/>"></script>

<script>
    var CONTEXT_PATH='<%=request.getContextPath()%>';
    var assetData="${chartData}";
</script>
</html>
