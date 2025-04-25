package com.example.penstore.controller;

import com.example.penstore.common.CommonResponse;
import com.example.penstore.common.ResponseCode;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.dto.CartRequest;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.entity.Goods;
import com.example.penstore.entity.User;
import com.example.penstore.service.impl.CartService;
import com.example.penstore.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(PathConstants.CART)
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    // ============================== 获取购物车 ==============================
//    @GetMapping
//    public CommonResponse<CartResponse> getCart(@SessionAttribute(name = "user", required = false) User user, UserRequest userRequest) {
//        if (user == null) {
//            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription());
//        }
//
//        List<Goods> items = cartService.getGoodsListByUserId(userRequest.getId());
//        BigDecimal totalPrice = cartService.calculateTotalPriceByUserId(user.getId());
//        boolean allChosen = cartService.isAllChosen(user.getId());
//
//        return CommonResponse.createForSuccess(new CartResponse(items, totalPrice, allChosen));
//    }
    @GetMapping
    public CommonResponse<CartResponse> getCart(UserRequest userRequest) {


        List<Goods> items = cartService.getGoodsListByUserId(userRequest.getId());

        BigDecimal totalPrice = cartService.calculateTotalPriceByUserId(userRequest.getId());
        boolean allChosen = cartService.isAllChosen(userRequest.getId());

        return CommonResponse.createForSuccess(new CartResponse(items, totalPrice, allChosen));
    }

    // ============================== 添加商品到购物车 ==============================
    @PostMapping("/items")
    public CommonResponse<Void> addItem(
            @SessionAttribute(name = "user", required = false) User user,
            @ModelAttribute CartRequest request) {
        if (user == null) {
            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription());
        }

        request.setUser_id(user.getId());
        cartService.addToCart(request);
        return CommonResponse.createForSuccess();
    }

    // ============================== 更新商品数量 ==============================
    @PutMapping("/items/{goodsId}/quantity")
    public CommonResponse<UpdateResponse> updateQuantity(
            @ModelAttribute UserRequest userRequest,
            @PathVariable String goodsId,
            @RequestParam String operation) {  // operation: increase/decrease


        int newQuantity = cartService.updateCart(userRequest.getId(), goodsId, operation);
        return buildUpdateResponse(userRequest.getId(), goodsId);
    }

    // ============================== 更新选中状态 ==============================
    @PutMapping("/items/{goodsId}/chosen")
    public CommonResponse<UpdateResponse> updateChosen(
            @ModelAttribute UserRequest userRequest,
            @PathVariable String goodsId,
            @RequestParam boolean isChosen) {



        cartService.updateCart(userRequest.getId(), goodsId, isChosen ? "choose" : "unchoose");
        return buildUpdateResponse(userRequest.getId(), goodsId);
    }

    // ============================== 删除商品 ==============================
    @DeleteMapping("/items/{goodsId}")
    public CommonResponse<UpdateResponse> deleteItem(
            @ModelAttribute UserRequest userRequest,
            @PathVariable String goodsId) {


        cartService.updateCart(userRequest.getId(), goodsId, "delete");
        return buildUpdateResponse(userRequest.getId(), goodsId);
    }

    private CommonResponse<UpdateResponse> buildUpdateResponse(String userId, String goodsId) {
//        int quantity = cartService.getProductQuantity(userId, goodsId);
        BigDecimal totalPrice = cartService.calculateTotalPriceByUserId(userId);
        boolean allChosen = cartService.isAllChosen(userId);

        return CommonResponse.createForSuccess(new UpdateResponse(totalPrice, allChosen));
    }

    // ====================== DTO 定义 ======================
    private static class CartResponse {
        public final List<Goods> items;
        public final BigDecimal totalPrice;
        public final boolean allChosen;

        public CartResponse(List<Goods> items, BigDecimal totalPrice, boolean allChosen) {
            this.items = items;
            this.totalPrice = totalPrice;
            this.allChosen = allChosen;
        }
    }

    private static class UpdateResponse {
//        public final int quantity;
        public final BigDecimal totalPrice;
        public final boolean allChosen;

        public UpdateResponse(BigDecimal totalPrice, boolean allChosen) {

            this.totalPrice = totalPrice;
            this.allChosen = allChosen;
        }
    }
}