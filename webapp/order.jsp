<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="objects.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%
    List<Product> orderItems = (List<Product>) request.getAttribute("orderItems");
    String userId = (String) request.getAttribute("userId");
    BigDecimal totalPrice = (BigDecimal) request.getAttribute("totalPrice");
%>

<html>
<head>
    <title>订单信息</title>
    <style>
        /* 你的样式 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        h2 {
            color: #555;
            text-align: center;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            background: #fff;
            margin: 10px 0;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        form {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"],
        textarea,
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* 让padding和border包含在width内 */
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        input[type="submit"] {
            background: #d4af7a;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background-color: #4cae4c;
        }

        #existingAddressContainer, #newAddressContainer {
            margin-top: 15px;
        }

        #userId {
            display: none; /* 隐藏用户ID的div */
        }
    </style>
</head>
<body>
<h1>订单信息</h1>
<% if (orderItems != null && !orderItems.isEmpty()) { %>
<ul>
    <% for (Product product : orderItems) { %>
    <li>
        <%= product.getQuantity() %> X <%= product.getName() %> - 价格: <%= product.getPrice() %>

    </li>
    <% } %>
</ul>
<h2>总价格: <%= totalPrice != null ? totalPrice.toString() : "0.00" %></h2>
<form action="submitOrderServlet" method="post">
    <input type="hidden" name="userId" value="<%= userId %>">
    <div>
        <label>
            <input type="radio" id="useExistingAddress" name="addressOption" value="existing"> 使用现有地址
        </label>
        <label>
            <input type="radio" id="useNewAddress" name="addressOption" value="new"> 使用新地址
        </label>
    </div>
    <div id="existingAddressContainer" style="display: none;">
        <label for="existingAddress">现有地址:</label>
        <select id="existingAddress" name="existingAddress">
            <!-- 现有地址选项将通过JavaScript动态填充 -->
        </select>
    </div>
    <div id="newAddressContainer" style="display: none;">
        <label for="newAddress">新地址:</label>
        <input type="text" id="newAddress" name="newAddress">
    </div>
    <div>
        <label for="notes">备注:</label>
        <textarea id="notes" name="notes"></textarea>
    </div>
    <div>
        <label for="newAddress">电话:</label>
        <input type="text" id="phone" name="phone">
    </div>
    <% for (Product product : orderItems) { %>
    <input type="hidden" name="selectedProducts" value="<%= product.getId() %>">
    <% } %>
    <input type="submit" value="提交订单" />
</form>
<% } else { %>
<p>没有订单信息。</p>
<% } %>
<div id="userId" data-user-id="<%= userId %>"></div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/order.js"></script>
</body>
</html>
