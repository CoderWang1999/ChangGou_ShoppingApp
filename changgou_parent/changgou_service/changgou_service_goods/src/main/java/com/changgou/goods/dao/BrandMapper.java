package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {

    /*
    根据categoryId查询Brand
    * */
    @Select("SELECT tb.* FROM tb_brand tb,tb_category_brand tcb WHERE tb.id=tcb.brand_id and tcb.category_id=#{categoryId}")
    List<Brand> findByCategory(Integer categoryId);
}
