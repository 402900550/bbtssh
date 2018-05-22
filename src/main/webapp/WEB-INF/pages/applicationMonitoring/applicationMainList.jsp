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
<%--<thead>--%>
<%--<tr class="tr_header">--%>
    <%--<td>区县</td>--%>
    <%--<td>学校名称</td>--%>
    <%--<td>班级总数</td>--%>
    <%--<td>检测数</td>--%>
    <%--<td>正在使用数</td>--%>
    <%--<td>使用率</td>--%>
    <%--<td>操作</td>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:if test="${empty page.data}">--%>
    <%--<tr>--%>
        <%--<td colspan="7">没查询到数据!</td>--%>
    <%--</tr>--%>
<%--</c:if>--%>
<%--<c:forEach items="${page.data}" var="appliacat">--%>
    <%--<tr class="tr_body">--%>
        <%--<td>${appliacat[0]}</td>--%>
        <%--<td>${appliacat[1]}</td>--%>
        <%--<td>${appliacat[2]}</td>--%>
        <%--<td>${appliacat[3]}</td>--%>
        <%--<td>${appliacat[4]}</td>--%>
        <%--<td>${appliacat[5]*100}%</td>--%>
        <%--<td style="color: red;cursor: pointer" >--%>
            <%--<span schoolCode="${appliacat[6]}" name="lookDetail">查看详情</span>--%>
            <%--|--%>
            <%--<span schoolCode="${appliacat[6]}" name="lookEnvironment">环境检测</span>--%>
        <%--</td>--%>
    <%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<c:forEach items="${page.data}" var="appliacat">
<ul>
    <li class="app_county">${appliacat[0]}</li>
    <li>${appliacat[1]}</li>
    <li>${appliacat[2]}</li>
    <li>${appliacat[3]}</li>
    <li>${appliacat[4]}</li>
    <li>${appliacat[5]*100}%</li>
    <li class="control">
        <div class="see" schoolCode="${appliacat[6]}" name="lookDetail" >查看详情</div>
        <p>I</p>
        <div class="monitor" schoolCode="${appliacat[6]}" name="lookEnvironment" >环境监测</div>
    </li>
</ul>
</c:forEach>

<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>