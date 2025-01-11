<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Security-Policy" content="default-src *; style-src 'self' http://* 'unsafe-inline'; script-src 'self' http://* 'unsafe-inline' 'unsafe-eval'" />

    <title>登录页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login&out.css">
</head>
<body>
<div class="content">
    <div class="form sign-in">
        <h2>欢迎回来</h2>
        <form action="loginServlet" method="post">
            <label>
                <span>邮箱</span>
                <input type="email" name="email" required>
            </label>
            <label>
                <span>密码</span>
                <input type="password" name="password" required>
            </label>
            <p class="forgot-pass"><a href="" decode_id="0">忘记密码？</a></p>
            <button type="submit" class="submit">登 录</button>
        </form>
<%--        <button type="button" class="fb-btn">使用 <span>其他</span> 帐号登录</button>--%>
    </div>

    <div class="sub-cont">
        <div class="img">
            <div class="img__text m--up">
                <h2>还未注册？</h2>
                <p>来自星尘<br>买好笔！</p>
            </div>
            <div class="img__text m--in">
                <h2>已有帐号？</h2>
                <p>立即登陆！<br>看好笔</p>
            </div>
            <div class="img__btn">
                <span class="m--up">注 册</span>
                <span class="m--in">登 录</span>
            </div>

        </div>

        <div class="form sign-up">
            <h2>立即注册</h2>
                <label>
                    <span>邮箱</span>
                    <input type="email" name="email" id="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>
                </label>
                <input type="hidden" name="action" value="register">
            <!-- 表单：注册 -->
            <form action="registerServlet" method="post">
                <label>
                    <span>验证码</span>
                    <label id="verify">
                        <input type="text" id="verificationCode" name="verificationCode" required>
                        <label class="submit">
                            <a href="javascript:void(0)" id="getVerificationCode" class="submit" >获取验证码</a>
                        </label>
                    </label>
                </label>
                <label>
                    <span>用户名</span>
                    <input type="text" id="username"  name= "username" required>
                </label>
                <label>
                    <span>密码</span>
                    <input type="password"  id="password" name="password" required>
                </label>
<%--                用户名密码检测--%>
                <div id="usernameResult"></div>
                <input type="hidden"  name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
                <button type="submit" class="submit" value="注册">注 册</button>
            </form>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/script.js"></script>
<script src="js/verify.js"></script>
<script src="js/register.js"></script>
</html>
