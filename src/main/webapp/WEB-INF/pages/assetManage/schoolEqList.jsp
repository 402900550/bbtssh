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
        <td colspan="9">未查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="item">
    <tr>
        <td>${item[0]}</td>
        <td>${item[1]}</td>
        <td>${item[3]}</td>
        <td>${item[4]}</td>
        <td>${item[5]}</td>
        <td>${item[6]}</td>
        <td>${item[11]*100}%</td>
        <td>${item[7]+item[8]}</td>
        <td>
            <fmt:formatDate value="${item[10]}" var="userBirthday1" pattern="yyyy"/>
            <fmt:formatDate value="${item[9]}" var="userBirthday2" pattern="yyyy"/>
                ${userBirthday1}---${userBirthday2}
        </td>
        <td>
            <div class="see" style="text-indent: 10px;background-position: 36px 0px" name="lookSchoolDetail" schoolCode="${item[2]}">查看详情</div>
        </td>
    </tr>
</c:forEach>
<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>