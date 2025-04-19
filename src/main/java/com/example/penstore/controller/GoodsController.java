package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.entity.Goods;
import com.example.penstore.entity.Shop;
import com.example.penstore.service.GoodsService;
import com.example.penstore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.penstore.entity.Comment;
import com.example.penstore.service.CommentService;

import java.util.List;

@RestController
@SessionAttributes("category_id")
@RequestMapping(PathConstants.GOODS)
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ShopService shopService;

//home页按分类请求商品列表
    @GetMapping("/{categoryId}")
    public List<Goods> getByCategory(@PathVariable String categoryId, GoodsRequest goodsRequest) {
        List<Goods> goodsList = goodsService.getByCategory(categoryId);
        return goodsList;
    }

    @GetMapping(PathConstants.DETAILS+"/{goodsId}")
    public  Goods getById(@PathVariable String goodsId) {
        return goodsService.getById(goodsId);
    }


    @PostMapping(PathConstants.SEARCH)
    public List<Goods> getByInput(GoodsRequest goodsRequest) {
        //        model.addAttribute("goods", goodsList);
        return goodsService.getByInput(goodsRequest.getKeyword());
    }
    //搜索功能
    @GetMapping(PathConstants.SearchInputServlet)
    @ResponseBody
    public List<Goods> search(@RequestParam("q") String query) {
        return goodsService.searchProducts(query);
    }



}
