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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>流程管理</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/appMonitor.css"/> ">
    <link rel="stylesheet" href="<c:url value="/resources/css/paging.css"/>"/>
</head>
<body>
<jsp:include page="../top.jsp"/>

<!--这是主要内容开始-->
<div class="main_warp">
    <div class="location">
        <span>运维管理</span> > <span>运维流程</span>
    </div>
    <div class="app_warp warp">
        <div class="current_position">运维管理---运维流程
            <div></div>
        </div>

        <!--这是监测数据头部开始-->
        <div class="realTime_data">
            <table class="realTime_table">
                <thead>
                <tr>
                    <th>学校</th>
                    <th>异常班级</th>
                    <th>异常类型</th>
                    <th>异常描述</th>
                    <th>上报时间</th>
                    <th>上报人</th>
                </tr>
                </thead>
                <tbody>
                <td>${school.name}</td>
                <td>${grade.name}${classes.className}</td>
                <td>${opertions.exceptionType}</td>
                <td>${opertions.exceptionDescription}</td>
                <td>${opertions.startDate}</td>
                <td>${submitUserName}</td>
                </tbody>
            </table>

            <%--这是运维流程图开始--%>
            <ul class="process_warp">
                <c:forEach items="${activitiHis}" var="his" varStatus="status">
                    <li class="process01">
                        <div>
                            <span>${his.activityName}</span>
                            <c:if test="${status.index==0}"><span>处理人:${submitUserName==null?'--':submitUserName}</span></c:if>
                            <c:if test="${status.index!=0}"><span>处理人:${his.operateUserName==null?'--':his.operateUserName}</span></c:if>
                            <span>处理意见:${his.remark==null?'无':his.remark}</span>
                        </div>
                        <p></p>
                        <span>${his.endTime}</span>
                    </li>
                </c:forEach>
            </ul>
            <%--这是运维流程图结束--%>

            <%--这是审批意见开始--%>
            <c:if test="${types eq 'operate'}">
                <c:if test="${isMyTask !=null}">
                    <p class="opinion_title">审批意见：</p>
                    <c:if test="${opertions.birthType eq 'auto'}">
                        <c:if test="${currentRole.roleName eq 'SCHOOLADMIN'}">
                            <c:if test="${isQueren !='学校管理员确认'}">
                                <div class="opinion_choice">
                                    <span>处理方式：</span>
                                    <select class="opinion_select" id="pendingType">
                                        <option value="end">结束流程</option>
                                        <option value="no">转公司维修</option>
                                    </select>
                                </div>
                                <div class="opinion_choice" style="display: none;" id="chooseCompany">
                                    <span>选择处理单位：</span>
                                    <select class="opinion_select" id="chooseSchool">
                                        <option value="">请选择</option>
                                        <c:forEach items="${schools}" var="ss">
                                            <option value="${ss.schoolCode}">${ss.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${currentRole.roleName ne 'SCHOOLADMIN'}">
                        <c:if test="${isQueren =='待厂商处理'}">
                            <div class="opinion_choice">
                                <span>选择维修的设备：</span>
                                <select class="opinion_select" id="chooseEquipment">
                                    <option value="">请选择</option>
                                    <c:forEach items="${accessorys}" var="acs">
                                        <c:forEach items="${develops}" var="dee">
                                            <c:if test="${dee.id == acs.equipmentType}">
                                                <option value="${acs.id}">${dee.name}</option>
                                            </c:if>

                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </c:if>
                    <div class="opinion_warp">
                        <span>审批意见：</span>
                        <textarea class="opinion_textarea" id="approval"></textarea>
                    </div>
                    <div class="opinion_keep">
                        <div class="keep_img"></div>
                        <span id="doSubmitApp">确认提交</span>
                    </div>
                </c:if>
            </c:if>

            <%--这是审批意见结束--%>
        </div>
        <!--这是监测数据头部结束-->


    </div>
</div>
<input type="hidden" value="${opertions.birthType}" id="birthType"/>
<input type="hidden" value="${taskId}" id="taskId"/>
<input type="hidden" value="${opertions.processId}" id="processId"/>
<!--这是主要内容结束-->

<!--这是版权开始-->
<footer>
    <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->

</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
<script src="<c:url value="/resources/js/operationsManagement/process.js"/> "></script>
<!--这是分页按钮js部分结束-->
</html>

