package com.radiantraccon.probe;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
     * Permissions
     */
    private String[] permissionList = {
            Manifest.permission.INTERNET
    };
    // Data ArrayList of RecyclerView
    private ArrayList<KeywordData> keywordDataList = new ArrayList<>(20);
    // Adapter for RecyclerView
    private KeywordAdapter keywordAdapter;
    // FragmentManager for changing fragments
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment mainFragment = new MainFragment();
    private Fragment optionFragment = new OptionFragment();
    private Fragment addFragment = new AddFragment();

    private Crawler crawler = new Crawler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check permissions and request
        if(!hasPermissions()) {
            requestPermissions(permissionList, 0);
        }
        //////////////////////////////////


        //////////////////////////////////
        // region Fragments
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // replace or add which is better?
        transaction.replace(R.id.frameLayout, mainFragment).commitAllowingStateLoss();
        // endregion
        //////////////////////////////////

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.toolbar_title);

        //////////////////////////////////
        //region BottomNavigationView
        BottomNavigationView bnv = findViewById(R.id.bottomNavView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    // TODO: Debug first! then change replace function to add or something else...
                    case R.id.navigation_menu1: {
                        transaction.replace(R.id.frameLayout, mainFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu2: {
                        break;
                    }
                    case R.id.navigation_menu3: {
                        transaction.replace(R.id.frameLayout, optionFragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
        // endregion
        //////////////////////////////////

        // TODO: Load keywordDataList from internal storage
        String json = readKeywordDataFile("data.json");
        parseKeywordData(json);
        /////// TEST /////////
        KeywordData d = new KeywordData();
        d.setImageId(R.drawable.ic_launcher_background);
        d.setTitle("TEST");
        d.setDescription("THIS IS A TEST DATA");
        keywordDataList.add(d);
        //////////////////////
        // RecyclerView
        initRecyclerView();
    }
    //////////////////////////////////
    // region Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.toolbar_add: {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frameLayout, addFragment).commit();
                break;
            }
            case R.id.toolbar_search: {
                // TODO: Search item in RecyclerView
                break;
            }
        }
        return true;
    }
    // endregion
    //////////////////////////////////


    /*
     *  Initialize RecyclerView
     *
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        // Attach keywordDataList to keywordAdapter
        keywordAdapter = new KeywordAdapter(keywordDataList);
        recyclerView.setAdapter(keywordAdapter);
        // Add OnItemListener to items
        keywordAdapter.setOnItemListener(new KeywordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // TODO: Change View to show favorite sites that include touched keyword
            }
        });
    }

    private void writeKeywordDataFile(String filename) {
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
                jsonWriter.name("title").value(data.getTitle());
                jsonWriter.name("description").value(data.getDescription());
                jsonWriter.name("imageid").value(data.getImageId());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
        } catch(IOException e) {

        }
    }
    private String readKeywordDataFile(String filename) {
        String ret = null;
        InputStream inputStream = null;
        try {
            inputStream = openFileInput(filename);
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

    private void parseKeywordData(String json) {
        if(json == null) {
            return;
        }
        try {
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            for(int i=0; i<length; ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                KeywordData data = new KeywordData();
                data.setTitle(jsonObject.getString("title"));
                data.setDescription(jsonObject.getString("description"));
                data.setImageId(jsonObject.getInt("imageid"));

                keywordDataList.add(data);
            }
        } catch(JSONException e) {
            Log.e("JSON parse", "Can't create JSONArray: " +e.toString());
        }
    }
    //////////////////////////////////
    // region Permissions
    private boolean hasPermissions() {
        for (String p : permissionList) {
            int check = checkCallingOrSelfPermission(p);
            return check != PackageManager.PERMISSION_DENIED;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            for(int i=0; i<grantResults.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // TODO: if Permissions accepted, keep going
                }
                else {
                    // TODO: if Permissions denied, request agian
                }
            }
        }
    }
    // endregion
    //////////////////////////////////
}
