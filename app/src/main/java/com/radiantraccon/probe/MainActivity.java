package com.radiantraccon.probe;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.radiantraccon.probe.data.AddressData;
import com.radiantraccon.probe.data.KeywordData;
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
    private Fragment mainFragment = new MainFragment();
    private Fragment optionFragment = new OptionFragment();
    private Fragment addFragment = new AddFragment();
    private Fragment addressFragment = new AddressFragment();
    private Fragment resultFragment = new ResultFragment();
    // Toolbar
    private Toolbar mainToolbar;
    private Toolbar noButtonToolbar;

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
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
        navController = Navigation.findNavController(this,R.id.frameLayout);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        NavigationUI.setupWithNavController(mainToolbar, navController);

        //////////////////////////////////
        //region BottomNavigationView
        BottomNavigationView bnv = findViewById(R.id.bottomNavView);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    // TODO: Debug first! then change replace function to add or something else...
                    case R.id.navigation_menu1: {
                        //
                        break;
                    }
                    case R.id.navigation_menu2: {
                        break;
                    }
                    case R.id.navigation_menu3: {
                        break;
                    }
                }
                return true;
            }
        });
        // endregion
        /////////////////////////////////
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.addFragment:
                return false;
        }
        return false;
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


    public void crawl(KeywordData data) {
        new Crawler().execute(data);
    }

    private class Crawler extends AsyncTask<KeywordData, Void, ArrayList<ResultData>> {
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
            // TODO: Wait dialoag?
        }

        @Override
        protected ArrayList<ResultData> doInBackground(KeywordData... keywordData) {
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<ResultData> resultData) {
            super.onPostExecute(resultData);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(ArrayList<ResultData> resultData) {
            super.onCancelled(resultData);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
