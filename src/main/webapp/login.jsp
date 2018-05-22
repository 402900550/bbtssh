<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物联网智慧教室应用管理系统</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>">
</head>

<body>
<div class="warp">
    <div class="swarp">
        <p class="title">物联网智慧教室应用管理系统</p>
        <ul>
            <li class="stitle">用户登录</li>
            <input  class="user" type="text" name="username" value="请输入用户名" />
            <input  class="password" type="password" name="password" value="请输入密码" />
            <button class="login"  type="button" id="loginBtn" disabled="disabled">登录</button>
            <button class="foundpassword"  type="button">忘记密码</button>
        </ul>
    </div>
</div>
</body>

<script>
    var CONTEXT_PATH='<%=request.getContextPath()%>';
</script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.3.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/login/login.js"/>" ></script>
</html>






















