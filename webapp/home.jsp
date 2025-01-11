<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String username = (String) session.getAttribute("username"); // 从请求中获取用户名
%>
<html>
<head>
    <title>佩阿回氏</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

</head>
<body>
    <div class="header">
        <div class="username">
            <% if (username != null) { %>
                欢迎, <a href="myPageServlet"><%=username%></a> <!-- 显示用户名 -->
                 <a href="logoutServlet">退出登录</a>

            <% } else { %>
                你好， <a href="log&sign.jsp">请登录</a> <!-- 登录超链接 -->
            <% } %>
        </div>
        <div class="logo">
            <img src="${pageContext.request.contextPath}/logo/logo.png" alt="peiahuishi">
        </div>
        <!-- 将客服和购物车链接放在最右边 -->
        <div class="utility">
            <a href="cartServlet">购物车</a>
            <a href="customerService.jsp">客服</a>
        </div>
    </div>

    <div class="container">
        <!-- 搜索框位于页面正中间 -->
        <div class="search-box">
            <form action="searchServlet" method="get">
                <input type="text" name="query"  id="searchInput" placeholder="搜索商品...">
                <input type="submit" value="搜索">
                <div class="suggestions" id="suggestions"></div>

            </form>
        </div>
       <%--下拉列表--%>

        <!-- 商品分类 -->
        <div class="categories">
            <a href="CategoryServlet?category_id=1" class="category">日常书写</a>
            <a href="CategoryServlet?category_id=2" class="category">书法</a>
            <a href="CategoryServlet?category_id=3" class="category">绘画</a>
            <a href="CategoryServlet?category_id=4" class="category">其他</a>
        </div>
    </div>

    <div class="slider">
        <div class="slide-viewer">
            <div class="slide-group">
                <div class="slide slide-1">
                    <img src="${pageContext.request.contextPath}/images/slide.webp" alt="slide1">
                </div>
                <div class="slide slide-2">
                    <img src="${pageContext.request.contextPath}/images/slide.jpg" alt="slide2">
                </div>
                <div class="slide slide-3">
                    <img src="${pageContext.request.contextPath}/images/slide2.webp" alt="slide3">
                </div>
                <div class="slide slide-4">
                    <img src="${pageContext.request.contextPath}/images/slide5.png" alt="slide4">
                </div>
            </div>
        </div>
        <div class="slide-buttons"></div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/home.js"></script>
<%--用户名查重js--%>
<script src=js/register.js></script>
</html>
<%
    request.setAttribute("username" ,username);
%>