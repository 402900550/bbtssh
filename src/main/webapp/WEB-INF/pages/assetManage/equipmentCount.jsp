<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<title>设备统计</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
	<link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>" />

</head>

<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
	<div class="location">
		<span>资产管理</span> > <span>设备统计</span>
	</div>
	<div class="app_warp">
		<!--这是搜索框开始-->
		<div class="current_position" >资产管理---设备统计<div></div></div>
		<div class="choice">
			<div class="select_backgroud">
				<div class="select_backgroud02">
					<select id="schoolType">
						<option value="10086">学校类型</option>
					</select>
				</div>
			</div>
			<div class="select_backgroud">
				<div class="select_backgroud02">
					<input class="input_down" id="distinctName" type="text" disabled value="沙坪坝区" placeholder="沙坪坝区"/>
				</div>
			</div>
			<div class="select_backgroud">
				<div class="select_backgroud02">
					<input id="schoolName" list="schoolList" autocomplete="off" class="input_down" placeholder="请输入想要查找的学校"/>
					<datalist id="schoolList"></datalist>
				</div>
			</div>
			<div class="search" id="searchSchoolBtn">
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
					<th>区县</th>
					<th>学校名称</th>
					<th>班级总数</th>
					<th>检测数</th>
					<th>设备总数</th>
					<th>完好数</th>
					<th>完好率</th>
					<th>资产总额</th>
					<th>建设年代</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody id="equipmentCountTable">

				</tbody>
			</table>

			<div id="pagination2" class="page"></div>

		</div>
		<!--这是监测数据头部结束-->

		<div id="countChart" class="app_chart"></div>
	</div>
</div>
<!--这是主要内容结束-->

<!--这是版权开始-->
<footer>
	<p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->

</div>
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
<script src="<c:url value="/resources/js/diagram.js"/> "></script>
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script src="<c:url value="/resources/js/assetManage/equipmentCount.js"/> "></script>
<!--这是树形图js部分开始-->
<script>
    var dataArr=[];
    var dataArr1=[];
    var dataArr2=[];
    var dataArr3=[];
    $.ajax({
        url: CONTEXT_PATH + "/equipment/loadEquimentCount",
        data: {},
        type:"POST",
        dataType : "json",
        async:false,
        success: function (data) {

            for (i=0;i<data.data.length;i++){
                dataArr.push(data.data[i][1]);
                dataArr1.push(data.data[i][3]);
                dataArr2.push(data.data[i][4]);
                dataArr3.push(data.data[i][6]);
            }
        }
    })

    var myConfig = {
        "type": "bar",
        "plot":{"value-box":{
            "font-color": "#4ca3b9",
            "padding":"0px 0px 0px 0px"
        }
        },
        "background-color": "rgba(255, 255, 255, 0.)",
//        "3d-aspect": {
//            "true3d": 0,
//            "y-angle": 10,
//            "depth": 30
//        },
        "title": {
            "text": "重庆市物联网智慧教室管理系统",
            "y": "-2%",
            "height": "40px",
            "font-weight": "normal",
            "font-color": "#02ddd3"
        },
        "legend": {
            "layout": "float",
            "background-color": "none",
            "border-color": "none",
            "item": {
                "font-color": "#30e7f0"
            },
            "x": "42.5%",
            "y": "12%",
            "width": "90%",
            "shadow": 0
        },
        "plotarea": {
            "margin": "95px 35px 50px 70px"
        },
        "scale-y": {
            "alpha": 0,
            "border-left": "1px solid #41af89",
            "guide": {
                "visible": false
            },
            "tick": {
                "line-color": "#19d0d0",
                "alpha": 0.2
            },
            "item": {
                "font-color": "#41af89",
                "padding-right": "6px"
            }
        },
        "scale-x": {
            "alpha": 0.1,
            "border-bottom": "1px solid #41af89",
            "border-left": "1px solid #41af89",
            "values": dataArr,
            "guide": {
                "visible": false
            },
            "tick": {
                "visible": false
            },
            "label": {
                "font-size": "14px",
                "font-weight": "normal",
                "offset-x": "10%",
                "font-angle": 360
            },
            "item": {
                "text-align": "center",
                "font-color": "#41af89"
            },
        },
        "series": [{
            "values": dataArr2,
            "text": "检测数",
            "background-color": "#03A9F4",
            "border-color": "#03A9F4",
            "legend-marker": {
                "border-color": "#03A9F4"
            },
            "tooltip": {
                "background-color": "#03A9F4",
                "text": "检测数%v个",
                "font-size": "12px",
                "padding": "6 12",
                "border-color": "none",
                "shadow": 0,
                "border-radius": 5
            }
        }, {
            "values": dataArr3,
            "text": "完好数",
            "background-color": "#6169b4",
            "border-color": "#6169b4",
            "legend-marker": {
                "border-color": "#6169b4"
            },
            "tooltip": {
                "background-color": "#6169b4",
                "text": "完好数%v个",
                "font-size": "12px",
                "padding": "6 12",
                "border-color": "none",
                "shadow": 0,
                "border-radius": 5
            }
        }, {
            "values": dataArr1,
            "text": "班级总数",
            "background-color": "#673AB7",
            "border-color": "#673AB7",
            "legend-marker": {
                "border-color": "#673AB7"
            },
            "tooltip": {
                "background-color": "#673AB7",
                "text": "班级总数%v个",
                "font-size": "12px",
                "padding": "6 12",
                "border-color": "none",
                "shadow": 0,
                "border-radius": 5
            }
        }]
    };

    zingchart.render({
        id: 'countChart',
        data: myConfig,
        height: 300,
        width: 1600,
        defaults: {
            'font-family': 'sans-serif',
            "enabled":true
        }
    });
</script>

</html>