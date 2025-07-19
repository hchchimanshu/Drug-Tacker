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


public class SignUpFragment extends Fragment {

    private EditText etName, etEmail, etPassword;
    private TextView btnCreateAccount;

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Firebase init
        firebaseAuth = FirebaseAuth.getInstance();

        // View bindings
        etName = view.findViewById(R.id.et_name);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnCreateAccount = view.findViewById(R.id.btn_create_account);

        btnCreateAccount.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(authResult -> {
                        Toast.makeText(getContext(), "Account created!", Toast.LENGTH_SHORT).show();
                        // Optionally save name to FirebaseUser profile or local db
                        // Then navigate to login or main screen
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(getContext(), "Signup failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
        });
    }
}