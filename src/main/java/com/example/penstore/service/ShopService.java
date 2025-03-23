package com.example.penstore.service;

import com.example.penstore.dao.ShopMapper;
import com.example.penstore.entity.Shop;
import com.example.penstore.dto.PictureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopMapper shopMapper;
    public  String getShopMethodById(String shopid)
    {
        String shopmethod=shopMapper.getShopMethodById(shopid);


        return shopmethod;
    }


    @Transactional
    public void deletePicture(String picid) {
        shopMapper.deletePicture(picid);
    }
    @Transactional
    public List<PictureRequest> getPictures() {
        return shopMapper.getPictures();
    }
    public  void updateShopMethodById(String method, String shopid)
    {
        shopMapper.updateShopMethodById(method,shopid);



    }

    public Shop getShopById(String shopid) {
        return shopMapper.getShopById(shopid);
    }

    public void updateShop(Shop shop) {
        shopMapper.updateShop(shop);
    }


    @Transactional
    public void insertPicture(PictureRequest pictureRequest) {
        try {
            shopMapper.insertPicture(pictureRequest);
        } catch (Exception e) {
            throw new RuntimeException("图片上传失败: " + e.getMessage());
        }
    }



}
