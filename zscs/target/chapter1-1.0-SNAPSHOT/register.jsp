<%--
  Created by IntelliJ IDEA.
  User: zcx
  Date: 2022/11/23
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/jspdemo/registerServlet" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" >${register_msg}</span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img  id="checkCodeImg" src="/jspdemo/checkCodeServlet">
                    <a href="#" id="changeImg">看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>
<script>
    // <input name="checkCode" type="text" id="checkCode">
    //     <img  id="checkCodeImg" src="/jspdemo/checkCodeServlet">
    //         <a href="#" id="changeImg">看不清？</a>
    document.getElementById("changeImg").onclick = function (){

        document.getElementById("checkCodeImg").src="/jspdemo/checkCodeServlet?"+new Date().getMilliseconds();
    }
    document.getElementById("checkCodeImg").onclick = function (){

        document.getElementById("checkCodeImg").src="/jspdemo/checkCodeServlet?"+new Date().getMilliseconds();
    }
</script>
</body>
</html>
