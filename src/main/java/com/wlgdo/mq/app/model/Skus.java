package com.wlgdo.mq.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document(indexName = "goods", indexStoreType = "skubean")
public class Skus implements Serializable {
    @Id
    private Long skuId;
    private Long goodsId;
    private String name;
    private Integer num;
    private LocalDateTime createTime;

    public Skus() {
    }

    public Skus(Long goodsId, Long skuId, String name) {
        this.goodsId = goodsId;
        this.skuId = skuId;
        this.name = name;
        this.num = 10;

    }
}
