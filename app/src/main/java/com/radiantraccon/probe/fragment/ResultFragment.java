package com.radiantraccon.probe.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private int minPage;
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
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Button prevButton = view.findViewById(R.id.button_prev);
        Button nextButton = view.findViewById(R.id.button_next);
        final ArrayList<TextView> pages = new ArrayList<>(5);
        pages.add((TextView)view.findViewById(R.id.textView_page1));
        pages.add((TextView)view.findViewById(R.id.textView_page2));
        pages.add((TextView)view.findViewById(R.id.textView_page3));
        pages.add((TextView)view.findViewById(R.id.textView_page4));
        pages.add((TextView)view.findViewById(R.id.textView_page5));

        Bundle bundle = getArguments();
        if(bundle != null) {
            ArrayList<ResultData> list = bundle.getParcelableArrayList("results");
            paramAddress = bundle.getString("address");
            paramKeyword = bundle.getString("keyword");
            paramPage = bundle.getString("page");

            currentPage = Integer.parseInt(paramPage);
            if(currentPage > 2) {
                minPage = currentPage - 2;
            } else {
                minPage = 1;
            }
            results.setResultDataList(list);
        }

        for(int i=0; i<5; i++) {
            final TextView textView = pages.get(i);
            textView.setText(String.valueOf(minPage + i));
            if(textView.getText().equals(paramPage)) {
                textView.setTypeface(null, Typeface.BOLD);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).crawl(paramAddress, paramKeyword, String.valueOf(textView.getText()), "false");
                }
            });
        }

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage == 1) {
                    return;
                } else {
                    ((MainActivity)getActivity()).crawl(paramAddress, paramKeyword, String.valueOf(--currentPage), "false");
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).crawl(paramAddress, paramKeyword, String.valueOf(++currentPage), "false");
            }
        });
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
    }



    public void addResultDataList(ArrayList<ResultData> list) {
        results.getResultDataList().addAll(list);
    }
}
