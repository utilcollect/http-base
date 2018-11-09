package com.gezhiwei.httpUtil.httpbase;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.TextUtils;

import javax.net.ssl.HostnameVerifier;
import java.util.concurrent.TimeUnit;

public class TestPoolHttpClient {
    public static void main(String[] args) {
        LayeredConnectionSocketFactory sslSocketFactoryCopy = null;

        PublicSuffixMatcher publicSuffixMatcherCopy = PublicSuffixMatcherLoader.getDefault();

        HostnameVerifier hostnameVerifierCopy = new DefaultHostnameVerifier(publicSuffixMatcherCopy);

        sslSocketFactoryCopy = new SSLConnectionSocketFactory(
                SSLContexts.createDefault(),
                hostnameVerifierCopy);


        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactoryCopy)
                .build();
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry,
                new ManagedHttpClientConnectionFactory(),
                new DefaultSchemePortResolver(),
                new SystemDefaultDnsResolver(),
                1000,
                TimeUnit.SECONDS);
    }


    private static String[] split(final String s) {
        if (TextUtils.isBlank(s)) {
            return null;
        }
        return s.split(" *, *");
    }
}
