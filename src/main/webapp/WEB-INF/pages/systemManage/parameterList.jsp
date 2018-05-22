<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-08
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:if test="${empty page.data}">
    <tr>
        <td colspan="4">未查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="item">
    <tr>
        <td>
            <div style="max-height: 40px;overflow: auto;">${item[1]}</div>
        </td>
        <td>${item[2]}</td>
        <td>
            <div style="max-height: 200px;overflow: auto;">${item[3]}</div>
        </td>
        <sec:authorize access="${currentRole.roleLevel gt TEACHER}">
            <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                <td>
                    <div class="button_edit03" name="modifyParameter"  parameterId="${item[4]}" ></div>
                </td>
            </sec:authorize>
        </sec:authorize>
    </tr>
</c:forEach>
<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>