<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-31
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${empty page.data}">
    <tr>
        <td colspan="9">未查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="item">
    <tr classId="${item[8]}" schoolCode="${item[7]}">
        <td>${item[1]}</td>
        <td>${item[0]}</td>
        <td>${item[2]}</td>
        <td>${item[3]}</td>
        <td>${item[4]==null?"未设置":item[4]}</td>
        <td>${item[5]==null?"未设置":item[5]}</td>
        <td roomId="${item[9]}">
                ${item[6]}
            <sec:authorize access="${currentRole.roleLevel eq SCHOOLADMIN}">
                <sec:authorize access="${item[7] eq currentUser.schoolCode}">
                    <c:if test="${item[9]!=null}">
                        <c:if test="${item[6]==null||item[6]==''}">
                            <div class="button_bangding" roomId="${item[9]}" name="bundingEquipment"></div>
                        </c:if>
                        <c:if test="${item[6]!=null&&item[6]!=''}">
                            <div class="button_edit" style="display: inline-block" name="modifyBundingEquipment" roomId="${item[9]}"></div>
                        </c:if>
                    </c:if>
                </sec:authorize>
            </sec:authorize>
            <c:if test="${item[9]==null}">
                请先确定该班级的教室
            </c:if>
        </td>
        <td>
            <sec:authorize access="${currentRole.roleLevel eq SCHOOLADMIN}">
                <sec:authorize access="${item[7] eq currentUser.schoolCode}">
                    <div class="button_edit03"  name="modifyClass" ></div>
                    <div class="button_delete02"  name="deleteClass" ></div>
                </sec:authorize>
            </sec:authorize>
        </td>
    </tr>
</c:forEach>
<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>