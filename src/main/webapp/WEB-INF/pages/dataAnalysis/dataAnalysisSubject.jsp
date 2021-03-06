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
<c:if test="${empty theSubject.data}">
    <li>
        <div>没有数据!</div>
    </li>
</c:if>
<c:forEach items="${theSubject.data}" var="subjectList" varStatus="status">
    <li>
        <div class="serial_number" >${status.index+1}</div>
        <div>${subjectList[0]}</div>
        <div>${subjectList[1]}</div>
    </li>
</c:forEach>
<input type="hidden" id="currentPage" value="${theSubject.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${theSubject.pageInfo.totalPages}"/>
