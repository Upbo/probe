package com.radiantraccon.probe;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    private ArrayList<KeywordData> keywordDataList;
    private ArrayList<AddressData> addressDataList;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance(ArrayList<KeywordData> keywordDataList,
                                          ArrayList<AddressData> addressDataList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("keywordDataList", keywordDataList);
        args.putParcelableArrayList("addressDataList", addressDataList);
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!= null) {
            keywordDataList = bundle.getParcelableArrayList("keywordDataList");
            addressDataList = bundle.getParcelableArrayList("addressDataList");
            // TODO: Must check parameter works properly
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        // TODO: Initialize RecyclerView of web page addresses

        final EditText keywordEditText = view.findViewById(R.id.editText_keyword);
        final AddressData addressData;
        Button submitButton = view.findViewById(R.id.button_submit);
        Button addressButton = view.findViewById(R.id.button_address);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressFragment addressFragment = new AddressFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frameLayout, addressFragment);
                fragmentTransaction.commit();

            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Send user input to MainActivity
                String s = keywordEditText.getText().toString();

            }
        });
        return view;
    }

}
