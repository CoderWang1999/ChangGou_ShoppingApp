package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.IdWorker;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.*;

/****
 * @Author:shenkunlin
 * @Description:Spu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SkuMapper skuMapper;

    /**
     * Spu条件+分页查询
     *
     * @param spu  查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Spu spu, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(spu);
        //执行搜索
        return new PageInfo<Spu>(spuMapper.selectByExample(example));
    }

    /**
     * Spu分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spu> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Spu>(spuMapper.selectAll());
    }

    /**
     * Spu条件查询
     *
     * @param spu
     * @return
     */
    @Override
    public List<Spu> findList(Spu spu) {
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }


    /**
     * Spu构建查询对象
     *
     * @param spu
     * @return
     */
    public Example createExample(Spu spu) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (spu != null) {
            // 主键
            if (!StringUtils.isEmpty(spu.getId())) {
                criteria.andEqualTo("id", spu.getId());
            }
            // 货号
            if (!StringUtils.isEmpty(spu.getSn())) {
                criteria.andEqualTo("sn", spu.getSn());
            }
            // SPU名
            if (!StringUtils.isEmpty(spu.getName())) {
                criteria.andLike("name", "%" + spu.getName() + "%");
            }
            // 副标题
            if (!StringUtils.isEmpty(spu.getCaption())) {
                criteria.andEqualTo("caption", spu.getCaption());
            }
            // 品牌ID
            if (!StringUtils.isEmpty(spu.getBrandId())) {
                criteria.andEqualTo("brandId", spu.getBrandId());
            }
            // 一级分类
            if (!StringUtils.isEmpty(spu.getCategory1Id())) {
                criteria.andEqualTo("category1Id", spu.getCategory1Id());
            }
            // 二级分类
            if (!StringUtils.isEmpty(spu.getCategory2Id())) {
                criteria.andEqualTo("category2Id", spu.getCategory2Id());
            }
            // 三级分类
            if (!StringUtils.isEmpty(spu.getCategory3Id())) {
                criteria.andEqualTo("category3Id", spu.getCategory3Id());
            }
            // 模板ID
            if (!StringUtils.isEmpty(spu.getTemplateId())) {
                criteria.andEqualTo("templateId", spu.getTemplateId());
            }
            // 运费模板id
            if (!StringUtils.isEmpty(spu.getFreightId())) {
                criteria.andEqualTo("freightId", spu.getFreightId());
            }
            // 图片
            if (!StringUtils.isEmpty(spu.getImage())) {
                criteria.andEqualTo("image", spu.getImage());
            }
            // 图片列表
            if (!StringUtils.isEmpty(spu.getImages())) {
                criteria.andEqualTo("images", spu.getImages());
            }
            // 售后服务
            if (!StringUtils.isEmpty(spu.getSaleService())) {
                criteria.andEqualTo("saleService", spu.getSaleService());
            }
            // 介绍
            if (!StringUtils.isEmpty(spu.getIntroduction())) {
                criteria.andEqualTo("introduction", spu.getIntroduction());
            }
            // 规格列表
            if (!StringUtils.isEmpty(spu.getSpecItems())) {
                criteria.andEqualTo("specItems", spu.getSpecItems());
            }
            // 参数列表
            if (!StringUtils.isEmpty(spu.getParaItems())) {
                criteria.andEqualTo("paraItems", spu.getParaItems());
            }
            // 销量
            if (!StringUtils.isEmpty(spu.getSaleNum())) {
                criteria.andEqualTo("saleNum", spu.getSaleNum());
            }
            // 评论数
            if (!StringUtils.isEmpty(spu.getCommentNum())) {
                criteria.andEqualTo("commentNum", spu.getCommentNum());
            }
            // 是否上架
            if (!StringUtils.isEmpty(spu.getIsMarketable())) {
                criteria.andEqualTo("isMarketable", spu.getIsMarketable());
            }
            // 是否启用规格
            if (!StringUtils.isEmpty(spu.getIsEnableSpec())) {
                criteria.andEqualTo("isEnableSpec", spu.getIsEnableSpec());
            }
            // 是否删除
            if (!StringUtils.isEmpty(spu.getIsDelete())) {
                criteria.andEqualTo("isDelete", spu.getIsDelete());
            }
            // 审核状态
            if (!StringUtils.isEmpty(spu.getStatus())) {
                criteria.andEqualTo("status", spu.getStatus());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        spuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Spu
     *
     * @param spu
     */
    @Override
    public void update(Spu spu) {
        spuMapper.updateByPrimaryKey(spu);
    }

    /**
     * 增加Spu
     *
     * @param spu
     */
    @Override
    public void add(Spu spu) {
        spuMapper.insert(spu);
    }

    /**
     * 根据ID查询Spu
     *
     * @param id
     * @return
     */
    @Override
    public Spu findById(String id) {
        return spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Spu全部数据
     *
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }


    /***
     * 保存Goods
     * @param goods
     */
    @Override
    public void saveGoods(Goods goods) {
        Spu spu = goods.getSpu();
        if (spu.getId() == null) {
            //增加Spu
            long id = idWorker.nextId();
            String gid = String.valueOf(id);
            spu.setId(gid);
            spuMapper.insertSelective(spu);
        } else {
            //修改数据
            spuMapper.updateByPrimaryKeySelective(spu);
            //删除Spu的Sku
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            skuMapper.delete(sku);
        }

        //增加Sku
        Date date = new Date();
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            //ID
            sku.setId(String.valueOf(idWorker.nextId()));
            //创建时间
            sku.setCreateTime(date);
            //修改时间
            sku.setUpdateTime(date);
            //spuID
            sku.setSpuId(spu.getId());
            //商品分类ID
            sku.setCategoryId(spu.getCategory1Id());
            //分类名称
            Category category = categoryMapper.selectByPrimaryKey(spu.getCategory1Id());
            sku.setCategoryName(category.getName());
            //品牌名称
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            sku.setBrandName(brand.getName());
            //Sku名称
            String name = spu.getName();
            Map<String, String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
            Set<Map.Entry<String, String>> entries = specMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                name += " " + entry.getValue();
            }
            sku.setName(name);
            //增加
            skuMapper.insertSelective(sku);
        }
    }

    //根据SpuId查询Spu和Sku的集合
    @Override
    public Goods findGoodsById(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        Sku sku = new Sku();
        sku.setSpuId(String.valueOf(spuId));
        List<Sku> skuList = skuMapper.select(sku);
        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return goods;
    }

    /***
     * 商品审核
     * @param spuId
     */
    @Override
    public void audit(Long spuId) {
        //查询商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //判断商品是否已被删除
        String isDelete = spu.getIsDelete();
        //如果被删除了不用审核
        if (isDelete.equalsIgnoreCase("1")) {
            throw new RuntimeException("该商品已被删除！");
        }
        //如果没被删除，审核通过并上架
        spu.setStatus("1");
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 商品下架
     * @param spuId
     */
    @Override
    public void pull(Long spuId) {
        //查询商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //判断商品是否被删除
        String isDelete = spu.getIsDelete();
        //已删除的商品不用下架
        if (isDelete.equalsIgnoreCase("1")) {
            throw new RuntimeException("此商品已被删除！");
        }
        //没被删除则下架
        spu.setIsMarketable("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 商品上架
     * @param spuId
     */
    @Override
    public void put(Long spuId) {
        //查询商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //判断商品是否被删除
        String isDelete = spu.getIsDelete();
        //已删除的商品不用下架
        if (isDelete.equalsIgnoreCase("1")) {
            throw new RuntimeException("此商品已被删除！");
        }
        //如果商品没通过审核也不能上架
        String status = spu.getStatus();
        if (!status.equalsIgnoreCase("1")) {
            throw new RuntimeException("此商品未通过审核！");
        }
        //没被删除并且已经通过审核则上架
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 批量上架
     * @param ids
     */
    @Override
    public int putMany(Long[] ids) {
        Spu spu = new Spu();
        spu.setIsMarketable("1");
        //批量修改
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        //没有上架
        criteria.andEqualTo("isMarketable", "0");
        //没被删除
        criteria.andEqualTo("isDelete", "0");
        //已通过审核
        criteria.andEqualTo("status", "1");
        return spuMapper.updateByExampleSelective(spu, example);
    }

    /***
     * 批量下架
     * @param ids
     */
    @Override
    public int pullMany(Long[] ids) {
        Spu spu = new Spu();
        spu.setIsMarketable("0");
        //批量修改
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        //已上架
        criteria.andEqualTo("isMarketable", "1");
        //没被删除
        criteria.andEqualTo("isDelete", "0");
        //已通过审核
        criteria.andEqualTo("status", "1");
        return spuMapper.updateByExampleSelective(spu, example);
    }

    /***
     * 逻辑删除
     * @param spuId
     */
    @Override
    public void logicDelete(Long spuId) {
        //查找商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //所删除的商品必须先下架
        if (!spu.getIsMarketable().equalsIgnoreCase("0")){
            throw new RuntimeException("没下架的商品不能被删除！");
        }
        //修改审核状态
        spu.setStatus("0");
        //物理删除
        spu.setIsDelete("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 恢复数据
     * @param spuId
     */
    @Override
    public void restore(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //检查是否删除的商品
        if(!spu.getIsDelete().equals("1")){
            throw new RuntimeException("此商品未删除！");
        }
        //未删除
        spu.setIsDelete("0");
        //未审核
        spu.setStatus("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /***
     * 物理删除
     * @param spuId
     */
    @Override
    public void physicsDelete(Long spuId) {
        //将要被物理删除的商品必须是已被逻辑删除的商品
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        String isDelete = spu.getIsDelete();
        if (!isDelete.equalsIgnoreCase("1")){
            throw new RuntimeException("没有被物理删除的商品不能进行逻辑删除！");
        }
        spuMapper.deleteByPrimaryKey(spuId);
    }
}
