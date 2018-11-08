package com.gezhiwei.httpUtil.httpbase;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestCloseableHttpClient {
    public static void main(String[] args) {

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        try {
            httpGet.setURI(new URI("https://www.baidu.com"));
            CloseableHttpResponse execute = closeableHttpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity, "utf-8");
            System.out.println(s);
        } catch (URISyntaxException e) {

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
