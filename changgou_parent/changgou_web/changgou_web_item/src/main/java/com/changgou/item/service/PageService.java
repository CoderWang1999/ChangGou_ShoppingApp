package com.changgou.item.service;

public interface PageService {
    /**
     * 根据商品的ID 生成静态页
     * @param spuId
     */
    public void createPageHtml(String spuId) ;

    /**
     * 根据商品的ID 删除静态页
     * @param spuId
     */
    void deleteHtml(String spuId);
}
