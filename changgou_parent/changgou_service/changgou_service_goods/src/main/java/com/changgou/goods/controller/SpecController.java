package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@Api(value = "SpecController")
@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;

    /***
     * Spec分页条件搜索实现
     * @param spec
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Spec条件分页查询",notes = "分页条件查询Spec方法详情",tags = {"SpecController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Spec对象",value = "传入JSON数据",required = false) Spec spec, @PathVariable  int page, @PathVariable  int size){
        //调用SpecService实现分页条件查询Spec
        PageInfo<Spec> pageInfo = specService.findPage(spec, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Spec分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Spec分页查询",notes = "分页查询Spec方法详情",tags = {"SpecController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SpecService实现分页查询Spec
        PageInfo<Spec> pageInfo = specService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spec
     * @return
     */
    @ApiOperation(value = "Spec条件查询",notes = "条件查询Spec方法详情",tags = {"SpecController"})
    @PostMapping(value = "/search" )
    public Result<List<Spec>> findList(@RequestBody(required = false) @ApiParam(name = "Spec对象",value = "传入JSON数据",required = false) Spec spec){
        //调用SpecService实现条件查询Spec
        List<Spec> list = specService.findList(spec);
        return new Result<List<Spec>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Spec根据ID删除",notes = "根据ID删除Spec方法详情",tags = {"SpecController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用SpecService实现根据主键删除
        specService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Spec数据
     * @param spec
     * @param id
     * @return
     */
    @ApiOperation(value = "Spec根据ID修改",notes = "根据ID修改Spec方法详情",tags = {"SpecController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Spec对象",value = "传入JSON数据",required = false) Spec spec,@PathVariable Integer id){
        //设置主键值
        spec.setId(id);
        //调用SpecService实现修改Spec
        specService.update(spec);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Spec数据
     * @param spec
     * @return
     */
    @ApiOperation(value = "Spec添加",notes = "添加Spec方法详情",tags = {"SpecController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Spec对象",value = "传入JSON数据",required = true) Spec spec){
        //调用SpecService实现添加Spec
        specService.add(spec);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Spec数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Spec根据ID查询",notes = "根据ID查询Spec方法详情",tags = {"SpecController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable Integer id){
        //调用SpecService实现根据主键查询Spec
        Spec spec = specService.findById(id);
        return new Result<Spec>(true,StatusCode.OK,"查询成功",spec);
    }

    /***
     * 查询Spec全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Spec",notes = "查询所Spec有方法详情",tags = {"SpecController"})
    @GetMapping
    public Result<List<Spec>> findAll(){
        //调用SpecService实现查询所有Spec
        List<Spec> list = specService.findAll();
        return new Result<List<Spec>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
