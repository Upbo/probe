package com.radiantraccon.probe;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KeywordDataListWrapper {
    private ArrayList<KeywordData> keywordDataList;
    private KeywordAdapter keywordAdapter;

    public KeywordDataListWrapper(int initialCapacity) {
        keywordDataList = new ArrayList<KeywordData>(initialCapacity);
    }

    public void setKeywordDataList(ArrayList<KeywordData> list) {
        keywordDataList = list;
    }

    public KeywordAdapter getKeywordAdapter() {
        return keywordAdapter;
    }

    public void initAdapter() {
        keywordAdapter = new KeywordAdapter(keywordDataList);
    }

    public void parseKeywordData(String json) {
        if(json == null) {
            return;
        }
        try {
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            for(int i=0; i<length; ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                KeywordData data = new KeywordData();
                data.setKeyword(jsonObject.getString("keyword"));
                data.setDescription(jsonObject.getString("description"));
                data.setImageId(jsonObject.getInt("imageid"));
                keywordDataList.add(data);
            }
        } catch(JSONException e) {
            Log.e("JSON parse", "Can't create JSONArray: " +e.toString());
        }
    }

}
