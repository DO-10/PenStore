package com.example.penstore.dao;

import com.example.penstore.entity.Shop;
import com.example.penstore.dto.PictureRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {



    String getShopMethodById(String shopid);
    void updateShopMethodById(String method,String shopid);
    Shop getShopById(@Param("shopid") String shopid);
    void updateShop(Shop shop);


    void insertPicture(PictureRequest pictureRequest);
    void deletePicture(String picid);
    public List<PictureRequest> getPictures();
    void insertShop(Shop shop);


    List<Shop> getAllShops();

}
