<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-07
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>设备录入</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/equipmentInput.css"/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/paging.css"/> "/>
</head>

<body>
<!--这是没引用完导航，到时候替换便是-->
<jsp:include page="../top.jsp"/>
<div class="main_warp">
    <!--这是页面定位开始-->
    <div class="location">
        <span>资产管理</span> > <span>设备录入</span>
    </div>

    <div class="top_title"></div>
    <div class="content">
        <div class="choices_condition">
            <table class="table">
                <tr>
                    <td><span class="text_span">学校:</span>
                        <select class="select" id="schoolCode"  <c:if test="${currentRole.roleLevel lt EDUCATION}">disabled</c:if> >
                            <option value=""  >请选择学校</option>
                            <c:forEach items="${allSchool}" var="schools">
                                <c:if test="${currentRole.roleLevel eq HUANFANGADMIN}">
                                    <option value="${schools.schoolCode}"  >${schools.name}</option>
                                </c:if>
                                <c:if test="${currentRole.roleLevel eq DISTNCTADMIN}">
                                    <option value="${schools.schoolCode}"  >${schools.name}</option>
                                </c:if>
                                <c:if test="${currentRole.roleLevel eq SCHOOLADMIN}">
                                    <c:if test="${school.schoolCode==schools.schoolCode}"><option value="${schools.schoolCode}">${schools.name}</option></c:if>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td><span class="text_span">配备类型:</span>
                        <select class="select" id="selectType">
                            <option value=""  >请选择配备类型</option>
                            <c:forEach items="${develops}" var="dev">
                                <c:if test="${dev.type eq 4}">
                                    <option value="${dev.id}">${dev.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>

                    <td><span class="text_span">年级:</span>
                        <select class="select"  id="gradeId" >
                            <option value=""  >请选择年级</option>
                        </select>
                    </td>
                    <td><span class="text_span">班级:</span>
                        <select class="select"  id="classId"  >
                            <option value=""  >请选择班级</option>
                            <c:forEach items="${parameters8}" var="p8">
                                <option value="${p8.id}">${p8.detail}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><span class="text_span">教学楼:</span>
                        <select class="select"  id="buildingId" disabled></select>
                    </td>
                    <td><span class="text_span">教室:</span>
                        <select class="select"  id="roomId"  disabled ></select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><span class="text_span">设备号:</span><input id="equipmentNo" class="input" type="text"/></td>
                    <td><span class="text_span">人工费:</span><input id="personCost" class="input number" type="text"/></td>
                    <td><span class="text_span">耗材费:</span><input id="datumCost" class="input number" type="text"/></td>
                    <td><span class="text_span">负责人:</span><input class="input" id="manager" type="text"/></td>
                    <td><span class="text_span">日期:</span><input class="input" type="date" style="width: 170px" id="inputDate" type="text"/></td>
                </tr>
            </table>
        </div>
        <div class="input_data">
            <div id="euqipmentData">
                <c:forEach items="${develops}" var="leixing" varStatus="status">
                    <c:if test="${leixing.type eq 1}">
                    <table class="table equipments">
                        <tr>
                            <td><span class="text_span">设备类型名称:</span>
                                <select class="select" name="equipmentType"  disabled >
                                    <option value="${leixing.id}" selected >${leixing.name}</option>
                                </select>
                            </td>
                            <td><span class="text_span">品牌:</span>
                                <select class="select" name="brand">
                                    <option value=""  >请选择品牌</option>
                                    <c:forEach items="${pinpai}" var="pai">
                                        <option value="${pai.id}" >${pai.detail}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><span class="text_span">规格型号:</span>
                                <select class="select" name="sizeType">
                                    <option value=""  >请选择规格型号</option>
                                    <c:forEach items="${guigexinghao}" var="gui">
                                        <option value="${gui.id}" >${gui.detail}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><span class="text_span " >单价:</span>
                                <input class="input number" name="price"  value="" type="text"/>元
                            </td>
                        </tr>
                    </table>
                    </c:if>
                </c:forEach>
            </div>

        </div>
        <div class="opinion_keep">
            <div class="keep_img"></div>
            <span>确认保存</span>
        </div>
    </div>

</div>


<!--这是版权开始-->
<footer>
    <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/assetManage/equipmentInput.js"/> "></script>
</html>
