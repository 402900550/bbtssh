<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-10-26
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户管理</title>
    <!--这是没引用完导航，到时候替换便是-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user.css"/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/paging.css"/> "/>
</head>
<body>
<!--这是没引用完导航，到时候替换便是-->
<jsp:include page="../top.jsp"/>
<!--这是没引用完导航，到时候替换便是-->

<div class="main_warp">
    <!--这是页面定位开始-->
    <div class="location">
        <span>用户管理</span> > <span>用户管理</span>
    </div>
    <!--这是页面定位结束-->

    <!--这是用户页面开始-->
    <div class="user_warp">
        <div class="user_main">
            <div class="head_portrait"></div>
            <div class="user_name">
                <span>${currentUser.realName}</span><br/>
                <span>${currentRole.description}</span>
            </div>
            <div class="use_change">编辑</div>
            <div class="use_keep">保存</div>
            <div class="use_cancel">取消</div>
            <p class="user_title">基本资料:</p>
            <ul class="basic">
                <li>
                    <span>姓名：</span>
                    <input type="text" class="user_input" value="冯宝宝" disabled=""/>
                </li>
                <li>
                    <span>性别：</span>
                    <select class="user_select" disabled="">
                        <option>女</option>
                        <option>男</option>
                    </select>
                </li>
                <li class="long_li">
                    <span>出生年月：</span>
                    <input type="date" class="user_input" disabled=""/>
                </li>
                <li>
                    <span>学校：</span>
                    <input type="text" class="user_input" value="名字很长的中学" disabled=""/>
                </li>
                <li>
                    <span>学科：</span>
                    <select class="user_select" disabled="">
                        <option>语文</option>
                        <option>数学</option>
                        <option>英语</option>
                    </select>
                </li>
                <li>
                    <span>学历：</span>
                    <input type="text" class="user_input" value="本科" disabled=""/>
                </li>

                <li>
                    <span>专业：</span>
                    <input type="text" class="user_input" value="搬砖" disabled=""/>
                </li>
                <li>
                    <span>IC卡：</span>
                    <input type="text" class="user_input" value="" disabled=""/>
                </li>
            </ul>
        </div>
    </div>
    <!--这是用户页面结束-->

    <!--这是表格开始-->
    <div class="user_form">
        <div class="user_list">
            <div class="choice">
                <div class="select_backgroud">
                    <div class="select_backgroud02">
                        <input id="distinctName" class="input_down" type="text" placeholder="请输入区县" disabled
                               value="沙坪坝区"/>
                    </div>
                </div>
                <div class="select_backgroud">
                    <div class="select_backgroud02">
                        <input id="schoolName" class="input_down" type="text" placeholder="请输入学校" list="schoolList"/>
                        <datalist id="schoolList"></datalist>
                    </div>
                </div>
                <div class="select_backgroud">
                    <div class="select_backgroud02">
                        <input id="userName" class="input_down" type="text" placeholder="请输入姓名" />
                    </div>
                </div>
                <div class="search" id="doSearch">
                    <img src="<c:url value="/resources/img/search.png"/> "/>
                </div>
                <sec:authorize access="${currentRole.roleLevel gt TEACHER}">
                    <sec:authorize access="${currentRole.roleLevel lt COMPANY}">
                            <div class="button" id="addUser">添加用户</div>
                    </sec:authorize>
                </sec:authorize>

            </div>
            <table class="user_table">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>学校名称</th>
                    <th>姓名</th>
                    <th>IC卡号</th>
                    <th>性别</th>
                    <th>出生年月</th>
                    <th>学历</th>
                    <th>专业</th>
                    <th>学科</th>
                    <th>角色</th>
                    <th style="width: 100px">操作</th>
                </tr>
                </thead>
                <tbody id="userLists"></tbody>
            </table>
            <div id="pagination2" class="page"></div>
        </div>
    </div>
    <!--这是表格结束-->
</div>
<!--这是主要内容结束-->

<!--这是版权开始-->
<footer>
    <p>重庆幻方科技有限公司 版权所有 @2016 - 2020 &nbsp;&nbsp;重庆幻方科技技术支持 电话 ： 023-65350991</p>
</footer>
<!--这是版权结束-->
</body>
<script src="<c:url value="/resources/js/jquery-1.8.3.js"/> "></script>
<script src="<c:url value="/resources/js/common/dialog.js"/> "></script>
<script src="<c:url value="/resources/js/paging.js"/> "></script>
<script src="<c:url value="/resources/js/common/loadCondition.js"/> "></script>
<script type="text/javascript" src="<c:url value="/resources/js/systemanage/userManage.js"/> "></script>
</html>

