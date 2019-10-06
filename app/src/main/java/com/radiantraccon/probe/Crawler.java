package com.radiantraccon.probe;

import android.os.AsyncTask;

import com.radiantraccon.probe.data.KeywordData;

import java.util.ArrayList;

public class Crawler extends AsyncTask<String, Integer, ArrayList<KeywordData>> {
    @Override
    protected void onPostExecute(ArrayList<KeywordData> keywordData) {
        super.onPostExecute(keywordData);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<KeywordData> doInBackground(String... strings) {
        return null;
    }
}
