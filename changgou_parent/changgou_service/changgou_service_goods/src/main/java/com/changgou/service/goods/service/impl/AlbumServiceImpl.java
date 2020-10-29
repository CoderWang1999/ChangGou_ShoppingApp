package com.changgou.service.goods.service.impl;

import com.changgou.goods.pojo.Album;
import com.changgou.service.goods.dao.AlbumMapper;
import com.changgou.service.goods.service.AlbumService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;


    //查询所有
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    //根据ID查询
    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    //根据ID删除
    @Override
    public void delete(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    //新增
    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    //修改
    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKey(album);
    }

    //多条件查询
    @Override
    public List<Album> findList(Map searchMap) {
        Example example = createExample(searchMap);
        return albumMapper.selectByExample(example);
    }

    @Override
    public Page<Album> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        return (Page<Album>) albumMapper.selectAll();
    }

    //分页查询
    @Override
    public Page<Album> findPage(Map searchMap, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Album>) albumMapper.selectByExample(example);
    }

    //构建查询对象
    private Example createExample(Map<String,Object> searchMap){
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap!=null){
            // 相册名称
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }
            // 相册封面
            if(searchMap.get("image")!=null && !"".equals(searchMap.get("image"))){
                criteria.andLike("image","%"+searchMap.get("image")+"%");
            }
            // 图片列表
            if(searchMap.get("image_items")!=null && !"".equals(searchMap.get("image_items"))){
                criteria.andLike("image_items","%"+searchMap.get("image_items")+"%");
            }

        }
        return example;
    }
}
