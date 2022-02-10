package com.example.web.ui.naver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.web.R;
import com.example.web.ui.daum.NaverViewModel;

public class NaverFragment {
    private NaverViewModel NaverViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        NaverViewModel = ViewModelProviders.of(this).get(NaverViewModel.class);
        View root = inflater.inflate(R.layout.fragment_naver, container, false);
        final WebView webView = root.findViewById(R.id.web_naver);
        webView.loadUrl("https://naver.com");
        return root;
    }
}
