package com.radiantraccon.probe;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.radiantraccon.probe.data.AddressData;
import com.radiantraccon.probe.data.KeywordAdapter;
import com.radiantraccon.probe.data.KeywordData;
import com.radiantraccon.probe.data.KeywordDataListWrapper;
import com.radiantraccon.probe.data.ResultData;
import com.radiantraccon.probe.fragment.AddFragment;
import com.radiantraccon.probe.fragment.AddressFragment;
import com.radiantraccon.probe.fragment.MainFragment;
import com.radiantraccon.probe.fragment.OptionFragment;
import com.radiantraccon.probe.fragment.ResultFragment;
import com.radiantraccon.probe.site.Quasarzone;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
     * Permissions
     */
    private String[] permissionList = {
            Manifest.permission.INTERNET
    };
    // TODO: data classes could be extends abstract class
    // FragmentManager for changing fragments
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment mainFragment = new MainFragment();
    private Fragment optionFragment = new OptionFragment();
    private Fragment addFragment = new AddFragment();
    private Fragment addressFragment = new AddressFragment();
    private Fragment resultFragment = new ResultFragment();
    // Toolbar
    private Toolbar toolbar;

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
        transaction.replace(R.id.frameLayout, mainFragment).commit();
        // endregion
        //////////////////////////////////

        // toolbar
        toolbar = findViewById(R.id.toolbar);
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
        // TODO: findFragmentById returns null. FIX!
        ((MainFragment)fragmentManager.findFragmentById(R.id.frameLayout)).initRecyclerView();

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
    public void showAddressFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, addressFragment).commit();
    }

    private AddressData selected;
    public void onAddFragmentSubmit(String keyword) {
        if(selected == null || keyword.equals("")) {
            // TODO: DEBUG. R.id.frameLayout? R.id.fragment_add?
            ((AddFragment)fragmentManager.findFragmentById(R.id.frameLayout)).showDialog();
        }
        else {
            /*
            keywords.addKeywordData(new KeywordData(0, keyword, selected.getAddress(), "desc here"));
            keywords.sort();
            keywords.getKeywordAdapter().notifyDataSetChanged();
            */
            selected = null;
        }
    }

    public void onAddressFragmentSubmit(AddressData data) {
        selected = data;
    }


    private class Crawler extends AsyncTask<KeywordData, Void, Void> {
        /* TODO:
        check http://siteaddress/robots.txt
        default image icon http://siteaddress/favicon.ico
        prevent getting blacklisted (delay random seconds?)
        get response code
    */
        private int currentPage;

        public Crawler() {
            currentPage = 0;
        }

        public ArrayList<ResultData> crawl(String address, int page, String keyword) {
            return Quasarzone.getData(address, page, keyword);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(KeywordData... keywordData) {
            return null;
        }
    }
}
