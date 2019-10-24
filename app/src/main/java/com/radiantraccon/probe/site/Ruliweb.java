package com.radiantraccon.probe.site;

import android.util.Log;

import com.radiantraccon.probe.data.ResultData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Ruliweb {

    public static final String MAIN_PAGE = "https://ruliweb.com/";
    public static final String NEWS_SALE = "https://bbs.ruliweb.com/market/board/1020";
    public static final String FAVICON = "https://ruliweb.com/favicon.ico";
    public static final String PAGE = "?page=";

    public static ArrayList<ResultData> getData(String address, String keyword, int page) {
        ArrayList<ResultData> ret = new ArrayList<>();
        try {
            Document document = Jsoup.connect(address + PAGE + page).get();
            Elements elements = document
                    .select("table[class=board_list_table]")
                    .select("tr[class=table_body]");

            for(Element e : elements) {
                Elements temp = e.select("td[class=subject]");

                String imageUrl = FAVICON;
                String title = temp.first().ownText();

                if(!title.contains(keyword)) {
                    continue;
                }

                String desc = e.select("td[class=divsn]").text();
                String link = temp.attr("href");
                Log.e("Result Data", link);
                ret.add(new ResultData(imageUrl, title, link, desc));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
