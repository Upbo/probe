package com.radiantraccon.probe;

import android.content.Context;
import android.content.SharedPreferences;

public class CrawlOption {
    public static int pagesPerCrawl;
    public static float version = 0.1f;

    public CrawlOption() { }

    public static void loadPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preferences_filename), Context.MODE_PRIVATE);
        pagesPerCrawl = sharedPreferences.getInt("pagesPerCrawl", 5);
    }

    public static void savePagesPerCrawl(Context context, int page) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preferences_filename), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("pagesPerCrawl", page);
        editor.commit();

        pagesPerCrawl = page;
    }
}
