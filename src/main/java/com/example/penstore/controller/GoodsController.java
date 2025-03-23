package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.entity.Goods;
import com.example.penstore.entity.Shop;
import com.example.penstore.service.GoodsService;
import com.example.penstore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.penstore.entity.Comment;
import com.example.penstore.service.CommentService;

import java.util.List;

@Controller
@SessionAttributes("category_id")
@RequestMapping(PathConstants.GOODS)
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ShopService shopService;


    @GetMapping(PathConstants.CATEGORY)
    public String getByCategory(@RequestParam String category_id, Model model) {
        List<Goods> goodsList = goodsService.getByCategory(category_id);
        String method=shopService.getShopMethodById(String.valueOf(1));
        Shop shop=shopService.getShopById(String.valueOf(1));
        model.addAttribute("category_id",category_id);
        model.addAttribute("shop",shop);
        model.addAttribute("method",method);
        System.out.println(method);
        model.addAttribute("goods", goodsList);
        model.addAttribute("from","good");
        return Pages.GOODSLIST;
    }

    @GetMapping(PathConstants.GOODSDISPLAY)
    public  String getById(@RequestParam String id, Model model) {
        Goods goods = goodsService.getById(id);
        model.addAttribute("goods",goods);

        List<Comment> comments = commentService.getNestedComments(id);
        model.addAttribute("comments", comments);


        return Pages.GOODS;
    }


    @PostMapping(PathConstants.SEARCH)
    public String getByInput(@RequestParam String query, Model model) {
        List<Goods> goodsList = goodsService.getByInput(query);
        model.addAttribute("goods", goodsList);
        return Pages.GOODSLIST;
    }
    //搜索功能
    @GetMapping(PathConstants.SearchInputServlet)
    @ResponseBody
    public List<Goods> search(@RequestParam("q") String query) {
        return goodsService.searchProducts(query);
    }



}
