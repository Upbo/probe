package com.radiantraccon.probe.data;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;


import com.radiantraccon.probe.site.Quasarzone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddressDataListWrapper {
    // data to populate the RecyclerView with
    private ArrayList<AddressData> addressDataList;
    private AddressAdapter addressAdapter;

    // constructor
    public AddressDataListWrapper() { }
    // getter and setter for list
    public ArrayList<AddressData> getAddressDataList() {
        return addressDataList;
    }
    public void setAddressDataList(ArrayList<AddressData> list) {
        addressDataList = list;
    }
    // getter and init for adapter
    public AddressAdapter getAddressAdapter() {
        return addressAdapter;
    }
    public void initAdapter() {
        addressAdapter = new AddressAdapter(addressDataList);
    }

    public void writeAddressDataFile(String filename, Context context) {
        File file = new File(context.getFilesDir(), filename);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            JsonWriter jsonWriter = new JsonWriter(bufferedWriter);
            jsonWriter.beginArray();
            for(AddressData data : addressDataList) {
                jsonWriter.beginObject();
                jsonWriter.name("imageid").value(data.getImageId());
                jsonWriter.name("title").value(data.getAddress());
                jsonWriter.name("address").value(data.getTitle());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch(IOException e) {
            Log.e("File write", "Can't write FIle "+e.toString());
        }
    }

    public ArrayList<AddressData> readAddressDataFile(String filename, Context context) {
        ArrayList<AddressData> ret = new ArrayList<>();
        File file = new File(context.getFilesDir(), filename);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            if(!file.exists()) {
                writeFirstDataFile(filename, context);
                Log.e("File read", "File not exists");
            }
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            JsonReader jsonReader = new JsonReader(bufferedReader);
            jsonReader.beginArray();
            while(jsonReader.hasNext()) {
                int imageId = jsonReader.nextInt();
                String title = jsonReader.nextString();
                String address = jsonReader.nextString();
                AddressData data = new AddressData(imageId, title, address);
                ret.add(data);
                Log.e("File read", data.toString() + "readed ");
            }
            jsonReader.endArray();
            jsonReader.close();

        } catch(FileNotFoundException e) {
            Log.e("File read", "File not found: "+e.toString());
        } catch(IOException e) {
            Log.e("File read", "Can't read file: "+e.toString());
        }
        finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch(IOException e) {
                Log.e("File read", "Can't close inputstream: "+e.toString());
            }
        }
        Log.e("result",ret.toString());
        return ret;
    }

    private void writeFirstDataFile(String filename, Context context) {
        AddressData QUASARZONE_GAME = new AddressData(R.drawable.ic_launcher_background, getString(R.string.quasarzone_game), Quasarzone.MAIN_PAGE);
        addressDataList.add(QUASARZONE_GAME);
    }
}
