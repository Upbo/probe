package com.radiantraccon.probe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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


        // region Fragments
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // replace or add which is better?
        transaction.replace(R.id.frameLayout, mainFragment).commitAllowingStateLoss();
        // endregion
        //////////////////////////////////

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.toolbar_title);

        //region BottomNavigationView
        BottomNavigationView bnv = findViewById(R.id.bottomNavView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.toolbar_add: {
                showAlertDialog();
                break;
            }
            case R.id.toolbar_search: {
                // TODO: Search item in RecyclerView
                break;
            }
        }
        return true;
    }
    /*
     * Show AlertDialog to get input data
     */
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alertdialog_addkeyword, null);
        builder.setView(view);
        
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
                    // TODO: if Permissions accepted, keep going
                }
                else {
                    // TODO: if Permissions denied, request agian
                }
            }
        }
    }
    // endregion
}
