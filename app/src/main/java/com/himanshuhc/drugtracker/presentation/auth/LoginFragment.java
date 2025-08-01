package com.himanshuhc.drugtracker.presentation.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshuhc.drugtracker.R;
import com.himanshuhc.drugtracker.presentation.base.BaseFragment;

public class LoginFragment extends BaseFragment {

    private EditText etEmail, etPassword;
    private TextView btnLogin;
    private ProgressBar progressBar;
    private AuthViewModel authViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set status bar color to match the fragment background
        setStatusBarColor(R.color.white);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        setProgressBar(view, R.id.progressBar);

        authViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            showProgress(isLoading);
        });

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
        progressBar = view.findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(v -> loginUser());

        authViewModel.getUserLiveData().observe(getViewLifecycleOwner(), firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_myMedicationFragment);
            }
        });

        authViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), errorMsg -> {
            Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            authViewModel.login(email, password);
        } else {
            Toast.makeText(getContext(), "Email and password are required", Toast.LENGTH_SHORT).show();
        }
    }
}