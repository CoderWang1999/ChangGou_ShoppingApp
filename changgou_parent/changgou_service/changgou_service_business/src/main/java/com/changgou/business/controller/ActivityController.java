package com.changgou.business.controller;

import com.changgou.business.pojo.Activity;
import com.changgou.business.service.ActivityService;
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
@Api(value = "ActivityController")
@RestController
@RequestMapping("/activity")
@CrossOrigin
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /***
     * Activity分页条件搜索实现
     * @param activity
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Activity条件分页查询",notes = "分页条件查询Activity方法详情",tags = {"ActivityController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "Activity对象",value = "传入JSON数据",required = false) Activity activity, @PathVariable  int page, @PathVariable  int size){
        //调用ActivityService实现分页条件查询Activity
        PageInfo<Activity> pageInfo = activityService.findPage(activity, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Activity分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Activity分页查询",notes = "分页查询Activity方法详情",tags = {"ActivityController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ActivityService实现分页查询Activity
        PageInfo<Activity> pageInfo = activityService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param activity
     * @return
     */
    @ApiOperation(value = "Activity条件查询",notes = "条件查询Activity方法详情",tags = {"ActivityController"})
    @PostMapping(value = "/search" )
    public Result<List<Activity>> findList(@RequestBody(required = false) @ApiParam(name = "Activity对象",value = "传入JSON数据",required = false) Activity activity){
        //调用ActivityService实现条件查询Activity
        List<Activity> list = activityService.findList(activity);
        return new Result<List<Activity>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Activity根据ID删除",notes = "根据ID删除Activity方法详情",tags = {"ActivityController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用ActivityService实现根据主键删除
        activityService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Activity数据
     * @param activity
     * @param id
     * @return
     */
    @ApiOperation(value = "Activity根据ID修改",notes = "根据ID修改Activity方法详情",tags = {"ActivityController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Activity对象",value = "传入JSON数据",required = false) Activity activity,@PathVariable Integer id){
        //设置主键值
        activity.setId(id);
        //调用ActivityService实现修改Activity
        activityService.update(activity);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Activity数据
     * @param activity
     * @return
     */
    @ApiOperation(value = "Activity添加",notes = "添加Activity方法详情",tags = {"ActivityController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Activity对象",value = "传入JSON数据",required = true) Activity activity){
        //调用ActivityService实现添加Activity
        activityService.add(activity);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Activity数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Activity根据ID查询",notes = "根据ID查询Activity方法详情",tags = {"ActivityController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Activity> findById(@PathVariable Integer id){
        //调用ActivityService实现根据主键查询Activity
        Activity activity = activityService.findById(id);
        return new Result<Activity>(true,StatusCode.OK,"查询成功",activity);
    }

    /***
     * 查询Activity全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Activity",notes = "查询所Activity有方法详情",tags = {"ActivityController"})
    @GetMapping
    public Result<List<Activity>> findAll(){
        //调用ActivityService实现查询所有Activity
        List<Activity> list = activityService.findAll();
        return new Result<List<Activity>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
