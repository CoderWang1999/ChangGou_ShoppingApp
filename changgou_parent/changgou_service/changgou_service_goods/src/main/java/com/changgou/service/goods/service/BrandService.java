package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * 品牌列表查询
     */
    List<Brand> findList();

    /**
     * 根据id查询品牌数据
     */
    Brand findById(Integer id);

    /**
     * 品牌新增
     */
    void add(Brand brand);

    /**
     * 品牌修改
     */
    void update(Brand brand);

    /**
     * 品牌删除
     */
    void delById(Integer id);

    /**
     * 品牌列表条件查询
     */
    List<Brand> list(Map<String, Object> searchMap);

    /**
     * 品牌列表分页查询
     * page:当前的页码
     * size:每页显示多少条
     */
    Page<Brand> findPage(int page, int size);

    /**
     * 品牌列表分页+条件查询
     */
    Page<Brand> findPage(Map<String, Object> searchMap, int page, int size);

    List<Map> findBrandListByCategoryName(String categoryName);

}
