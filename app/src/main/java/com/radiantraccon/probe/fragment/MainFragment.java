package com.radiantraccon.probe.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radiantraccon.probe.R;
import com.radiantraccon.probe.data.KeywordAdapter;
import com.radiantraccon.probe.data.KeywordData;
import com.radiantraccon.probe.data.KeywordDataListWrapper;

import java.util.ArrayList;
import java.util.NavigableMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private NavController navController;

    // ex:) class Data;
    //      class KeywordData extends Data ... etc
    // Data ArrayList of RecyclerView
    public KeywordDataListWrapper keywords = new KeywordDataListWrapper();
    // TODO: Move code of the mainActivity to this
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null) {
            Log.e("Main", bundle.getString("keyword"));
        }
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // TODO: Load keywordDataList from internal storage
        ArrayList<KeywordData> list  = keywords.readKeywordDataFile(getString(R.string.keywordData_filename), getContext());
        Log.e("MainFragment", "RecyclerView items: " + list.toString());
        keywords.setKeywordDataList(list);
        // RecyclerView
        return view;
    }

    /*
     *  Initialize RecyclerView
     */
    // TODO: Move this function to MainFragment
    public  void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView_main);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        keywords.initAdapter();
        final KeywordAdapter adapter = keywords.getKeywordAdapter();
        recyclerView.setAdapter(adapter);
        // Add OnItemListener to items
        adapter.setOnItemListener(new KeywordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                /*
                // TODO: Change View to show favorite sites that include touched keyword
                KeywordData data = adapter.getItem(pos);
                crawler.crawl(data.getAddress(), 1, data.getKeyword());
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frameLayout, resultFragment);
                */
                keywords.writeKeywordDataFile(getString(R.string.keywordData_filename), getContext());
                Bundle bundle = new Bundle();
                KeywordData data = adapter.getItem(pos);
                bundle.putString("keyword", data.getKeyword());
                bundle.putString("address", data.getAddress());
                navController.navigate(R.id.action_mainFragment_to_resultFragment, bundle);
            }
        });
    }

}
