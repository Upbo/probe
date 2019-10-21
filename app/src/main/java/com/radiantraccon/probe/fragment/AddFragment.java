package com.radiantraccon.probe.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
        Bundle args = getArguments();
        if(args != null) {
            Log.e("add",args.getString("address"));
        }
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add, container, false);

        // TODO: Initialize RecyclerView of web page addresses

        final EditText keywordEditText = view.findViewById(R.id.editText_keyword);
        Button submitButton = view.findViewById(R.id.button_submit);
        Button addressButton = view.findViewById(R.id.button_address);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_addFragment_to_addressFragment);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Send user input to MainActivity
                String str = keywordEditText.getText().toString();
                Bundle bundle = new Bundle();
                if(!str.equals("")) {
                    bundle.putString("keyword", str);
                    Navigation.findNavController(view).navigate(R.id.action_addFragment_to_mainFragment, bundle);
                }
                else {
                    bundle.putString("text", getString(R.string.dialog_nullInput));
                    Navigation.findNavController(view).navigate(R.id.action_addFragment_to_eventDialogFragment, bundle);
                }
            }
        });
        return view;
    }
}
