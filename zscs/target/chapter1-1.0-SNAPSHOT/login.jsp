<%--
  Created by IntelliJ IDEA.
  User: zcx
  Date: 2022/11/23
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/loginServlet" id="form" method="post">
        <h1 id="loginMsg">LOGIN IN</h1>
        <div id="errorMsg">${login_msg}${register_msg}</div>
        <p>账号:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>
        <p>密码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
        <p>自动登录:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="登录">
            <input type="reset" class="button" value="重置">&nbsp;&nbsp;&nbsp;
<%--            <a href="register.jsp">没有账号？</a>--%>
        </div>
    </form>
</div>

</body>
</html>