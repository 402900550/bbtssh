<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-09
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wiring">
    <ul class="wiringInput">
        <li>
            <span class="checkFont">名称:</span>
            <select class="selectParameter" name="lineName">
                <c:forEach items="${lineNames}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <span class="checkFont">品牌：</span>
            <select class="selectParameter" name="lineModel">
                <c:forEach items="${lineBrands}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <span class="checkFont">规格：</span>
            <select class="selectParameter" name="lineBrand">
                <c:forEach items="${lineModels}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <span class="checkFont">单价:</span>
            <input class="inputWiring" name="linePrice"/>
        </li>
        <li>
            <span class="checkFont">数量:</span>
            <input class="inputWiring" name="lineCount"/>
        </li>
    </ul>
    <p class="parameter_wiring">
        <span class="checkFont">参数:</span>
        <textarea class="wiring_parameter"></textarea>
    </p>
    <p class="img_close"></p>
</div>
