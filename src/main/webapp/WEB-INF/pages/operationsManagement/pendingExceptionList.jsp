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
    <th>上报单位</th>
    <th>流程状态</th>
    <th>操作</th>
</tr>
</thead>

<tbody>
<c:if test="${empty theOperation}">
    <tr>
        <td colspan="7">未查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${theOperation}" var="operation">
    <tr>
        <td>${operation.operateDate}</td>
        <td>${operation.operateSchool}</td>
        <td>${operation.processStatus}</td>
        <td>
            <div class="see see_s"  taskId="${operation.taskId}" processId="${operation.processId}" name="lookDetailProcess" types="operate">查看详情</div>
        </td>
    </tr>
</c:forEach>

</tbody>


