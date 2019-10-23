package com.radiantraccon.probe.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.radiantraccon.probe.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    private int imageId;
    private String title;
    private String address;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add, container, false);

        final EditText keywordEditText = view.findViewById(R.id.editText_keyword);
        final EditText descEditText = view.findViewById(R.id.editText_desc);
        Button submitButton = view.findViewById(R.id.button_submit);
        Button addressButton = view.findViewById(R.id.button_address);

        Bundle bundle = getArguments();
        if(bundle != null) {
            imageId = bundle.getInt("imageId");
            title = bundle.getString("title");
            address = bundle.getString("address");
            addressButton.setText(title);
        }

        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_addFragment_to_addressFragment);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordEditText.getText().toString();
                String desc = descEditText.getText().toString();
                Bundle bundle = new Bundle();
                if(!keyword.equals("")) {
                    bundle.putInt("imageId", imageId);
                    bundle.putString("keyword",keyword);
                    bundle.putString("address", address);
                    bundle.putString("desc", desc);
                    Navigation.findNavController(view).navigate(R.id.action_addFragment_to_mainFragment, bundle);
                } else {
                    Context context = getContext();
                    new MaterialAlertDialogBuilder(context)
                            .setMessage(context.getString(R.string.dialog_nullInput))
                            .setPositiveButton(context.getString(R.string.dialog_confirm), null)
                            .show();

                }
            }
        });
        return view;
    }

}
