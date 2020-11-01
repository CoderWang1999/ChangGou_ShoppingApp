package com.changgou.business.service.impl;

import com.changgou.business.dao.AdMapper;
import com.changgou.business.pojo.Ad;
import com.changgou.business.service.AdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Ad业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdMapper adMapper;


    /**
     * Ad条件+分页查询
     * @param ad 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Ad> findPage(Ad ad, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(ad);
        //执行搜索
        return new PageInfo<Ad>(adMapper.selectByExample(example));
    }

    /**
     * Ad分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Ad> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Ad>(adMapper.selectAll());
    }

    /**
     * Ad条件查询
     * @param ad
     * @return
     */
    @Override
    public List<Ad> findList(Ad ad){
        //构建查询条件
        Example example = createExample(ad);
        //根据构建的条件查询数据
        return adMapper.selectByExample(example);
    }


    /**
     * Ad构建查询对象
     * @param ad
     * @return
     */
    public Example createExample(Ad ad){
        Example example=new Example(Ad.class);
        Example.Criteria criteria = example.createCriteria();
        if(ad!=null){
            // ID
            if(!StringUtils.isEmpty(ad.getId())){
                    criteria.andEqualTo("id",ad.getId());
            }
            // 广告名称
            if(!StringUtils.isEmpty(ad.getName())){
                    criteria.andLike("name","%"+ad.getName()+"%");
            }
            // 广告位置
            if(!StringUtils.isEmpty(ad.getPosition())){
                    criteria.andEqualTo("position",ad.getPosition());
            }
            // 开始时间
            if(!StringUtils.isEmpty(ad.getStartTime())){
                    criteria.andEqualTo("startTime",ad.getStartTime());
            }
            // 到期时间
            if(!StringUtils.isEmpty(ad.getEndTime())){
                    criteria.andEqualTo("endTime",ad.getEndTime());
            }
            // 状态
            if(!StringUtils.isEmpty(ad.getStatus())){
                    criteria.andEqualTo("status",ad.getStatus());
            }
            // 图片地址
            if(!StringUtils.isEmpty(ad.getImage())){
                    criteria.andEqualTo("image",ad.getImage());
            }
            // URL
            if(!StringUtils.isEmpty(ad.getUrl())){
                    criteria.andEqualTo("url",ad.getUrl());
            }
            // 备注
            if(!StringUtils.isEmpty(ad.getRemarks())){
                    criteria.andEqualTo("remarks",ad.getRemarks());
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
        adMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Ad
     * @param ad
     */
    @Override
    public void update(Ad ad){
        adMapper.updateByPrimaryKey(ad);
    }

    /**
     * 增加Ad
     * @param ad
     */
    @Override
    public void add(Ad ad){
        adMapper.insert(ad);
    }

    /**
     * 根据ID查询Ad
     * @param id
     * @return
     */
    @Override
    public Ad findById(Integer id){
        return  adMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Ad全部数据
     * @return
     */
    @Override
    public List<Ad> findAll() {
        return adMapper.selectAll();
    }

    @Override
    public List<Ad> findAdByPosition(String position) {
        Ad ad = new Ad();
        ad.setPosition(position);
        List<Ad> ads = findList(ad);
        return ads;
    }

}
