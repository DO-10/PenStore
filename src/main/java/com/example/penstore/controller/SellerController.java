package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.domain.Goods;
import com.example.penstore.domain.Order;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.dto.OrderRequest;
import com.example.penstore.service.GoodsService;
import com.example.penstore.service.OrderService;
import com.example.penstore.service.ShopService;
import com.example.penstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(PathConstants.SELLER)
public class SellerController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;

    @GetMapping("/{id}")//请求卖家页面
    public String seller(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return Pages.DASHBOARD;
    }
    @GetMapping(PathConstants.SHOPMANAGEMENT+"/{id}")//请求店铺管理页面
    public String shopManagement(@PathVariable String id, Model model) {
        //根据userid找到店铺(等待固件完成）
//        model.addAttribute("shop",shopService.getShopByUserId(id));
        return Pages.SHOPMANAGEMENT;
    }
    //请求添加/修改商品页面
    @GetMapping(PathConstants.GOODSMANAGEMENT+"/{id}")
    public String updateGoods(@PathVariable String id) {
        return Pages.NEWGOODS;
    }
    @PostMapping(PathConstants.GOODSMANAGEMENT+"/{id}")//请求添加/修改商品
    public String addGoods(@ModelAttribute("goods") GoodsRequest goodsRequest, @PathVariable String id) {
        goodsRequest.setShop_id(id);
        goodsService.insertGoods(goodsRequest);
        return Pages.SHOPMANAGEMENT;
    }

    //请求订单管理页面
    @GetMapping(PathConstants.ORDERMANAGEMENT+"/{id}")
    public String orderManagement(@PathVariable String id, Model model) {
        String shop_id = id;
        List<Order> orderList = orderService.getOrders(shop_id);
        model.addAttribute("orders", orderList);
        return Pages.ORDERMANAGEMENT;
    }
    @PostMapping(PathConstants.ORDERMANAGEMENT+"/{id}")
    public String searchOrder(@ModelAttribute("order")OrderRequest orderRequest, Model model) {
        String shop_id = "shopA";
        List<Order> orderList = orderService.getOrdersByQuery(orderRequest);
        model.addAttribute("orders", orderList);
        return Pages.ORDERMANAGEMENT;
    }
    @GetMapping(PathConstants.ORDER+"/{status}/{id}")
    public String unshipped(@PathVariable String status, Model model, @PathVariable String id) {
        String shop_id = "shopA";//等待固件的工作
        List<Order> orderList = orderService.getOrders(status,shop_id);
        model.addAttribute("orders", orderList);
        model.addAttribute("status", status);//将状态添加到model中，显示该状态的独特内容
        return Pages.ORDERMANAGEMENT;
    }
    @PostMapping(PathConstants.ORDER+"/{status}/{id}")
    public String searchOrder(@ModelAttribute("order")OrderRequest orderRequest, Model model, @PathVariable String status) {
        List<Order> orderList = orderService.getOrdersByQuery(orderRequest);
        orderRequest.setStatus(status);//添加状态
        model.addAttribute("orders", orderList);
        model.addAttribute("status", status);
        return Pages.ORDERMANAGEMENT;
    }
    //请求商品管理页面
    @GetMapping(PathConstants.SHOPMANAGEMENT+PathConstants.GOODSMANAGEMENT+"/{id}")
    public String goodsManagement(@PathVariable String id, Model model) {
        String shop_id = id;
        model.addAttribute("goods", goodsService.getGoodsByShopId(shop_id));
        return Pages.GOODSMANAGEMENT;
    }
    @PostMapping(PathConstants.SHOPMANAGEMENT+PathConstants.GOODSMANAGEMENT+"/{id}")
    public String searchGoods(@ModelAttribute("goods") GoodsRequest goodsRequest, @PathVariable String id, Model model) {
        String shop_id = id;//等待固件的工作
        goodsRequest.setShop_id(shop_id);
        model.addAttribute("goods", goodsService.getGoodsByQuery(goodsRequest));
        return Pages.GOODSMANAGEMENT;
    }
    //按状态请求商品页面
    @GetMapping(PathConstants.SHOPMANAGEMENT+PathConstants.GOODSMANAGEMENT+"/{status}/{id}")
    public String status(@PathVariable String status, Model model, @PathVariable String id) {
        String shop_id = id;
        List<Goods> goodsList = goodsService.getGoodsByStatus(status,shop_id);
        model.addAttribute("goods", goodsList);
        model.addAttribute("status", status);
        return Pages.GOODSMANAGEMENT;
    }
    @PostMapping(PathConstants.SHOPMANAGEMENT+PathConstants.GOODSMANAGEMENT+"/{status}/{id}")
    public String searchGoods(@ModelAttribute("goods") GoodsRequest goodsRequest, Model model, @PathVariable String status, @PathVariable String id) {
        String shop_id = id;
        goodsRequest.setShop_id(shop_id);
        List<Goods> goodsList = goodsService.getGoodsByQuery(goodsRequest);
        goodsRequest.setStatus(status);
        model.addAttribute("goods", goodsList);
        model.addAttribute("status", status);
        return Pages.GOODSMANAGEMENT;
    }
    @GetMapping(PathConstants.GOODSMANAGEMENT+"/{operation}/{id}")
    public String goods(@PathVariable String operation, Model model, @PathVariable String id/*, @RequestParam String status*/) {
        String shop_id = id;
        if(operation.equals("update")) {
            model.addAttribute("goods", goodsService.getById(id));
            return Pages.NEWGOODS;
        }
        else {
            goodsService.updateGoods(id, operation, shop_id);
            return Pages.GOODSMANAGEMENT;
        }
//        List<Goods> goodsList = goodsService.getGoodsByStatus(status,shop_id);
//        model.addAttribute("goods", goodsList);

    }
    @GetMapping(PathConstants.SHOPMANAGEMENT+PathConstants.CATEGORYMANAGEMENT+"/{id}")
    public String categoryManagement(@PathVariable String id, Model model) {
        String shop_id = id;
        //model.addAttribute("categories", goodsService.getCategoriesByShopId(shop_id));
        return Pages.CATEGORYMANAGEMENT;
    }
}
