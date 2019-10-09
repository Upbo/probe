package com.radiantraccon.probe;

import com.radiantraccon.probe.data.AddressData;
import com.radiantraccon.probe.data.ResultData;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Crawler {

    /* TODO:
        check http://siteaddress/robots.txt
        default image icon http://siteaddress/favicon.ico
        prevent getting blacklisted (delay random seconds?)
        get response code
    */
    public ArrayList<ResultData> crawl(AddressData data) {
        Site site = data.getSite()
    }
}
