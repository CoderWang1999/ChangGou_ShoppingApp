package com.changgou.business.feign;
import com.changgou.business.pojo.Ad;
import com.changgou.entity.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="business")
@RequestMapping("/ad")
public interface AdFeign {

    /***
     * Ad分页条件搜索实现
     * @param ad
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Ad ad, @PathVariable int page, @PathVariable int size);

    /***
     * Ad分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param ad
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Ad>> findList(@RequestBody(required = false) Ad ad);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Integer id);

    /***
     * 修改Ad数据
     * @param ad
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Ad ad, @PathVariable Integer id);

    /***
     * 新增Ad数据
     * @param ad
     * @return
     */
    @PostMapping
    Result add(@RequestBody Ad ad);

    /***
     * 根据ID查询Ad数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Ad> findById(@PathVariable Integer id);

    /***
     * 查询Ad全部数据
     * @return
     */
    @GetMapping
    Result<List<Ad>> findAll();


    //根据广告位置查询广告
    @GetMapping("/ad_read")
    public Result<List<Ad>> findAdByPosition(@RequestParam String position);
}