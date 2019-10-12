package com.radiantraccon.probe.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radiantraccon.probe.R;
import com.radiantraccon.probe.data.ResultAdapter;
import com.radiantraccon.probe.data.ResultDataListWrapper;

public class ResultFragment extends Fragment {
    private ResultDataListWrapper results;

    public ResultFragment() {
        results = new ResultDataListWrapper();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        initRecylcerView(view);
        return view;
    }

    public void initRecylcerView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView_result);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        results.initResultAdapter();
        ResultAdapter resultAdapter = results.getResultAdapter();
        recyclerView.setAdapter(resultAdapter);

        resultAdapter.setOnItemListener(new ResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // TODO: Move to webpage of clicked item

            }
        });

        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = llm.getItemCount();
                boolean isLastItemVisible = llm.findLastCompletelyVisibleItemPosition() > totalItemCount - 1;
                if(isLastItemVisible) {
                    // TODO: load more page and get data
                }
            }
        };
    }
}
