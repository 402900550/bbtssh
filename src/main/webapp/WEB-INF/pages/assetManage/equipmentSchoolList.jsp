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


<c:forEach items="${page.data}" var="classEqiument">
    <tr>
        <td>${classEqiument[0]}${classEqiument[1]}</td>
        <td>${classEqiument[2]}</td>
        <td>
            <c:if test="${classEqiument[3]==null}">否</c:if>
            <c:if test="${classEqiument[3]!=null}">是</c:if>
        </td>
        <td>${classEqiument[4]}</td>
        <td>${classEqiument[5]}</td>
        <td>${classEqiument[6]+classEqiument[7]}</td>
        <td>
            <fmt:formatDate value="${classEqiument[8]}" var="userBirthday1" pattern="yyyy"/>
            <fmt:formatDate value="${classEqiument[9]}" var="userBirthday2" pattern="yyyy"/>
                ${userBirthday1}---${userBirthday2}
        </td>
        <td>
            <c:if test="${classEqiument[4]!=0}">
                <div class="see" style="text-indent: 10px;background-position: 36px 0px;" name="lookSchoolDetail"
                     classId="${classEqiument[12]}">查看详情
                </div>
            </c:if>
        </td>
    </tr>
</c:forEach>

<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>
