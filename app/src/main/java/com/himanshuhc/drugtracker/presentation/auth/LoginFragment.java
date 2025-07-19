package com.himanshuhc.drugtracker.presentation.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.himanshuhc.drugtracker.R;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private TextView btnLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Basic validation
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        btnLogin.setEnabled(false);
        btnLogin.setText("Logging in...");

        // Sign in with Firebase
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    btnLogin.setEnabled(true);
                    btnLogin.setText("Login");

                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                        //navigation left


                    } else {
                        Toast.makeText(getContext(), "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}