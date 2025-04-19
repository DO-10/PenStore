package com.example.penstore.controller;

import com.example.penstore.common.CommonResponse;
import com.example.penstore.common.ResponseCode;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.dto.OrderRequest;
import com.example.penstore.entity.*;
import com.example.penstore.service.GoodsService;
import com.example.penstore.service.impl.OrderService;
import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(PathConstants.ORDERS)
@RequiredArgsConstructor // 替换 @Autowired
public class OrderController {
    private final OrderService orderService;
    private final GoodsService goodsService;
    private final HttpSession session;

    // ============================== 订单查询 ==============================
    @GetMapping
    public CommonResponse<List<Order>> listUserOrders() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription());
        }
        return CommonResponse.createForSuccess(orderService.getOrdersByUserId(user.getId()));

    }

    // ============================== 创建订单 ==============================
    @PostMapping
    public CommonResponse<String> createOrder(@RequestBody OrderRequest request) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription() );
        }
        request.setUserId(user.getId());
        String orderId = orderService.createOrder(request);
        return CommonResponse.createForSuccess(orderId);
    }

    // ============================== 订单支付 ==============================
    @PostMapping("/{orderId}/pay")
    public CommonResponse<Void> payOrder(@PathVariable String orderId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription());
        }

        try {
            orderService.payOrder(orderId);
            return CommonResponse.createForSuccess();
        } catch (Exception e) {
            return CommonResponse.createForError(ResponseCode.INTERNAL_ERROR.getCode(), e.getMessage());
        }
    }

    // ============================== 订单发货 ==============================
    @PatchMapping("/{orderId}/deliver")
    public CommonResponse<Void> deliverOrder(@PathVariable String orderId) {
        // 权限校验（示例：仅管理员可操作）
        User user = (User) session.getAttribute("user");
        if (user == null ) {
            return CommonResponse.createForError(ResponseCode.FORBIDDEN.getCode(), ResponseCode.FORBIDDEN.getDescription());
        }

        orderService.deliverOrder(orderId);
        return CommonResponse.createForSuccess();
    }

    // ============================== 获取地址 ==============================
    @GetMapping("/addresses")
    public CommonResponse<List<String>> getAddresses() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription());
        }
        return CommonResponse.createForSuccess(orderService.findAddressesByUserId(user.getId()));
    }
}
