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

<c:if test="${empty user.data}">
    <tr>
        <td colspan="11">没查询到用户!</td>
    </tr>
</c:if>
<c:forEach items="${user.data}" var="userList" varStatus="status">
    <tr schoolCode="${userList[11]}">
        <td id="b">${status.index+1}</td>
        <td>${userList[0]}</td>
        <td>${userList[1]}</td>
        <td>${userList[10]}</td>
        <c:if test="${userList[2]==1}">
            <td>男</td>
        </c:if>
        <c:if test="${userList[2]==2}">
            <td>女</td>
        </c:if>
        <td><fmt:formatDate value="${userList[3]}" var="userBirthday" pattern="yyyy-MM-dd"/>
                ${userBirthday}
        </td>
        <td>${userList[4]}</td>
        <td>${userList[5]}</td>
        <td>${userList[6]}</td>
        <td>${userList[7]}</td>
        <td>
            <c:if test="${currentRole.roleLevel eq HUANFANGADMIN}">
                <%--<sec:authorize access="${currentRole.roleLevel ge ADMIN}">--%>
                <sec:authorize access="${currentUser.id ne userList[8]}">
                    <sec:authorize access="${userList[9] lt HUANFANGADMIN}">
                        <div class="button_edit02" name="modifyUser" userId="${userList[8]}" ></div>
                        <div class="button_delete"  userId="${userList[8]}" name="deleteUser" ></div>
                    </sec:authorize>
                </sec:authorize>
                <%--</sec:authorize>--%>
            </c:if>
            <c:if test="${currentRole.roleLevel eq DISTNCTADMIN}">
                <sec:authorize access="${currentUser.id ne userList[8]}">
                    <sec:authorize access="${userList[9] lt DISTNCTADMIN}">
                        <div class="button_edit02" name="modifyUser" userId="${userList[8]}" ></div>
                        <div class="button_delete"  userId="${userList[8]}" name="deleteUser" ></div>
                    </sec:authorize>
                </sec:authorize>
            </c:if>
            <c:if test="${currentRole.roleLevel eq SCHOOLADMIN}">
                <sec:authorize access="${currentUser.id ne userList[8]}">
                    <sec:authorize access="${currentUser.schoolCode eq userList[11]}">
                        <sec:authorize access="${userList[9] eq TEACHER}">
                            <div class="button_edit02" name="modifyUser" userId="${userList[8]}" ></div>
                            <div class="button_delete"  userId="${userList[8]}" name="deleteUser" ></div>
                        </sec:authorize>
                    </sec:authorize>
                </sec:authorize>

            </c:if>
        </td>
    </tr>
</c:forEach>
<input type="hidden" id="currentPage" value="${user.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${user.pageInfo.totalPages}"/>
