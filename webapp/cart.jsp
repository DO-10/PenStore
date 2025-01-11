<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="objects.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%
    List<Product> cartItems = (List<Product>) request.getAttribute("cartItems");
    BigDecimal totalPrice = (BigDecimal) request.getAttribute("totalPrice");
    String userId = (String) request.getAttribute("userId");
%>

<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
</head>
<body>
    <div class="header">
        <div class="username">
            <% String username = (String) session.getAttribute("username"); // 从请求中获取用户名
                if (username != null) { %>
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
            <a href="">客服</a>
        </div>
    </div>

    <h1>您的购物车</h1>
    <% if (cartItems != null && !cartItems.isEmpty()) { %>
    <form id="cartForm" action="checkoutServlet" method="post">
        <input type="hidden" name="userId" value="<%= userId %>">
        <div class="table">
            <table id="cartTable">
                <tr>
                    <th><label for="selectAll"></label><input type="checkbox" class="product-checkbox" id="selectAll" name="selectedProducts" value=""/>全选</th>
                    <th>商品名称</th>
                    <th>描述</th>
                    <th class="money">单价</th>
                    <th class="amount">数量</th>
                    <th>操作</th>
                </tr>
                <% for (Product product : cartItems) { %>
                <tr id="product_<%= product.getId() %>">
                    <td>
                        <input type="checkbox" class="product-checkbox"  data-product-id='<%=product.getId()%>' name="selectedProducts" value="<%= product.getId() %>"/>
                    </td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getDescription() %></td>
                    <td class="money"><%= product.getPrice() %></td>
                    <td class="amount">
                        <span class="quantity"><%= product.getQuantity() %></span>
                    </td>
                    <td>
                        <button type="button" class="increase-btn" data-product-id="<%= product.getId() %>">增加</button>
                        <button type="button" class="decrease-btn" data-product-id="<%= product.getId() %>">减少</button>
                        <button type="button" class="delete-btn" data-product-id="<%= product.getId() %>">删除</button>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
        <div class="checkout">
            <h2>总价格: <span id="totalPrice"><%= totalPrice != null ? totalPrice.toString() : "0" %></span></h2>
            <button type="submit" class = "submit" name="checkout" value="结算" >结算</button>
        </div>
    </form>



    <% } else { %>
    <p>您的购物车是空的。</p>
    <% } %>

    <div style="text-align: center; margin-top: 20px;">
        <a href="home.jsp">返回首页</a>
    </div>


</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/cart.js"></script>
</html>
