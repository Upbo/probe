package com.radiantraccon.probe.data;

import android.content.Context;
import android.util.JsonWriter;
import android.util.Log;

import com.radiantraccon.probe.data.KeywordAdapter;
import com.radiantraccon.probe.data.KeywordData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KeywordDataListWrapper {
    // data to populate the RecyclerView with
    private ArrayList<KeywordData> keywordDataList;
    private KeywordAdapter keywordAdapter;
    // constructor
    public KeywordDataListWrapper() {

    }
    // getter and setter for list
    public ArrayList<KeywordData> getKeywordDataList() {
        return keywordDataList;
    }
    public void setKeywordDataList(ArrayList<KeywordData> list) {
        keywordDataList = list;
    }
    // getter and init for adapter
    public KeywordAdapter getKeywordAdapter() {
        return keywordAdapter;
    }
    public void initAdapter() {
        keywordAdapter = new KeywordAdapter(keywordDataList);
    }
    // public method for add data
    public void addKeywordData(KeywordData data) {
        keywordDataList.add(data);
    }
    // sort arrayList
    public void sort() {
        Collections.sort(keywordDataList, new Comparator<KeywordData>() {
            @Override
            public int compare(KeywordData o1, KeywordData o2) {
                return o1.getKeyword().compareTo(o2.getKeyword());
            }
        });
    }

    public void writeKeywordDataFile(String filename) {
        File file = new File(filename);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            JsonWriter jsonWriter = new JsonWriter(bufferedWriter);
            jsonWriter.beginArray();
            for(KeywordData data : keywordDataList) {
                jsonWriter.beginObject();
                jsonWriter.name("imageid").value(data.getImageId());
                jsonWriter.name("keyword").value(data.getKeyword());
                jsonWriter.name("address").value(data.getAddress());
                jsonWriter.name("description").value(data.getDescription());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
        } catch(IOException e) {

        }
    }

    public String readKeywordDataFile(String filename, Context context) {
        String ret = null;
        InputStream inputStream = null;
        try {
            inputStream = context.openFileInput(filename);
            if(inputStream != null) {
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String read = null;
                while((read = bufferedReader.readLine()) != null) {
                    stringBuilder.append(read);
                }
                ret = stringBuilder.toString();
            }

        } catch(FileNotFoundException e) {
            Log.e("File read", "File not found: "+e.toString());
        } catch(IOException e) {
            Log.e("File read", "Can't read file: "+e.toString());
        }
        finally {
            try {
                inputStream.close();
            } catch(IOException e) {
                Log.e("File read", "Can't close inputstream: "+e.toString());
            }
        }
        return ret;
    }

    public ArrayList<KeywordData> parseKeywordData(String json) {
        ArrayList<KeywordData> list;
        if(json == null) {
            return null;
        }
        try {
            list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            for(int i=0; i<length; ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                KeywordData data = new KeywordData(
                        jsonObject.getInt("imageid"),
                        jsonObject.getString("keyword"),
                        jsonObject.getString("address"),
                        jsonObject.getString("description")
                );

                list.add(data);
            }
            return list;
        } catch(JSONException e) {
            Log.e("JSON parse", "Can't create JSONArray: " +e.toString());
            return null;
        }
    }

}
