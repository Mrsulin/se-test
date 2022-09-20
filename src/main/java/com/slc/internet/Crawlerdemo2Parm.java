package com.slc.internet;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class Crawlerdemo2Parm {
    public static void main(String[] args) {
        //创建一个httpClient对象（打开浏览器）
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = null;
        try {
            int i = 1;
            httpGet = new HttpGet(new URIBuilder( "https://www.wmdown8.com/novel/3dRRI1/" + i + ".html").build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                String content = EntityUtils.toString(httpEntity, "utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
