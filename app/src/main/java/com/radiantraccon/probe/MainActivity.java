package com.radiantraccon.probe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String[] permissionList = {
            Manifest.permission.INTERNET
    };
    ArrayList<KeywordData> keywordDataList;
    KeywordAdapter keywordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check permissions and request
        if(!hasPermissions()) {
            requestPermissions(permissionList, 0);
        }

        // Initialize recyclerView of favorite keyword items
        keywordDataList = new ArrayList<>();
        KeywordData d = new KeywordData();
        d.setImageId(R.drawable.ic_launcher_background);
        d.setTitle("TEST");
        d.setDescription("THIS IS A TEST DATA");
        keywordDataList.add(d);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        keywordAdapter = new KeywordAdapter(keywordDataList);
        recyclerView.setAdapter(keywordAdapter);

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
