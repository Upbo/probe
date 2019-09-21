package com.radiantraccon.probe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
     * Permissions
     */
    String[] permissionList = {
            Manifest.permission.INTERNET
    };
    // Data ArrayList of RecyclerView
    ArrayList<KeywordData> keywordDataList;
    // Adapter for RecyclerView
    KeywordAdapter keywordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check permissions and request
        if(!hasPermissions()) {
            requestPermissions(permissionList, 0);
        }

        //////////////////////////////////
         //region BottomNavigationView
        BottomNavigationView bnv = findViewById(R.id.bottomNavView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });
        // endregion
        //////////////////////////////////

        keywordDataList = new ArrayList<>();
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
                // TODO: Change View show favorite sites that include touched keyword
            }
        });
    }

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
                    // if permission granted
                }
                else {
                    // if permission denied,
                }
            }
        }
    }
    // endregion
}
