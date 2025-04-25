package com.example.penstore.controller;

import com.example.penstore.common.CommonResponse;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.dto.CartRequest;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.entity.Goods;
import com.example.penstore.service.GoodsService;
import com.example.penstore.service.impl.GoodsServiceImpl;
import com.example.penstore.service.impl.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.penstore.service.impl.CommentService;

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
    public CommonResponse<List<Goods>> getByCategory(@PathVariable String categoryId, GoodsRequest goodsRequest) {
        List<Goods> goodsList = goodsService.getByCategory(categoryId);
        return  CommonResponse.createForSuccess(goodsList);
    }

    @GetMapping(PathConstants.DETAILS+"/{goodsId}")
    public  CommonResponse<Goods> getById(@PathVariable String goodsId) {
        Goods goods = goodsService.getById(goodsId);
        return CommonResponse.createForSuccess(goods);
    }


    @PostMapping(PathConstants.SEARCH)
    public List<Goods> getByInput(GoodsRequest goodsRequest) {
        //        model.addAttribute("goods", goodsList);
        return goodsService.getByInput(goodsRequest.getKeyword());
    }
    //搜索功能
    @GetMapping(PathConstants.SearchInputServlet)
    @ResponseBody
    public List<Goods> search(@ModelAttribute CartRequest cartRequest) {
        String query=cartRequest.getId();
        return goodsService.searchProducts(query);
    }



}
