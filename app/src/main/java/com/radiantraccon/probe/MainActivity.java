package com.radiantraccon.probe;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
                    case R.id.navigation_menu1: {
                        // TODO: navigate each fragment

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

    public void crawl(String address, String keyword, String page, String nav) {
        // if nav == true, navigate mainFragment to resultFragment
        // if nav == false, don't
        crawler = new Crawler();
        crawler.execute(address, keyword, page, nav);
    }

    private class Crawler extends AsyncTask<String, Void, ArrayList<ResultData>> {
        /* TODO:
        check http://siteaddress/robots.txt
        default image icon http://siteaddress/favicon.ico
        prevent getting blacklisted (delay random seconds?)
        get response code
        */

        private ProgressDialog progressDialog;
        private ArrayList<ResultData> results;
        private String address;
        private String keyword;
        private String page;
        private String nav;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // TODO: Wait dialoag?
            // temprary dialog
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();
        }

        @Override
        protected ArrayList<ResultData> doInBackground(String... strings) {
            Log.e("AsyncTask" , strings[0] + " "+ strings[1]+ " "+ strings[2]);
            address = strings[0];
            keyword = strings[1];
            page = strings[2];
            nav = strings[3];
            int p = Integer.parseInt(page);
            switch(address) {
                case Quasarzone.NEWS_GAME:
                    results = Quasarzone.getData(address, keyword, p);
            }
            return results;
        }

        @Override
        protected void onPostExecute(ArrayList<ResultData> resultData) {
            super.onPostExecute(resultData);
            //TODO: temprary dialog
            progressDialog.dismiss();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("results", results);
            bundle.putString("keyword", keyword);
            bundle.putString("address", address);
            bundle.putString("page", page);
            if(nav.equals("true")) {
                navController.navigate(R.id.action_mainFragment_to_resultFragment, bundle);
            } else {
                navController.navigate(R.id.action_resultFragment_self, bundle);

            }
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
