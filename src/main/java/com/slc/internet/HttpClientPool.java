package com.slc.internet;

import cn.hutool.core.io.FileUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpClientPool {
    static final Pattern p = Pattern.compile("^[0-9]*\\..*");

    public static void main(String[] args) throws IOException {

        //使用连接池创建连接
       create(1,100);

    }

    private static void create(int begin, int end) throws IOException {
        File file = new File("C:\\Users\\18484\\Desktop\\txt\\重生为君" + begin + "_" + end + ".txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        PoolingHttpClientConnectionManager poolCM = new PoolingHttpClientConnectionManager();
        poolCM.setMaxTotal(100);
        poolCM.setDefaultMaxPerRoute(10);
        List<String> list = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            list.add(doGet(poolCM, i));
        }
        FileUtil.writeLines(list, file, StandardCharsets.UTF_8);
    }

    private static String doGet(PoolingHttpClientConnectionManager poolCM, int i) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolCM).build();
        HttpGet httpGet = new HttpGet("https://www.wmdown8.com/novel/3dRRI1/" + i + ".html");
        CloseableHttpResponse response = null;
        String data = null;
        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                String contentStr = EntityUtils.toString(httpEntity, "utf8");
                Document document = Jsoup.parse(contentStr);
                StringBuilder builder = new StringBuilder();
                String title = document.getElementsByClass("content").get(0).getElementsByTag("h1").text();
                Matcher matcher = p.matcher(title);
                if (matcher.matches()) {
                    title = title.replaceAll("(^[0-9]*)(\\.)(.*)", "第 $1 章$2 $3 ");
                }
                System.out.println(title);
                String content = document.getElementsByClass("showtxt").get(0).html().replaceAll("<br />", "").replaceAll("\n\n", "\n");
                if (content.contains("scriptapp2;")) {
                    System.out.println(title + " error! 重新发送请求");
                    return doGet(poolCM, i);
                }
                data = builder.append(title).append("\n").append(content).append("\n").toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                //此处不用关闭httpClient，由连接池管理
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
