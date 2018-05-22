<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-31
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty page.data}">
    <tr>
        <td colspan="9">没查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="appliacat">
    <tr>
        <td>${appliacat[0]}</td>
        <td>${appliacat[1]}</td>
        <td>${appliacat[2]}</td>
        <td>${appliacat[3]}</td>
        <td>${appliacat[4]}</td>
        <td>${appliacat[5]}</td>
        <td>${appliacat[6]}</td>
        <td>${appliacat[7]}</td>
        <td>${appliacat[8]}</td>
    </tr>
</c:forEach>

<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>