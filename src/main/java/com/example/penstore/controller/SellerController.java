package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.domain.*;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.dto.OrderRequest;
import com.example.penstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileService fileService;
    @Autowired
    private TransactionSnapshotService snapshotService;
    @Autowired
    private SalesService salesService;

    @GetMapping("/{id}")//请求卖家页面
    public String seller(@PathVariable String id, @RequestParam(required = false) String period, Model model) {
        model.addAttribute("user", userService.getById(id));
        String method=shopService.getShopMethodById(String.valueOf(1));
        Shop shop=shopService.getShopById(String.valueOf(1));
        model.addAttribute("shop",shop);
        model.addAttribute("method",method);

            LocalDate startDate = LocalDate.now().minusDays(7);
            LocalDate endDate = LocalDate.now();

            if ("month".equals(period)) {
                startDate = LocalDate.now().minusMonths(1);
            } else if ("year".equals(period)) {
                startDate = LocalDate.now().minusYears(1);
            }

            Map<String, Object> salesData = salesService.getSalesOverview(startDate, endDate);
            BigDecimal realtimeSales = salesService.getRealtimeSales();
            model.addAttribute("realtimeSales", realtimeSales);
            model.addAttribute("salesData", salesData);
            model.addAttribute("period", period != null ? period : "week");
//            return Pages.SALE;

        return Pages.DASHBOARD;
    }
    @GetMapping(PathConstants.SHOPMANAGEMENT+"/{id}")//请求店铺管理页面
    public String shopManagement(@PathVariable String id,
                                 @RequestParam(value = "activeSection",required = false) String activeSection,
                                 @RequestParam(value = "method",required = false) String method,
                                 Model model) {
        Shop shop = shopService.getShopById(id);
        model.addAttribute("shop", shop);
        model.addAttribute("activeSection", activeSection);
        return Pages.SHOPMANAGEMENT;
    }
    //请求添加/修改商品页面
    @GetMapping(PathConstants.GOODSMANAGEMENT+"/{id}")
    public String updateGoods(@PathVariable String id, Model model) {
        model.addAttribute("goods", goodsService.getById(id));
        return Pages.NEWGOODS;
    }
    @PostMapping(PathConstants.GOODSMANAGEMENT+"/{id}")//请求添加/修改商品
    public String addGoods(@ModelAttribute("goods") GoodsRequest goodsRequest, @PathVariable String id, Model model) {
        String image_url = fileService.saveFile(goodsRequest.getImage(),"goods");
        goodsRequest.setImageUrl(image_url);
        goodsRequest.setShop_id(id);
        goodsService.insertGoods(goodsRequest);
        model.addAttribute("goods", goodsService.getGoodsByShopId(id));
        return Pages.GOODSMANAGEMENT;
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
    public String searchOrder(@ModelAttribute("order")OrderRequest orderRequest, @PathVariable String id, Model model) {
        String shop_id = id;
        orderRequest.setShop_id(shop_id);
        List<Order> orderList = orderService.getOrdersByQuery(orderRequest);
        model.addAttribute("orders", orderList);
        return Pages.ORDERMANAGEMENT;
    }
    @GetMapping(PathConstants.ORDER+"/{status}/{id}")
    public String unshipped(@PathVariable String status, Model model, @PathVariable String id) {
        String shop_id = id;//等待固件的工作
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
        Shop shop = shopService.getShopById(id);
        model.addAttribute("shop", shop);
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
    //上架下架删除商品
    @GetMapping(PathConstants.GOODSMANAGEMENT+"/{operation}/{id}")
    public String goods(@PathVariable String operation, Model model, @PathVariable String id/*, @RequestParam String status*/) {
        String shop_id = id;
        if(operation.equals("update")) {
            model.addAttribute("goods", goodsService.getById(id));
            return Pages.NEWGOODS;
        }
        else {
            goodsService.updateGoods(id, operation, shop_id);
            model.addAttribute("goods", goodsService.getGoodsByShopId(shop_id));
            return Pages.GOODSMANAGEMENT;
        }

    }
    @GetMapping(PathConstants.SHOPMANAGEMENT+PathConstants.CATEGORYMANAGEMENT+"/{id}")
    public String categoryManagement(@PathVariable String id, Model model) {
        String shop_id = id;
        model.addAttribute("categories", categoryService.getCategoryTreeByShopId(shop_id));
        return Pages.CATEGORYMANAGEMENT;
    }
    @PostMapping(PathConstants.SHOPMANAGEMENT+PathConstants.CATEGORYMANAGEMENT+"/{id}")
    public String addCategory(@RequestParam String parent, @RequestParam String child, @PathVariable String id, Model model) {
        categoryService.addCategory(parent, child, id);
        model.addAttribute("categories", categoryService.getCategoryTreeByShopId(id));
        return Pages.CATEGORYMANAGEMENT;
    }
    @PostMapping(PathConstants.CATEGORYMANAGEMENT+"/delete/{id}")
    public String updateCategory(@PathVariable String id, @RequestParam String shopId, Model model) {
            categoryService.deleteCategory(id);
            model.addAttribute("categories", categoryService.getCategoryTreeByShopId(shopId));
            return Pages.CATEGORYMANAGEMENT;
        }

    // 商家查看商品快照（需校验商家身份）
    @GetMapping("/snapshot/{productId}")
    public String getShopSnapshot(
            @PathVariable String productId,
            @ModelAttribute("user") User seller,
            Model model
    ) {
        List<TransactionSnapshot> snapshots =
                snapshotService.getSnapshotsByShopAndProduct(seller.getId(), productId);
        model.addAttribute("snapshots", snapshots);
        return "shop_snapshot_list";
    }
}
