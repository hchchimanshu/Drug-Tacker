package com.himanshuhc.drugtracker;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class DrugTrackerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase here
        FirebaseApp.initializeApp(this);
    }
}
