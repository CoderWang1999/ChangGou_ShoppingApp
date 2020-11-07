package com.changgou.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.goods.feign.CategoryFeign;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.item.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private SpuFeign spuFeign;
    @Autowired
    private CategoryFeign categoryFeign;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${pagePath}")
    private String pagePath;

    /**
     * 生成静态页面
     *
     * @param spuId
     */
    @Override
    public void createPageHtml(String spuId) {
        //上下文
        Context context = new Context();
        Map<String, Object> dataModel = buildDataModel(spuId);
        context.setVariables(dataModel);
        //准备文件
        File dir = new File(pagePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(dir, spuId + ".html");
        //生成页面
        try (PrintWriter writer = new PrintWriter(dest, "utf-8")) {
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除静态页面
     *
     * @param spuId
     */
    @Override
    public void deleteHtml(String spuId) {
        if (!StringUtils.isEmpty(spuId)) {
            String pathName = "D:\\IDE\\Idea_space\\changgou\\changgou_parent\\changgou_web\\changgou_web_item\\src\\main\\resources\\templates\\items\\" + spuId + ".html";
            File file = new File(pathName);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 构建数据模型
     *
     * @param spuId
     * @return
     */
    private Map<String, Object> buildDataModel(String spuId) {
        //构建数据模型
        HashMap<String, Object> dataMap = new HashMap<>();
        //获取Spu，Sku列表
        Result<Spu> result = spuFeign.findById(spuId);
        Spu spu = result.getData();
        //获取分类信息
        dataMap.put("category1", categoryFeign.findById(spu.getCategory1Id()).getData());
        dataMap.put("category2", categoryFeign.findById(spu.getCategory2Id()).getData());
        dataMap.put("category3", categoryFeign.findById(spu.getCategory3Id()).getData());
        if (spu.getImages() != null) {
            dataMap.put("imageList", spu.getImages().split(","));
        }
        dataMap.put("specificationList", JSON.parseObject(spu.getSpecItems(), Map.class));
        dataMap.put("spu", spu);
        //根据spuId查询sku集合
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        Result<List<Sku>> skuList = skuFeign.findList(sku);
        dataMap.put("skuList", skuList.getData());
        return dataMap;
    }
}
