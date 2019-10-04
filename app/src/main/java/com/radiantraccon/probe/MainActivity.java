package com.radiantraccon.probe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
     * Permissions
     */
    private String[] permissionList = {
            Manifest.permission.INTERNET
    };
    // TODO: data classes could be extends abstract class
    // ex:) class Data;
    //      class KeywordData extends Data ... etc
    // Data ArrayList of RecyclerView
    public KeywordDataListWrapper keywords;
    public AddressDataListWrapper addresses;
    // FragmentManager for changing fragments
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment mainFragment = new MainFragment();
    private Fragment optionFragment = new OptionFragment();
    private Fragment addFragment = new AddFragment();
    private Fragment addressFragment = new AddressFragment();


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
        String json = keywords.readKeywordDataFile(getString(R.string.keywordData_filename), this);
        ArrayList<KeywordData> list = keywords.parseKeywordData(json);
        keywords.setKeywordDataList(list);
        keywords.initAdapter();
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
                // TODO: Get user input from addFragment and add this to keywordDataList
                // TODO: Write keywordDataList to internal storage

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
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = mainFragment.getView().findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        keywords.initAdapter();
        KeywordAdapter adapter = keywords.getKeywordAdapter();
        recyclerView.setAdapter(adapter);
        // Add OnItemListener to items
        adapter.setOnItemListener(new KeywordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // TODO: Change View to show favorite sites that include touched keyword
            }
        });
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

    public void showAddressFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, addressFragment).commit();
    }

    private AddressData selected;
    public void onAddFragmentSubmit(String keyword) {
        keywords.addKeywordData(new KeywordData(0, keyword, "desc here"));
        /* TODO: IMPORTANT! change keyword data structure
           int imageId
           String keyword
           String address
           String desc
           so this method should add these to recyclerview list
           
           if touch this item in recylcerview, go to crawled data directly

        */
    }

    public void onAddressFragmentSubmit(AddressData data) {
        selected = data;
    }
    // endregion
    //////////////////////////////////
}
