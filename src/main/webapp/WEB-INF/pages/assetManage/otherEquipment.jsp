<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-11-09
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="addOther">
    <ul class="equipmentStyle">
        <li>
            <span>设备类型:</span>
            <select class="selectEquipment" name="otherEqType">
                <c:forEach items="${othertypes}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <span>品牌型号:</span>
            <select class="selectEquipment" name="otherModel">
                <c:forEach items="${othermodels}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <span>单价:</span>
            <input class="inputAdd" style="margin-left:32px;"/>
        </li>
    </ul>
    <p class="fontEquipment">设备参数:</p>
    <ul class="parameter">
        <li>
            <input name="Fruit" type="checkbox" value="0" class="checkbox"/>
            <span class="checkFont">CPU</span>
            <select class="selectParameter" name="cpu">
                <c:forEach items="${cpus}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <input name="Fruit" type="checkbox" value="0" class="checkbox"/>
            <span class="checkFont">内存</span>
            <select class="selectParameter" name="memory">
                <c:forEach items="${memorys}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <input name="Fruit" type="checkbox" value="0" class="checkbox"/>
            <span class="checkFont">硬盘</span>
            <select class="selectParameter" name="disk">
                <c:forEach items="${disks}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <input name="Fruit" type="checkbox" value="0" class="checkbox"/>
            <span class="checkFont">显卡</span>
            <select class="selectParameter" name="displayCard">
                <c:forEach items="${displays}" var="item">
                    <option value="${item.id}">${item.detail}</option>
                </c:forEach>
            </select>
        </li>
    </ul>
    <p class="otherss">
        <input name="Fruit" type="checkbox" value="0" class="checkbox" style="margin-left:100px;"/>
        <span class="checkFont">其他:</span>
        <textarea class="otherInput"></textarea>
    </p>
    <p class="imgClose"></p>
</div>