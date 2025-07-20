package com.himanshuhc.drugtracker.presentation.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.himanshuhc.drugtracker.R;

public class BaseFragment extends Fragment {

    private ProgressBar progressBar;

    protected void setProgressBar(View rootView, int progressBarId) {
        progressBar = rootView.findViewById(progressBarId);
    }

    protected void showProgress(boolean show) {
        if (progressBar != null) {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}