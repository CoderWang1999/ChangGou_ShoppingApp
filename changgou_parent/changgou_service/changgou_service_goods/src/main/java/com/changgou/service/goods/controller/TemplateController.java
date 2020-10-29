package com.changgou.service.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Template;
import com.changgou.service.goods.service.TemplateService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    //查询全部数据
    @GetMapping
    public Result findAll() {
        List<Template> templateList = templateService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", templateList);
    }

    //根据ID查询数据
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        Template template = templateService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", template);
    }

    //根据id删除
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        templateService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //新增
    @PostMapping
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    //修改
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Template template, @PathVariable("id") Integer id) {
        template.setId(id);
        templateService.update(template);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    //多条件搜索
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Template> list = templateService.findList(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    //分页搜索实现
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,@PathVariable Integer page,@PathVariable Integer size){
        Page<Template> pageList=templateService.findPage(searchMap,page,size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}
