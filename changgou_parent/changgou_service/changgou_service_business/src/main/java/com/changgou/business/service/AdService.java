package com.changgou.business.service;

import com.changgou.business.pojo.Ad;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Ad业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface AdService {

    /***
     * Ad多条件分页查询
     * @param ad
     * @param page
     * @param size
     * @return
     */
    PageInfo<Ad> findPage(Ad ad, int page, int size);

    /***
     * Ad分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Ad> findPage(int page, int size);

    /***
     * Ad多条件搜索方法
     * @param ad
     * @return
     */
    List<Ad> findList(Ad ad);

    /***
     * 删除Ad
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Ad数据
     * @param ad
     */
    void update(Ad ad);

    /***
     * 新增Ad
     * @param ad
     */
    void add(Ad ad);

    /**
     * 根据ID查询Ad
     * @param id
     * @return
     */
     Ad findById(Integer id);

    /***
     * 查询所有Ad
     * @return
     */
    List<Ad> findAll();

    //根据广告位置查询广告
    List<Ad> findAdByPosition(String position);
}
