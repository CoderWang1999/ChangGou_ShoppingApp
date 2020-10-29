package com.changgou.service.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Album;
import com.changgou.service.goods.service.AlbumService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //查询全部数据
    @GetMapping
    public Result findAll() {
        List<Album> albumList = albumService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", albumList);
    }

    //根据ID查询数据
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Long id) {
        Album album = albumService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", album);
    }

    //根据id删除
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") Long id) {
        albumService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //新增
    @PostMapping
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    //修改
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Album album, @PathVariable("id") Long id) {
        album.setId(id);
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    //多条件搜索
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Album> list = albumService.findList(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    //分页搜索实现
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Album> pageList=albumService.findPage(searchMap,page,size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}
