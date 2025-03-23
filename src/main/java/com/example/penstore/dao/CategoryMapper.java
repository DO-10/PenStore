package com.example.penstore.dao;

import com.example.penstore.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> getCategoriesByShopId(String shopId);
    String parentExists(String name);
    void insertCategory(String id, String name, String parentId, String shopId);
    void deleteCategory(String id);
}
