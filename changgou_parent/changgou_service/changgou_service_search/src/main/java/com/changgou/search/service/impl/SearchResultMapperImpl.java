package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.search.pojo.SkuInfo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import java.util.ArrayList;
import java.util.Map;

public class SearchResultMapperImpl implements SearchResultMapper {
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
        ArrayList<T> content = new ArrayList<>();
        //如果没有结果返回为空
        if (searchResponse.getHits() == null || searchResponse.getHits().getTotalHits() <= 0) {
            return new AggregatedPageImpl<>(content);
        }
        for (SearchHit hit : searchResponse.getHits()) {
            String sourceAsString = hit.getSourceAsString();
            SkuInfo skuInfo = JSON.parseObject(sourceAsString, SkuInfo.class);
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField highlightField = highlightFields.get("name");

            if (highlightField != null) {
                StringBuffer stringBuffer = new StringBuffer();
                Text[] fragments = highlightField.getFragments();
                for (Text fragment : fragments) {
                    stringBuffer.append(fragment.string());
                }
                skuInfo.setName(stringBuffer.toString());
            }
            content.add((T) skuInfo);
        }
        return new AggregatedPageImpl<>(content, pageable, searchResponse.getHits().getTotalHits(), searchResponse.getAggregations(), searchResponse.getScrollId());
    }
}
