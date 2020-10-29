package com.changgou.service.goods.service.impl;

import com.changgou.goods.pojo.Template;
import com.changgou.service.goods.dao.TemplateMapper;
import com.changgou.service.goods.service.TemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateMapper templateMapper;


    //查询所有
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

    //根据ID查询
    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    //根据ID删除
    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    //新增
    @Override
    public void add(Template template) {
        templateMapper.insert(template);
    }

    //修改
    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKey(template);
    }

    //多条件查询
    @Override
    public List<Template> findList(Map searchMap) {
        Example example = createExample(searchMap);
        return templateMapper.selectByExample(example);
    }

    //分页查询
    @Override
    public Page<Template> findPage(Map searchMap, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Template>) templateMapper.selectByExample(example);
    }

    //构建查询对象
    private Example createExample(Map<String,Object> searchMap){
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            // 模板名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

            // ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 规格数量
            if(searchMap.get("specNum")!=null ){
                criteria.andEqualTo("specNum",searchMap.get("specNum"));
            }
            // 参数数量
            if(searchMap.get("paraNum")!=null ){
                criteria.andEqualTo("paraNum",searchMap.get("paraNum"));
            }
        }
        return example;
    }
}
