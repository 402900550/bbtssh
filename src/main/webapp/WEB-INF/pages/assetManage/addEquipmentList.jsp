<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-27
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${developsType}" var="leixing">
<table class="table equipments">
    <tr>
        <td><span class="text_span">设备类型名称:</span>
            <select class="select" name="equipmentType"  disabled >
                <option value="${leixing.id}">${leixing.name}</option>
            </select>
        </td>
        <td><span class="text_span">品牌:</span>
            <select class="select" name="brand">
                <option value=""  >请选择品牌</option>
                <c:forEach items="${pinpai}" var="p8">
                    <option value="${p8.id}">${p8.detail}</option>
                </c:forEach>
            </select>
        </td>
        <td><span class="text_span">规格型号:</span>
            <select class="select" name="sizeType">
                <option value=""  >请选择规格型号</option>
                <c:forEach items="${guigexinghao}" var="p5">
                    <option value="${p5.id}">${p5.detail}</option>
                </c:forEach>
            </select>
        </td>
        <td><span class="text_span">联系方式:</span>
            <input class="input number" name="repairPhone"   type="text"/>
        </td>
        <td><span class="text_span number" >单价:</span>
            <input class="input" name="price"   type="text"/>元
        </td>
    </tr>
</table>
</c:forEach>