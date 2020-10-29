package com.changgou.service.goods.service;

import com.changgou.goods.pojo.Template;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface TemplateService {
    List<Template> findAll();

    Template findById(Integer id);

    void delete(Integer id);

    void add(Template template);

    void update(Template template);

    List<Template> findList(Map searchMap);

    Page<Template> findPage(Map searchMap, Integer page, Integer size);

}
