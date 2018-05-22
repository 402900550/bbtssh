<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-24
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:forEach items="${page.data}" varStatus="status" var="item">
    <li schoolCode="${item.schoolCode}" >
        <span class="school_name"> ${school.name}</span><br/>
        <span class="county_name">(沙坪坝)</span><br/><br/>
        <span class="building_name">名称 (${item.buildingName})</span><br/>
        <span name="lookClassRoom" buildingId="${item.id}" >教室管理 (点击查看)</span><br/>
        <sec:authorize access="${currentRole.roleLevel eq SCHOOLADMIN}">
            <sec:authorize access="${currentUser.schoolCode eq school.schoolCode}">
                <span class="operation_warp">
                <span class="modify_building" buildingId="${item.id}" >修改</span>
                <span>I</span>
                <span class="delete_building" buildingId="${item.id}" >删除</span>
                </span>
            </sec:authorize>
        </sec:authorize>
    </li>
</c:forEach>
<sec:authorize access="${currentRole.roleLevel eq SCHOOLADMIN}">
    <sec:authorize access="${currentUser.schoolCode eq school.schoolCode}">
        <li class="add_warp">
            <div class="add_building"></div>
            <div class="building_add">(添加教学楼)</div>
        </li>
    </sec:authorize>
</sec:authorize>


<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>

