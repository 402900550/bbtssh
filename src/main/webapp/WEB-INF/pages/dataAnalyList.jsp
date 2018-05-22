<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-24
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul headerType="school"  <c:if test="${type ne 'school'}">style="display:none"</c:if> >
    <c:forEach items="${theSchool.data}" var="schoolList" varStatus="status">
        <li>
            <div class="serial_number">${status.index+1}</div>
            <div>${schoolList[0]}</div>
            <div>${schoolList[3]*100}%</div>
        </li>
    </c:forEach>
</ul>
<ul headerType="subject" <c:if test="${type ne 'subject'}">style="display:none"</c:if> >
    <c:forEach items="${theSubject.data}" var="subjectList" varStatus="status">
        <li>
            <div class="serial_number">${status.index+1}</div>
            <div>${subjectList[0]}</div>
            <div>${subjectList[1]}</div>
        </li>
    </c:forEach>
</ul>
<ul headerType="teacher" <c:if test="${type ne 'teacher'}">style="display:none"</c:if> >
    <c:forEach items="${theTeacher.data}" var="teacherList" varStatus="status">
        <li>
            <div class="serial_number">${status.index+1}</div>
            <div>${teacherList[0]}</div>
            <div>${teacherList[1]}</div>
            <div>${teacherList[4]*100}%</div>
        </li>
    </c:forEach>
</ul>

