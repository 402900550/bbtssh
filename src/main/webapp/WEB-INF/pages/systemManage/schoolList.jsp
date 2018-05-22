<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-24
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:forEach items="${page.data}" var="item">
    <div class="single_school">
        <div class="school_detail">
            <div class="school_title" name="schoolName" schoolCode="${item[2]}" >${item[0]}</div>
            <div class="school_other_info">学校类型:${item[1]}</div>
            <div class="school_other_info">学校代码: <span>${item[2]}</span></div>
            <div class="school_other_info">学校电话：<span>${item[3]}</span></div>
            <div class="school_other_info">所属区县：<span>${item[7]}</span></div>
            <div class="school_other_info">学校地址：<span>${item[4]}</span></div>
        </div>
        <div class="introduction">
            <div>
                简介：<span>${item[5]}</span>
            </div>
        </div>
        <sec:authorize access="${currentRole.roleLevel gt SCHOOLADMIN}">
            <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                <div class="operate">
                    <div class="button_edit"  name="modifySchool" schoolId="${item[6]}" ></div>
                    <div class="button_delete"  name="deleteSchool" schoolId="${item[6]}"></div>
                </div>
            </sec:authorize>
        </sec:authorize>
    </div>
</c:forEach>
<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>

