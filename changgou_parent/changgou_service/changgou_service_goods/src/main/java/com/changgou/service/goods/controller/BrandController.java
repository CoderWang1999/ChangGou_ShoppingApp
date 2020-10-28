package com.changgou.service.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/brand")
@CrossOrigin
@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<List<Brand>> findList() {
        List<Brand> brandList = brandService.findList();
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", brand);
    }

    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Integer id) {
        brandService.delById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/search")
    public Result<List<Brand>> search(@RequestParam Map searchMap) {
        List<Brand> list = brandService.list(searchMap);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    @GetMapping("/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page pageInfo = brandService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getResult());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 根据分类名称查询品牌列表
     * @param category
     * @return
     */
    @GetMapping("/category/{category}")
    public Result  findListByCategoryName(@PathVariable String category){
        System.out.println(category);
        List<Map> brandList = brandService.findListByCategoryName(category);
        return new Result(true,StatusCode.OK,"查询成功",brandList);
    }

    @GetMapping("/category/{categoryName}")
    public Result<List<Map>> findBrandListByCategoryName(@PathVariable("categoryName") String categoryName) {
        List<Map> brandList = brandService.findBrandListByCategoryName(categoryName);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }
}
