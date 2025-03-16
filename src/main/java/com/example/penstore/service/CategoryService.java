package com.example.penstore.service;

import com.example.penstore.dao.CategoryMapper;
import com.example.penstore.domain.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<Category> getCategoryTreeByShopId(String shopId) {
        List<Category> allCategories = categoryMapper.getCategoriesByShopId(shopId);

        Map<String, Category> categoryMap = allCategories.stream()
                .collect(Collectors.toMap(Category::getId, category -> category));

        List<Category> rootCategories = new ArrayList<>();

        for (Category category : allCategories) {
            if (category.getParentId() == null) {
                rootCategories.add(category);
            } else {
                Category parent = categoryMap.get(category.getParentId());
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(category);
                }
            }
        }
        return rootCategories;
    }
    public void addCategory(String parent, String child, String shopId) {
        String id=categoryMapper. parentExists(parent);
        if(id == null) {
            id = UUID.randomUUID().toString();
            categoryMapper.insertCategory(id, parent, null, shopId);
        }
        if(child != null) {
            categoryMapper.insertCategory(UUID.randomUUID().toString(), child, id, shopId);
        }
    }
    @Transactional
    public void deleteCategory(String id) {
        categoryMapper.deleteCategory(id);
    }
}
