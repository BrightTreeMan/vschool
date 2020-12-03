package com.mansh.vschool.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mansh.vschool.runner.ESRunner;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

@Component
public class ESUtils {
    @Resource
    private ESRunner runner;

    private static ESUtils esUtils;

    @PostConstruct
    public void init(){
        esUtils = this;
    }

    public static void createIndex(String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse response = esUtils.runner.client().indices().create(request,RequestOptions.DEFAULT);
        System.out.println("createIndex: " + JSONUtil.parse(response));
    }

    public static boolean existIndex(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return esUtils.runner.client().indices().exists(request,RequestOptions.DEFAULT);
    }


    public static void set(String indexName,String key,Object value) throws IOException {
        IndexRequest request = new IndexRequest(indexName);
        JSONObject json = new JSONObject();
        json.put(key,value);
        request.id("1").source(json);
        IndexResponse response = esUtils.runner.client().index(request,RequestOptions.DEFAULT);
        System.out.println("index: " + JSONUtil.parse(response));
    }

}
