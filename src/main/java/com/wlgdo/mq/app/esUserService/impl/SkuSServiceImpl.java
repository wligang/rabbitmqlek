package com.wlgdo.mq.app.esUserService.impl;

import com.wlgdo.mq.app.esUserService.ISkuService;
import com.wlgdo.mq.app.model.Skus;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class SkuSServiceImpl implements ISkuService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public SkuSServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public Skus save(Skus sku) {
        Skus save = elasticsearchRestTemplate.save(sku);
        return save;
    }

    @Override
    public Skus get(Long skuId) {
        Skus skus = elasticsearchRestTemplate.get(skuId + "", Skus.class);
        log.info("获取到SKU信息:{}",skus);
        return skus;
    }

    @Override
    public List<Skus> getList(String goodsId) {


        // 第二步构建参数
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("goodsId",goodsId);
//                .matchQuery("goodsId", goodsId);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(matchQueryBuilder).build();

        // 第一步 选方法
        //<T> SearchHits<T> search(Query query, Class<T> clazz, IndexCoordinates index);
        SearchHits<Skus> template = elasticsearchRestTemplate.search(nativeSearchQuery, Skus.class);
        List<SearchHit<Skus>> searchHits = template.getSearchHits();

        List<Skus> skusList = searchHits.stream().map(h -> h.getContent()).collect(Collectors.toList());


//        Query query = Query.findAll();
//        query.addFields("goodsId", goodsId);
//        return elasticsearchRestTemplate.multiGet(query, Skus.class);
        return skusList;
    }
}
