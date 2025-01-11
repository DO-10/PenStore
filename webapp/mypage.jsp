
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="objects.Product" %>
<%@ page import="objects.Order" %>
<%@ page import="objects.User" %>

<%
    User user = (User) request.getAttribute("user");
    String username = (String) session.getAttribute("username");
%>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myaccount.css">
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

    <div class="av">
        <h2>您的头像:</h2>
        <img src="${user.getPhoto()}" alt="用户头像" width="100" height="100">
    </div>
    <div class="container">
    <!-- 欢迎信息和修改用户名、头像 -->
        <ul class="tab-list">
            <li class="tab active"><a class="tab-control" href="#tab-1">历史订单</a></li>
            <li class="tab"><a class="tab-control" href="#tab-2">地址管理</a></li>
            <li class="tab"><a class="tab-control" href="#tab-3">账户管理</a></li>
        </ul>
        <div class="tab-panel active" id="tab-1">
            <h2>您的订单:</h2>
            <%
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                for (Order order : orders) {
            %>
            <h3>订单 ID: <%= order.getId() %></h3>
            <h4>下单时间：<%= order. getCreatedAt()%></h4>
            <ul>
                <%
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    for (Product product : products) {
                %>
                <li><%= product.getName() %> - 数量: <%= product.getQuantity() %> - 价格: <%= product.getPrice() %></li>
                <%
                    }
                %>
            </ul>
            <%
                }
            %>
        </div>
        <div class="tab-panel" id="tab-2">
            <h2>地址管理</h2>
            <form id="addressForm">
                <input type="hidden" id="userId" value="${user.getId()}">
                <input type="text" id="newAddress" placeholder="请输入新地址" required>
                <button type="button" id="submitBtn">提交</button>
            </form>
            <p id="responseMessage" style="color: green;"></p>
        </div>
        <div class="tab-panel" id="tab-3">
            <h2>账户管理</h2>
            <!-- 修改用户名 -->
            <form action="changeifoservlet" method="post">
                <input type="hidden" name="username" value="${user.getUsername()}">
                <input type="text" name="newname" placeholder="请输入新名字" required>
                <button type="submit">提交</button>
            </form>

            <!-- 修改头像 -->
            <form action="changeAvatarServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="userId" value="${user.getId()}">
                <input type="file" name="avatar" accept="image/*" required>
                <button type="submit">上传新头像</button>
            </form>
        </div>
    </div>








        <div class="user-info">


        <!-- 修改用户名 -->
<%--        <form action="changeifoservlet" method="post">--%>
<%--            <input type="hidden" name="username" value="${user.getUsername()}">--%>
<%--            <input type="text" name="newname" placeholder="请输入新名字" required>--%>
<%--            <button type="submit">提交</button>--%>
<%--        </form>--%>

<%--        <!-- 修改头像 -->--%>
<%--        <form action="changeAvatarServlet" method="post" enctype="multipart/form-data">--%>
<%--            <input type="hidden" name="userId" value="${user.getId()}">--%>
<%--            <input type="file" name="avatar" accept="image/*" required>--%>
<%--            <button type="submit">上传新头像</button>--%>
<%--        </form>--%>
<%--    </div>--%>

    <!-- 显示用户头像 -->


    <!-- 显示用户订单 -->
<%--    <h2>您的订单:</h2>--%>
<%--    <%--%>
<%--        List<Order> orders = (List<Order>) request.getAttribute("orders");--%>
<%--        for (Order order : orders) {--%>
<%--    %>--%>
<%--    <h3>订单 ID: <%= order.getId() %></h3>--%>
<%--    <h4>下单时间：<%= order. getCreatedAt()%></h4>--%>
<%--    <ul>--%>
<%--        <%--%>
<%--            List<Product> products = (List<Product>) request.getAttribute("products");--%>
<%--            for (Product product : products) {--%>
<%--        %>--%>
<%--        <li><%= product.getName() %> - 数量: <%= product.getQuantity() %> - 价格: <%= product.getPrice() %></li>--%>
<%--        <%--%>
<%--            }--%>
<%--        %>--%>
<%--    </ul>--%>
<%--    <%--%>
<%--        }--%>
<%--    %>--%>

    <div style="text-align: center; margin-top: 20px;">
        <a href="home.jsp">返回首页</a>
    </div>
</div>
</body>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/myaccount.js"></script>
</html>
