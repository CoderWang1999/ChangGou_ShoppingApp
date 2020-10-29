package com.changgou.service.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Category;
import com.changgou.service.goods.service.CategoryService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //查询所有
    @GetMapping
    public Result findAll(){
        List<Category> categoryList=categoryService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",categoryList);
    }
    //根据id查询
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Category category=categoryService.findById(id);
        return new Result(true, StatusCode.OK,"查询成功",category);
    }
    //根据多条件查询
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap){
        List<Category> list=categoryService.findList(searchMap);
        return new Result(true, StatusCode.OK,"查询成功",list);
    }
    //分页查询
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Category> pageList=categoryService.findPage(searchMap,page,size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }
    //新增
    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return new Result(true,StatusCode.OK,"添加成功");
    }
    //修改
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Category category,@PathVariable Integer id){
        category.setId(id);
        categoryService.update(category);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    //删除
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
