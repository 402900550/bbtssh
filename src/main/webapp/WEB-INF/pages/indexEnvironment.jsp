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
<ul>
    <li class="scienceTitle">
        <c:if test="${qxmId == 123623 }">
            <span>主城区</span>
        </c:if>
        <c:if test="${qxmId == 500106123623 }">
            <span>西部地区</span>
        </c:if>
        <c:if test="${qxmId == 500106111226 }">
            <span>歌乐山片区</span>
        </c:if>
    </li>
    <li>
        <span class="scienceSmall">温度</span>
        <p class="temperature_img"></p>
        <div class="temperature" style="margin-left: 20px;">
            <span>${environment[1]==null?"数据丢失":environment[1]}</span> ℃
        </div>
        <p class="fulls"></p>
    </li>
    <li>
        <span class="scienceSmall">湿度</span>
        <p class="humidity_img"></p>
        <div class="humidity">
            <span>${environment[2]==null?"数据丢失":environment[2]}</span> %RH
        </div>
        <p class="fulls"></p>
    </li>
    <li>
        <span class="scienceSmall">PM2.5</span>
        <p class="PM_img"></p>
        <div class="PM">
            <span>${environment[3]==null?"数据丢失":environment[3]}</span> μm
        </div>
        <p class="fulls"></p>
    </li>
    <li>
        <span class="scienceSmall">照度</span>
        <p class="illumination_img"></p>
        <div class="illumination" style="margin-left:13px;">
            <span>${environment[4]==null?"数据丢失":environment[4]}</span> Lux
        </div>
        <p class="fulls"></p>
    </li>
    <li>
        <span class="scienceSmall">噪音</span>
        <p class="noise_img"></p>
        <div class="noise">
            <span>${environment[5]==null?"数据丢失":environment[5]}</span> dB
        </div>
        <p class="fulls"></p>
    </li>
</ul>
