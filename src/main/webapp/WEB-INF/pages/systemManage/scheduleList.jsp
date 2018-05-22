<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="grade_time">请选择年级</div>
<p>上午课时：</p>
<ul class="morning_schedule">
    <c:forEach items="${morning}" var="mo">
        <li scheduleId="${mo.id}" timeType="morning" schoolCode="${schoolCode}">
            <span name="numberCourse">${mo.numberCourse}</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" value="${mo.start}" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" value="${mo.end}" class="schedule_input" disabled/>
            </div>
        </li>
    </c:forEach>
    <c:if test="${empty morning}">
        <li scheduleId="" timeType="morning" schoolCode="${schoolCode}">
            <span name="numberCourse">第1节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="morning" schoolCode="${schoolCode}">
            <span name="numberCourse">第2节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="morning" schoolCode="${schoolCode}">
            <span name="numberCourse">第3节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="morning" schoolCode="${schoolCode}">
            <span name="numberCourse">第4节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
    </c:if>
</ul>
<p>下午课时：</p>
<ul class="afternoon_schedule">
    <c:forEach items="${afternoon}" var="af">
        <li scheduleId="${af.id}" timeType="afternoon" schoolCode="${schoolCode}">
            <span name="numberCourse">${af.numberCourse}</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" value="${af.start}" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" value="${af.end}" class="schedule_input" disabled/>
            </div>
        </li>
    </c:forEach>
    <c:if test="${empty afternoon}">
        <li scheduleId="" timeType="afternoon" schoolCode="${schoolCode}">
            <span name="numberCourse">第5节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="afternoon" schoolCode="${schoolCode}">
            <span name="numberCourse">第6节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="afternoon" schoolCode="${schoolCode}">
            <span name="numberCourse">第7节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="afternoon" schoolCode="${schoolCode}">
            <span name="numberCourse">第8节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
    </c:if>
</ul>
<p>晚自习：</p>
<ul class="night_schedule">
    <c:if test="${empty night}">
        <li scheduleId="" timeType="night" schoolCode="${schoolCode}">
            <span name="numberCourse">第9节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" value="${af.start}" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" value="${af.end}" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="night" schoolCode="${schoolCode}">
            <span name="numberCourse">第10节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
        <li scheduleId="" timeType="night" schoolCode="${schoolCode}">
            <span name="numberCourse">第11节</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" class="schedule_input" disabled/>
            </div>
        </li>
    </c:if>


    <c:forEach items="${night}" var="nt">
        <li scheduleId="${nt.id}" timeType="night" schoolCode="${schoolCode}">
            <span name="numberCourse">${nt.numberCourse}</span>
            <div>
                <span>开始：</span>
                <input name="start" type="time" value="${nt.start}" class="schedule_input" disabled/>
                <span class="endTime">结束：</span>
                <input name="end" type="time" value="${nt.end}" class="schedule_input" disabled/>
            </div>
        </li>
    </c:forEach>
</ul>
