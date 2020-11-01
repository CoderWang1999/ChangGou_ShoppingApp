package com.changgou.business.controller;

import com.changgou.business.pojo.Ad;
import com.changgou.business.service.AdService;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
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
@Api(value = "AdController")
@RestController
@RequestMapping("/ad")
@CrossOrigin
public class AdController {

    @Autowired
    private AdService adService;

    /***
     * Ad分页条件搜索实现
     * @param ad
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Ad条件分页查询",notes = "分页条件查询Ad方法详情",tags = {"AdController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Ad对象",value = "传入JSON数据",required = false) Ad ad, @PathVariable  int page, @PathVariable  int size){
        //调用AdService实现分页条件查询Ad
        PageInfo<Ad> pageInfo = adService.findPage(ad, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Ad分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Ad分页查询",notes = "分页查询Ad方法详情",tags = {"AdController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AdService实现分页查询Ad
        PageInfo<Ad> pageInfo = adService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param ad
     * @return
     */
    @ApiOperation(value = "Ad条件查询",notes = "条件查询Ad方法详情",tags = {"AdController"})
    @PostMapping(value = "/search" )
    public Result<List<Ad>> findList(@RequestBody(required = false) @ApiParam(name = "Ad对象",value = "传入JSON数据",required = false) Ad ad){
        //调用AdService实现条件查询Ad
        List<Ad> list = adService.findList(ad);
        return new Result<List<Ad>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Ad根据ID删除",notes = "根据ID删除Ad方法详情",tags = {"AdController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用AdService实现根据主键删除
        adService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Ad数据
     * @param ad
     * @param id
     * @return
     */
    @ApiOperation(value = "Ad根据ID修改",notes = "根据ID修改Ad方法详情",tags = {"AdController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Ad对象",value = "传入JSON数据",required = false) Ad ad,@PathVariable Integer id){
        //设置主键值
        ad.setId(id);
        //调用AdService实现修改Ad
        adService.update(ad);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Ad数据
     * @param ad
     * @return
     */
    @ApiOperation(value = "Ad添加",notes = "添加Ad方法详情",tags = {"AdController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Ad对象",value = "传入JSON数据",required = true) Ad ad){
        //调用AdService实现添加Ad
        adService.add(ad);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Ad数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Ad根据ID查询",notes = "根据ID查询Ad方法详情",tags = {"AdController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Ad> findById(@PathVariable Integer id){
        //调用AdService实现根据主键查询Ad
        Ad ad = adService.findById(id);
        return new Result<Ad>(true,StatusCode.OK,"查询成功",ad);
    }

    /***
     * 查询Ad全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Ad",notes = "查询所Ad有方法详情",tags = {"AdController"})
    @GetMapping
    public Result<List<Ad>> findAll(){
        //调用AdService实现查询所有Ad
        List<Ad> list = adService.findAll();
        return new Result<List<Ad>>(true, StatusCode.OK,"查询成功",list) ;
    }

    //根据广告位置查询广告
    @GetMapping("/ad_read")
    public Result<List<Ad>> findAdByPosition(@RequestParam String position){
        System.out.println(position+"----------------==============");
        List<Ad> adList=adService.findAdByPosition(position);
        return new Result<List<Ad>>(true,StatusCode.OK,"查询成功",adList);
    }
}
