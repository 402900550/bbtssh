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
        <td colspan="14">没查询到数据!</td>
    </tr>
</c:if>
<c:forEach items="${page.data}" var="appliacat">
    <tr equipmentNo="${appliacat[18]}" equipmentType="${appliacat[19]}" schoolCode="${appliacat[11]}">
        <td>${appliacat[2]==null?"未绑定教室":appliacat[2]}${appliacat[3]}---${appliacat[20]==1?"教室":"办公室"}</td>
        <td>${appliacat[18]==null?"未找到设备":appliacat[18]}</td>
        <td>${appliacat[22]}</td>
        <td style="cursor: pointer" name="lookTimeTable" gradeId="${appliacat[21]}" classId="${appliacat[13]}" schoolCode="${appliacat[11]}">${appliacat[0]}${appliacat[1]}</td>
        <td>${appliacat[4]==null?"无":appliacat[4]}</td>
        <td>${appliacat[5]==null?"无":appliacat[5]}</td>
        <!-- 控制器开始 -->
        <c:if test="${appliacat[6] eq 0}">
            <td class="off">
                关闭
            </td>
        </c:if>
        <c:if test="${appliacat[6] eq 1}">
            <td class="on">
                开启
            </td>
        </c:if>
        <c:if test="${appliacat[6] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 控制器结束 -->
        <!-- 电脑开始 -->
        <c:if test="${appliacat[7] eq 0}">
            <td class="off">
                关闭
            </td>
        </c:if>
        <c:if test="${appliacat[7] eq 1}">
            <td class="on">
                开启
            </td>
        </c:if>
        <c:if test="${appliacat[7] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 电脑结束 -->
        <!-- 投影开始 -->
        <c:if test="${appliacat[8] eq 0}">
            <td class="off">
                关闭
            </td>
        </c:if>
        <c:if test="${appliacat[8] eq 1}">
            <td class="on">
                开启
            </td>
        </c:if>
        <c:if test="${appliacat[8] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 结束开始 -->
        <!-- 展台开始 -->
        <c:if test="${appliacat[9] eq 1}">
            <td class="off">
                关闭
            </td>
        </c:if>
        <c:if test="${appliacat[9] eq 2}">
            <td class="on">
                开启
            </td>
        </c:if>
        <c:if test="${appliacat[9] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 展台结束 -->
        <!-- 电灯开始 -->
        <c:if test="${appliacat[14] eq 0}">
            <td class="off">
                <div class="control_off" controlType="lights" order="1"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[14] eq 1}">
            <td class="on">
                <div class="control_on" controlType="lights" order="0"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[14] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 电灯结束 -->
        <!-- 插座开始 -->
        <c:if test="${appliacat[15] eq 0}">
            <td class="off">
                <div class="control_off" controlType="sockets" order="1"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[15] eq 1}">
            <td class="on">
                <div class="control_on" controlType="sockets" order="0"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[15] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 插座结束 -->
        <!-- 空调开始 -->
        <c:if test="${appliacat[16] eq 0}">
            <td class="off">
                <div class="control_off" controlType="air_conditioning" order="1"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[16] eq 1}">
            <td class="on">
                <div class="control_on" controlType="air_conditioning" order="0"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[16] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 空调结束 -->
        <!-- 风扇开始 -->
        <c:if test="${appliacat[17] eq 0}">
            <td class="off">
                <div class="control_off" controlType="fan" order="1"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[17] eq 1}">
            <td class="on">
                <div class="control_on" controlType="fan" order="0"></div>
            </td>
        </c:if>
        <c:if test="${appliacat[17] == null}">
            <td>
                未检测
            </td>
        </c:if>
        <!-- 风扇结束 -->
        <td>
                ${appliacat[10]==null?0:appliacat[10]}W
        </td>

    </tr>
</c:forEach>

<input type="hidden" id="currentPage" value="${page.pageInfo.currentPage}"/>
<input type="hidden" id="totalPage" value="${page.pageInfo.totalPages}"/>