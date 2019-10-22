package com.radiantraccon.probe.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radiantraccon.probe.MainActivity;
import com.radiantraccon.probe.R;
import com.radiantraccon.probe.data.ResultAdapter;
import com.radiantraccon.probe.data.ResultData;
import com.radiantraccon.probe.data.ResultDataListWrapper;

import java.util.ArrayList;

public class ResultFragment extends Fragment {
    private ResultDataListWrapper results;
    private String paramAddress;
    private String paramKeyword;
    private String paramPage;
    private int currentPage;

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
        Bundle bundle = getArguments();
        if(bundle != null) {
            ArrayList<ResultData> list = bundle.getParcelableArrayList("results");
            paramAddress = bundle.getString("address");
            paramKeyword = bundle.getString("keyword");
            paramPage = bundle.getString("page");
            currentPage = Integer.parseInt(paramPage);
            results.setResultDataList(list);
        }
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        initRecylcerView(view);
        return view;
    }

    public void initRecylcerView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView_result);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        results.initResultAdapter();
        final ResultAdapter resultAdapter = results.getResultAdapter();
        recyclerView.setAdapter(resultAdapter);

        resultAdapter.setOnItemListener(new ResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // TODO: Move to webpage of clicked item
                Bundle bundle = new Bundle();
                bundle.putString("URL", resultAdapter.getItem(pos).getAddress());
                Navigation.findNavController(v).navigate(R.id.action_resultFragment_to_webFragment, bundle);

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
                    ((MainActivity)getActivity()).crawl(paramAddress, paramKeyword, String.valueOf(++currentPage));
                }
            }
        };
    }
}
