package com.wlgdo.mq.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Data
@Document(indexName = "poms", indexStoreType = "testbean")
public class Users implements Serializable {

    @Id
    private String userId;
    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String name;

    private String age;

    public Users(String userId, String name, String age) {
        this.age = age;
        this.userId = userId;
        this.name = name;

    }

    public Users() {

    }
}
