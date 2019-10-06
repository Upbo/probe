package com.radiantraccon.probe.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.radiantraccon.probe.MainActivity;
import com.radiantraccon.probe.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        // TODO: Initialize RecyclerView of web page addresses

        final EditText keywordEditText = view.findViewById(R.id.editText_keyword);
        Button submitButton = view.findViewById(R.id.button_submit);
        Button addressButton = view.findViewById(R.id.button_address);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showAddressFragment();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Send user input to MainActivity
                String s = keywordEditText.getText().toString();
                ((MainActivity)getActivity()).onAddFragmentSubmit(s);
            }
        });
        return view;
    }

    public void showDialog() {
        EventDialogFragment.newInstance(
                R.drawable.ic_launcher_background,
                getString(R.string.dialog_nullInput)
        ).show(getFragmentManager(), "eventDialog");
    }

}
