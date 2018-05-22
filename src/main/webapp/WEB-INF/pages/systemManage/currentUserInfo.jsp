<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-26
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<li>
    <span>姓名：</span>
    <input type="hidden" name="userId" value="${currentNewUser.id}"/>
    <input class="user_input" name="realName" type="text" value="${currentNewUser.realName}" disabled=""/>
</li>
<li>
    <span>性别：</span>
    <select name="sex" class="user_select" disabled="">
        <option value="1" <c:if test="${currentNewUser.sex==1}">selected</c:if> >男</option>
        <option value="2" <c:if test="${currentNewUser.sex==2}">selected</c:if> >女</option>
    </select>
</li>
<li class="long_li">
    <span>出生年月：</span>
    <fmt:formatDate value="${currentNewUser.birthday }" var="userBirthday" pattern="yyyy-MM-dd"/>
    <input name="birthday" class="user_input" type="date" value="${userBirthday}"  disabled=""/>
</li>
<li>
    <span>学校：</span>
    <select class="user_select" name="schoolCode"  disabled="">
        <option value="${currentSchool.schoolCode}" >${currentSchool.name}</option>
    </select>
</li>
<li>
    <span>学科：</span>
    <select name="subjectId" class="user_select" disabled="">
        <c:forEach items="${subject}" var="sub">
            <option value="${sub.id}" <c:if test="${currentNewUser.subjectId==sub.id}">selected</c:if> >${sub.subjectName}</option>
        </c:forEach>
    </select>
</li>
<li>
    <span>学历：</span>
    <input name="educational" class="user_input" type="text" value="${currentNewUser.educational}"  disabled=""/>
</li>

<li>
    <span>专业：</span>
    <input name="professional" class="user_input" type="text" value="${currentNewUser.professional}"  disabled=""/>
</li>
<li>
    <span>IC卡：</span>
    <input name="icNo" class="user_input" type="text" value="${currentNewUser.icardNo}"  disabled=""/>
</li>
<li id="roleId">
    <span>角色：</span>
    <select name="roleUserId" class="user_select" disabled="">
        <c:forEach items="${userRoleId}" var="sub">
            <option value="${sub.id}" <c:if test="${roleId.roleId==sub.id}">selected</c:if> >${sub.description}</option>
        </c:forEach>
    </select>
</li>















