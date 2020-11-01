package com.changgou.business.service.impl;

import com.changgou.business.dao.ActivityMapper;
import com.changgou.business.pojo.Activity;
import com.changgou.business.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Activity业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;


    /**
     * Activity条件+分页查询
     * @param activity 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Activity> findPage(Activity activity, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(activity);
        //执行搜索
        return new PageInfo<Activity>(activityMapper.selectByExample(example));
    }

    /**
     * Activity分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Activity> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Activity>(activityMapper.selectAll());
    }

    /**
     * Activity条件查询
     * @param activity
     * @return
     */
    @Override
    public List<Activity> findList(Activity activity){
        //构建查询条件
        Example example = createExample(activity);
        //根据构建的条件查询数据
        return activityMapper.selectByExample(example);
    }


    /**
     * Activity构建查询对象
     * @param activity
     * @return
     */
    public Example createExample(Activity activity){
        Example example=new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if(activity!=null){
            // ID
            if(!StringUtils.isEmpty(activity.getId())){
                    criteria.andEqualTo("id",activity.getId());
            }
            // 活动标题
            if(!StringUtils.isEmpty(activity.getTitle())){
                    criteria.andLike("title","%"+activity.getTitle()+"%");
            }
            // 开始时间
            if(!StringUtils.isEmpty(activity.getStartTime())){
                    criteria.andEqualTo("startTime",activity.getStartTime());
            }
            // 结束时间
            if(!StringUtils.isEmpty(activity.getEndTime())){
                    criteria.andEqualTo("endTime",activity.getEndTime());
            }
            // 状态
            if(!StringUtils.isEmpty(activity.getStatus())){
                    criteria.andEqualTo("status",activity.getStatus());
            }
            // 活动内容
            if(!StringUtils.isEmpty(activity.getContent())){
                    criteria.andEqualTo("content",activity.getContent());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        activityMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Activity
     * @param activity
     */
    @Override
    public void update(Activity activity){
        activityMapper.updateByPrimaryKey(activity);
    }

    /**
     * 增加Activity
     * @param activity
     */
    @Override
    public void add(Activity activity){
        activityMapper.insert(activity);
    }

    /**
     * 根据ID查询Activity
     * @param id
     * @return
     */
    @Override
    public Activity findById(Integer id){
        return  activityMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Activity全部数据
     * @return
     */
    @Override
    public List<Activity> findAll() {
        return activityMapper.selectAll();
    }
}
