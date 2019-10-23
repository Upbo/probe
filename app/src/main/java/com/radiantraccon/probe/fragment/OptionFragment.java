package com.radiantraccon.probe.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.radiantraccon.probe.CrawlOption;
import com.radiantraccon.probe.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionFragment extends Fragment {


    public OptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_option, container, false);
        Button saveButton = view.findViewById(R.id.button_save);
        final EditText pageEditText = view.findViewById(R.id.editText_page);
        pageEditText.setText(String.valueOf(CrawlOption.pagesPerCrawl));
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Toast.makeText(context, "Save complete!", Toast.LENGTH_SHORT);
                CrawlOption.savePagesPerCrawl(context, Integer.parseInt(pageEditText.getText().toString()));
            }
        });
        return view;
    }

}
