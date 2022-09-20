package com.slc.internet;

import cn.hutool.core.io.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JsoupTest {


    public static void main(String[] args) throws IOException, InterruptedException {
//        long begin = System.currentTimeMillis();
//        List<String> articleList = IntStream.range(1, 1000).parallel().mapToObj(JsoupTest::getA).collect(Collectors.toList());
//        long end = System.currentTimeMillis();
//        System.out.println(end-begin);
//        FileUtil.writeLines(articleList,"C:\\Users\\18484\\Desktop\\txt\\重生为君.txt", StandardCharsets.UTF_8);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String a = getA(1);
        System.out.println();
    }

    private static String getA(int i) {
        long begin = System.currentTimeMillis();

        Connection connect = Jsoup.connect("https://www.wmdown8.com/novel/3dRRI1/" + i + ".html");
        StringBuilder builder = new StringBuilder();
        try {
            Document document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                    .timeout(6000).ignoreContentType(true)
                    .get();
            long mid = System.currentTimeMillis();
            System.out.println("mid : "+ (mid-begin));
            String title = document.getElementsByClass("content").get(0).getElementsByTag("h1").text();
            String content = document.getElementsByClass("showtxt").get(0).html().replaceAll("<br />", "").replaceAll("\n\n","\n");
            builder.append(title).append("\n").append(content).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
        return builder.toString();
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Article {
    private String title;
    private String conetent;
}