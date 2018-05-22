<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-24
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:if test="${empty page.data}">
    <tr>
        <td>未查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="classEqiument">
    <tr>
        <td>${classEqiument[0]}</td>
        <td>${classEqiument[1]}</td>
        <td>${classEqiument[2]}</td>
        <td>
            <fmt:formatDate value="${classEqiument[3]}" var="classDate" pattern="yyyy-MM-dd"/>
                ${classDate}
        </td>
        <td>${classEqiument[4]}</td>
        <td>${classEqiument[5]}</td>
        <td>
            <c:if test="${classEqiument[6]==1}">运行中</c:if>
            <c:if test="${classEqiument[6]==2}">维修中</c:if>
            <c:if test="${classEqiument[6]==3}">已报废</c:if>
        </td>
        <td>
            <fmt:formatDate value="${classEqiument[7]}" var="classDate1" pattern="yyyy"/>
            <fmt:formatDate value="${classEqiument[3]}" var="classDate" pattern="yyyy"/>
                ${classDate+8-classDate1}年
        </td>
        <td>
            <div class="see" style="text-indent: 10px;background-position: 36px 0px;" name="lookAccessDetail" equimentId="${classEqiument[8]}">查看详情</div>
        </td>
    </tr>
</c:forEach>


<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>
