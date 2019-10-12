package com.radiantraccon.probe.site;

import com.radiantraccon.probe.data.ResultData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Quasarzone {
    private static final String MAIN_PAGE = "https://quasarzone.co.kr/";
    private static final String NEWS_GAME = "bbs/board.php?bo_table=qn_game";
    private static final String NEWS_HARDWARE = "bbs/board.php?bo_table=qn_hardware";
    private static final String NEWS_MOBILE = "bbs/board.php?bo_table=qn_mobile";
    private static final String FAVICON = "https://quasarzone.co.kr/favicon.ico";
    private static final String PAGE = "&page=";

    public static ArrayList<ResultData> getData(String address, int page, String keyword) {
        ArrayList<ResultData> ret = null;
        try {
            Document document = Jsoup.connect(address + PAGE + page).get();
            Elements elements = document
                    .select("ul[class=list-body]")
                    .select("li[class=list-item]");
            int size = elements.size();

            for(Element e : elements) {
                Elements temp = e.select("li div[class=wr-subject] a");
                String imageUrl = FAVICON;
                String title = temp.text();
                if(!title.contains(keyword)) {
                    continue;
                }
                String desc = e.select("li div[class=wr-category fs11 hidden-xs]").text();
                String link = temp.attr("href");
                ret.add(new ResultData(imageUrl, title, desc, link));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
