<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-30
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${empty page.data}">
    <tr>
        <td colspan="8">该教学楼还没有添加教室!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="item">
    <tr  schoolCode="${item[2]}">
        <td>${item[0]}</td>
        <td>${item[1]}</td>
        <td>${item[3]}</td>
        <td>${item[5]}</td>

        <td>
            <c:if test="${item[6] eq 1}">教室</c:if>
            <c:if test="${item[6] eq 2}">办公室</c:if>
        </td>

        <td>${item[11]==null?"":item[11]}${item[10]==null?"未绑定班级":item[10]}</td>
        <td>
                ${item[8]}
            <sec:authorize access="${currentRole.roleLevel eq SCHOOLADMIN}">
                <sec:authorize access="${item[2] eq currentUser.schoolCode}">
                    <c:if test="${item[8]==null||item[8]==''}">
                        <div class="button_bangding" roomId="${item[4]}" name="bundingEquipment"></div>
                    </c:if>
                    <c:if test="${item[8]!=null&&item[8]!=''}">
                        <div class="button_edit" style="display: inline-block" name="modifyBundingEquipment" roomId="${item[4]}"></div>
                    </c:if>
                </sec:authorize>
            </sec:authorize>
        </td>
        <td >
            <sec:authorize access="${currentRole.roleLevel eq SCHOOLADMIN}">
                <sec:authorize access="${item[2] eq currentUser.schoolCode}">
                    <div class="button_edit02" roomId="${item[4]}" name="modifyClassroom" ></div>
                    <div class="button_delete"  roomId="${item[4]}" name="deleteClassRoom" ></div>
                </sec:authorize>
            </sec:authorize>
        </td>
    </tr>
</c:forEach>


<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>