package com.mansh.vschool.runner;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Data
public class ESRunner implements CommandLineRunner {

    @Value("${spring.elasticsearch.hosts}")
    private String hosts;

    private ArrayList<HttpHost> hostList = null;

    private RestHighLevelClient client;


    @Override
    public void run(String... args){
        hostList = (ArrayList<HttpHost>) Arrays.stream(hosts.split(",")).map(this::parseHostList).collect(Collectors.toList());
    }

    public RestHighLevelClient client(){
        if(client == null){
            RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
            builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                @Override
                public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                    builder.setConnectTimeout(1000);
                    builder.setSocketTimeout(30000);
                    builder.setConnectionRequestTimeout(500);
                    return builder;
                }
            });
            builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                    httpAsyncClientBuilder.setMaxConnTotal(100);
                    httpAsyncClientBuilder.setMaxConnPerRoute(100);
                    return httpAsyncClientBuilder;
                }
            });
            client = new RestHighLevelClient(builder);
        }
        return client ;
    }

    private HttpHost parseHostList(String host){
        String[] hostArr = host.split(":");
        return new HttpHost(hostArr[0],Integer.parseInt(hostArr[1]),"http");
    }
}
