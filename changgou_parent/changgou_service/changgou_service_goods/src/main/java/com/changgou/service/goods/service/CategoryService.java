package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Category;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Integer id);

    List<Category> findList(Map searchMap);

    Page<Category> findPage(Map searchMap, int page, int size);

    Page<Category> findPage(int page, int size);

    void add(Category category);

    void update(Category category);

    void delete(Integer id);

}
