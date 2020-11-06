package com.changgou.search.controller;

import com.changgou.entity.Page;
import com.changgou.search.feign.SkuFeign;
import com.changgou.search.pojo.SkuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/search")
public class SkuController {
    @Autowired
    private SkuFeign skuFeign;

    @GetMapping(value = "/list")
    public String search(@RequestParam(required = false) Map searchMap, Model model) {
        //调用search微服务
        Map resultMap = skuFeign.search(searchMap);
        //搜索条件
        model.addAttribute("searchMap", searchMap);
        //请求地址
        String url = getUrl(searchMap);
        model.addAttribute("url", url);
        //搜索数据结果
        model.addAttribute("result", resultMap);
        //创建一个分页的对象  可以获取当前页 和总个记录数和显示的页码(以当前页为中心的5个页码)
        Page<SkuInfo> page = new Page<>(
                Long.parseLong(resultMap.get("totalPages").toString()),//总记录数
                Integer.parseInt(resultMap.get("pageNum").toString()),//当前页
                Integer.parseInt(resultMap.get("pageSize").toString())//每页显示条目数
        );
        model.addAttribute("page",page);
        return "search";
    }

    /**
     * url组装和处理
     *
     * @param searchMap
     * @return
     */
    public String getUrl(Map<String, String> searchMap) {
        //url初始地址
        String url = "/search/list";
        if (searchMap != null && searchMap.size() > 0) {
            url += "?";
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                //如果是排序则跳过拼接
                if (entry.getKey().equals("sortField") || entry.getKey().equals("sortRule")) {
                    continue;
                }
                //分页参数不进行拼接
                if (entry.getKey().equalsIgnoreCase("pageNum")){
                    continue;
                }
                String key = entry.getKey();
                String value = entry.getValue();
                url += key + "=" + value + "&";
            }
            //去掉最后一个&
            if (url.lastIndexOf("&") != -1) {
                url = url.substring(0, url.length() - 1);
            }
        }
        return url;
    }
}
