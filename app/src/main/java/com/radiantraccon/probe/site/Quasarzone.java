package com.radiantraccon.probe.site;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Quasarzone extends Site{
    private static final String MAIN_PAGE = "https://quasarzone.co.kr/";
    private static final String NEWS_GAME = "bbs/board.php?bo_table=qn_game";
    private static final String NEWS_HARDWARE = "bbs/board.php?bo_table=qn_hardware";
    private static final String NEWS_MOBILE = "bbs/board.php?bo_table=qn_mobile";
    public static void getTitle() {
        try {
            Document document = Jsoup.connect(MAIN_PAGE + NEWS_GAME).get();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void getDesc() {

    }

    public static void getImage() {

    }

    public static void getLink() {

    }

}
