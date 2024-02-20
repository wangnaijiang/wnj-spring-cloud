package com.wnj.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsoupParserTest {
    private String url = "https://www.douban.com/doulist/44514376/?start=index&sort=seq&playable=0&sub_type=";

    @Test
    public void test() throws Exception {
        List<String> list = new ArrayList();
        for (int i = 0; i < 9 ; i++) {
            String indexStr = String.valueOf(i * 25);
            String finalUrl = url.replace("index", indexStr);
            Document doc = Jsoup.connect(finalUrl).get();
            Elements itemList = doc.getElementsByClass("doulist-item");
            for (Element element : itemList) {
                Elements title = element.select("[class=title]");
                String name = title.text().replace("播放全片","").trim();
                Elements rating = element.select("[class=rating]");
                String rate = rating.text().trim();
                list.add(rate+"=="+name);
            }
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}