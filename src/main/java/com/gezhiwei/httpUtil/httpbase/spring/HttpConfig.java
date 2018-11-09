package com.gezhiwei.httpUtil.httpbase.spring;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;

public class HttpConfig {

    @Bean(name = "poolingHttpClientConnectionManager")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(10);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(5);
        return poolingHttpClientConnectionManager;
    }

    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager());
        return httpClientBuilder;
    }


    @Bean(name = "httpClient")
    public CloseableHttpClient httpClient() {

        return httpClientBuilder().build();

    }
}
