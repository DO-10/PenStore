package com.example.penstore.controller;

import com.example.penstore.common.CommonResponse;
import com.example.penstore.constants.Pages;
import com.example.penstore.entity.Goods;
import com.example.penstore.entity.Shop;
import com.example.penstore.dto.PictureRequest;
import com.example.penstore.service.impl.GoodsServiceImpl;
import com.example.penstore.service.impl.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("method")
@RequestMapping("/seller/shopmanagement")
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @GetMapping("/info")
    public String getShopInfo(@RequestParam String id, Model model) {
        //得到shop
        Shop shop = shopService.getShopById(id);
        model.addAttribute("shop", shop);
        model.addAttribute("activeSection", "info");
        return Pages.SHOPMANAGEMENT;
    }
    @GetMapping("/edit")
    public String getShopEdit(@RequestParam String id,  Model model) {
        System.out.println(id);
        Shop shop = shopService.getShopById(id);
        model.addAttribute("shop", shop);
        model.addAttribute("activeSection", "edit");

        System.out.println("edit");
        return  Pages.SHOPMANAGEMENT;
    }

    @PostMapping("/update")
    public String updateShopInfo(@ModelAttribute("shop") Shop shop,@RequestParam String id, Model model) {

        shopService.updateShop(shop);


        System.out.println(111);
        shop = shopService.getShopById(String.valueOf(id));
        model.addAttribute("shop", shop);
//        model.addAttribute("shop", shop);
        CommonResponse<List<Goods>> goodsList = goodsServiceImpl.getByCategory(String.valueOf(2));
        model.addAttribute("goods", goodsList);
        model.addAttribute("activeSection", "info");
        System.out.println("shop");

        return Pages.SHOPMANAGEMENT;
    }


    @GetMapping("/customize")
    public String getCustomize(@RequestParam String id, Model model) {
        Shop shop = shopService.getShopById(id);
        System.out.println("customize");
        String method=shopService.getShopMethodById(id);
        model.addAttribute("method",method);
        model.addAttribute("shop", shop);
        model.addAttribute("activeSection", "customize");
        System.out.println(method);

        return  Pages.SHOPMANAGEMENT;
    }

    @GetMapping("/updatemethod")
    public String updateMethod(@RequestParam String id, @RequestParam String method,Model model) {
        Shop shop = shopService.getShopById(id);
        System.out.println("customize");
        shopService.updateShopMethodById(method,id);
        String methodNew=shopService.getShopMethodById(id);
        model.addAttribute("style",methodNew);
        model.addAttribute("shop", shop);
        model.addAttribute("activeSection", "customize");
        System.out.println("update:"+methodNew);


        return  Pages.SHOPMANAGEMENT;
    }

    @GetMapping("/shop")
    public String shop(@RequestParam String id, @RequestParam String style, @RequestParam String from,Model model) {
        Shop shop = shopService.getShopById(id);
        model.addAttribute("shop", shop);
        model.addAttribute("style", style);
        model.addAttribute("from",from);
        System.out.println(style);
        return "shop";
    }

    @GetMapping("/shopfrom")
    public String shopfrom(@RequestParam String id, @RequestParam String from,Model model) {
        Shop shop = shopService.getShopById(id);
        String style=shopService.getShopMethodById(id);

        model.addAttribute("shop", shop);
        model.addAttribute("style", style);
        model.addAttribute("from",from);
        System.out.println(style);
        return "shop";
    }

@PostMapping("/uploadPicture")
@ResponseBody
public String uploadPicture(@RequestParam("file") MultipartFile file) {
    try {
        // 生成唯一文件名（避免重名）
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 定义服务器存储目录（例如：项目根目录下的 uploads 文件夹）
        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // 创建目录
        }

        // 保存文件到服务器
        Files.copy(file.getInputStream(), uploadPath.resolve(fileName));

        // 将文件路径存入数据库（例如：/uploads/filename.jpg）
        PictureRequest pictureRequest = new PictureRequest();
        pictureRequest.setPicture("/uploads/" + fileName); // 存储相对路径
        shopService.insertPicture(pictureRequest);

        return "success";
    } catch (IOException e) {
        return "error: " + e.getMessage();
    }


}

    @PostMapping("/deletePicture")
    @ResponseBody
    public String deletePicture(@RequestParam("picid") String picid) {
        shopService.deletePicture(picid);
        return "success";
    }

    @GetMapping("/getPictures")
    @ResponseBody
    public List<PictureRequest> getPictures() {
        return shopService.getPictures();
    }

}
