package com.changgou.business.service;

import com.changgou.business.pojo.Activity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Activity业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface ActivityService {

    /***
     * Activity多条件分页查询
     * @param activity
     * @param page
     * @param size
     * @return
     */
    PageInfo<Activity> findPage(Activity activity, int page, int size);

    /***
     * Activity分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Activity> findPage(int page, int size);

    /***
     * Activity多条件搜索方法
     * @param activity
     * @return
     */
    List<Activity> findList(Activity activity);

    /***
     * 删除Activity
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Activity数据
     * @param activity
     */
    void update(Activity activity);

    /***
     * 新增Activity
     * @param activity
     */
    void add(Activity activity);

    /**
     * 根据ID查询Activity
     * @param id
     * @return
     */
     Activity findById(Integer id);

    /***
     * 查询所有Activity
     * @return
     */
    List<Activity> findAll();
}
