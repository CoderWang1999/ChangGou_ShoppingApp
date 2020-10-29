package com.changgou.service.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Log;
import com.changgou.service.goods.service.LogService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    //查询所有
    @GetMapping
    public Result findAll(){
        List<Log> logList=logService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",logList);
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id){
        Log log=logService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",log);
    }

    //新增数据
    @PostMapping
    public Result add(@RequestBody Log log){
        logService.add(log);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    //修改
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Log log,@PathVariable Long id){
        log.setId(id);
        logService.update(log);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    //根据id删除
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        logService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    //多条件搜索
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap){
        List<Log> list=logService.findList(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    //分页查询
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Log> pageList=logService.findPage(searchMap,page,size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}
