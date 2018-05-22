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

<thead>
<tr>
    <th>上报时间</th>
    <th>结束时间</th>
    <th>处理时间</th>
    <th>异常类型</th>
    <th>异常描述</th>
    <th>异常班级</th>
    <th>操作</th>
</tr>
</thead>

<tbody>
<c:if test="${empty theOperation.data}">
    <tr>
        <td colspan="7">未查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${theOperation.data}" var="operation">
    <tr>
        <td>${operation[3]}</td>
        <td>${operation[5]==null?'未结束':operation[5]}</td>
        <td>${operation[7]}小时</td>
        <td>${operation[1]}</td>
        <td>${operation[6]}</td>
        <td>${operation[4]}</td>
        <td>
            <div class="see" processId="${operation[2]}" name="lookDetailProcess" types="look">查看详情</div>
        </td>
    </tr>
</c:forEach>

</tbody>
<input type="hidden" id="currentPage" value="${theOperation.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${theOperation.pageInfo.totalPages}"/>

