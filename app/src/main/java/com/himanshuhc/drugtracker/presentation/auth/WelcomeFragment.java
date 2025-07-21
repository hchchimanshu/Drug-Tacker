package com.himanshuhc.drugtracker.presentation.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.himanshuhc.drugtracker.R;
import com.himanshuhc.drugtracker.presentation.base.BaseFragment;

public class WelcomeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set status bar color to match the fragment background
        setStatusBarColor(R.color.light_blue);

        // Navigate on a button click or conditionally
        view.findViewById(R.id.btn_create_account).setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_welcomeFragment_to_signupFragment)
        );

        view.findViewById(R.id.tv_login).setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_welcomeFragment_to_loginFragment)
        );
    }
}