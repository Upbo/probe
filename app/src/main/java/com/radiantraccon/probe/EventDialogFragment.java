package com.radiantraccon.probe;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class EventDialogFragment extends DialogFragment {
    private int imageId;
    private String eventText;
    public EventDialogFragment() { }
    public static EventDialogFragment newInstance(int imageId, String text) {
        EventDialogFragment fragment = new EventDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("imageId", imageId);
        bundle.putString("text", text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            imageId = bundle.getInt("imageId");
            eventText = bundle.getString("text");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_event, container);
        ImageView imageView = view.findViewById(R.id.imageView_icon);
        Button button = view.findViewById(R.id.button_event);
        TextView textView = view.findViewById(R.id.textView_event);

        imageView.setImageResource(imageId);
        textView.setText(eventText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
