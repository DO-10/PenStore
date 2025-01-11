package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

import jakarta.servlet.http.HttpSession;
import models.LogService;  // 假设这是处理日志插入的服务类

public class ActionFilter implements Filter {
    private FilterConfig filterConfig;
    private LogService logService;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.logService = new LogService();  // 初始化日志服务类
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 从请求中提取信息
        String email = httpRequest.getParameter("email");  // 假设用户ID存储在session中
        String userId = (String) httpRequest.getSession().getAttribute("userId");  // 假设用户ID存储在session中
        String requestURI = httpRequest.getRequestURI();
        String actionType = determineActionType(httpRequest, requestURI);  // 根据URI判断用户行为类型

        // 如果用户已登录，记录日志
        if (email != null) {
            logService.logAction(email, actionType);
        }
        if (userId != null) {
            logService.logAction(userId, actionType);
        }

        chain.doFilter(request, response);

        // 之后的代码将在请求处理完毕后执行

    }

    private String determineActionType(HttpServletRequest httpRequest, String requestURI) {
        if (requestURI.contains("/productDetailServlet")) {
            String productId = httpRequest.getParameter("id");
            return productId != null ? "浏览商品 - 商品ID: " + productId : "浏览商品 - 无商品ID";
        } else if (requestURI.contains("/AddToCartServlet")) {
            String productId = httpRequest.getParameter("productId");
            return productId != null ? "添加购物车 - 商品ID: " + productId : "无商品ID";
        } else if (requestURI.contains("/checkoutServlet")) {
            return "生成订单";
        } else if (requestURI.contains("/loginServlet")) {
            return "登录";
        } else if (requestURI.contains("/logoutServlet")) {
            return "登出";
        } else if (requestURI.contains("/myPageServlet")) {
            return "查看个人主页";
        } else if (requestURI.contains("/home.jsp")) {
            return "访问主页";
        } else if (requestURI.contains("/CategoryServlet")) {
            return "查看商品分类";
        } else if (requestURI.contains("/cartServlet")) {
            return "查看购物车";
        } else if (requestURI.contains("/changeCartServlet")) {
            if (httpRequest.getParameter("productId") != null) {
                return "删除商品 -id " + httpRequest.getParameter("productId");
            }
            else
                return "修改购物车";
        } else {
            return "未知操作";
        }
    }
}