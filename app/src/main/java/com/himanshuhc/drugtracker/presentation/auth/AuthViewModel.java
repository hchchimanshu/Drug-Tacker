package com.himanshuhc.drugtracker.presentation.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends ViewModel {

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void login(String email, String password) {
        isLoading.setValue(true);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        userLiveData.setValue(firebaseAuth.getCurrentUser());
                    } else {
                        errorLiveData.setValue("Login failed: " + task.getException().getMessage());
                    }
                });
    }

    public void signup(String email, String password) {
        isLoading.setValue(true);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    isLoading.setValue(false);
                    if (task.isSuccessful()) {
                        userLiveData.setValue(firebaseAuth.getCurrentUser());
                    } else {
                        errorLiveData.setValue("Signup failed: " + task.getException().getMessage());
                    }
                });
    }

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }
}
