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
        <td colspan="7">没查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="environment">
    <tr>
        <td style="width: 200px;">${environment[1]}</td>
        <td>${environment[0]}</td>
        <td>
            <div class="environments temperature">${environment[2]==null?"未获取到值":environment[2]}</div>
        </td>
        <td>
            <div class="environments humidity">${environment[3]==null?"未获取到值":environment[3]}</div>
        </td>
        <td>
            <div class="environments PM">${environment[4]==null?"未获取到值":environment[4]}</div>
        </td>
        <td>
            <div class="environments illumination">${environment[5]==null?"未获取到值":environment[5]}</div>
        </td>
        <td>
            <div class="environments noise">${environment[6]==null?"未获取到值":environment[6]}</div>
        </td>
    </tr>
</c:forEach>
<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>