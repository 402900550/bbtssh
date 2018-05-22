<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>设备查询</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/equipmentDetails.css"/> ">

</head>
<body>
<!--这是没引用完导航，到时候替换便是-->
<jsp:include page="../top.jsp"/>
<!--这是没引用完导航，到时候替换便是-->
<div class="main_warp">
    <!--这是页面定位开始-->
    <div class="location">
        <span>资产管理</span> > <span>设备详情</span>
    </div>

    <div class="top_title"></div>
    <div class="content">
        <div class="choices_condition">
            <table class="table">
                <c:forEach items="${schoolClassRoom.data}" var="classes">
                    <tr>
                        <td><span class="text_span">学校名称:</span>
                            <input class="input" type="text" value="${classes[0]}" disabled/>
                        </td>
                        <td><span class="text_span">班级名称:</span>
                            <input class="input" type="text" value="${classes[1]}${classes[2]}" disabled/>
                            <input id="classId" type="hidden" value="${classId.id}" disabled/>
                        </td>
                        <td><span class="text_span">教学楼:</span>
                            <input class="input" type="text" value="${classes[3]}" disabled/>
                        </td>
                        <td><span class="text_span">房间号:</span>
                            <input class="input" type="text" value="${classes[4]}" disabled/>
                        </td>
                        <td></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td><span class="text_span">设备类型:</span>
                        <input class="input" type="text" value="${equipmentType.name}" disabled/>
                    </td>
                    <td><span class="text_span">品牌:</span>
                        <input class="input" type="text" value="${brand.detail}" disabled/>
                    </td>
                    <td><span class="text_span">型号:</span>
                        <input class="input" type="text" value="${sizeType.detail}" disabled/>
                    </td>
                    <td>
                        <span class="text_span">单价:</span>
                        <input class="input" type="text" value="${accessory.price}" disabled/>元
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="text_span">配备时间:</span>
                        <fmt:formatDate value="${accessory.purchaseDate}" var="purchaseDate" pattern="yyyy-MM-dd"/>
                        <input class="input" type="text" value="${purchaseDate}" disabled/>
                    </td>
                    <td><span class="text_span">维护周期:</span><input class="input " type="text" value="8" disabled/>年</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="5" style="height: 400px;">
                        <span class="text_span" style="display: block">主要参数:</span>
                        <textarea class="textarea" placeholder="请输入主要参数" disabled="">${sizeType.remark}</textarea>
                    </td>
                </tr>
            </table>
        </div>

    </div>
    <sec:authorize access="${currentRole.roleName eq 'SCHOOLADMIN'}">
        <div class="opinion_keep">
            <div class="keep_img"></div>
            <span id="applicationForScrap" accessoryId="${accessory.id}">申请报废</span>
        </div>
    </sec:authorize>
</div>


<!--这是版权开始-->
<footer>
    <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->

</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/systemanage/equipmentDetails.js"/> "></script>
</html>

