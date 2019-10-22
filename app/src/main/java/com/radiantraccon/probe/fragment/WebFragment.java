package com.radiantraccon.probe.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.radiantraccon.probe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {

    String url;
    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if(bundle != null) {
            url = bundle.getString("URL");
            Log.e("WebView", url);
        }
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        WebView webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        return view;
    }

}
