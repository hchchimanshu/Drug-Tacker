package com.himanshuhc.drugtracker.domain.repository;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.himanshuhc.drugtracker.data.local.Medication;
import com.himanshuhc.drugtracker.data.local.MedicationDatabaseHelper;

import java.util.List;

public class MedicationRepository {

    private final MedicationDatabaseHelper dbHelper;

    public MedicationRepository(Context context) {
        dbHelper = new MedicationDatabaseHelper(context);
    }

    // Get all medications from DB
    public List<Medication> getAllMedications() {
        return dbHelper.getAllMedications();
    }

    // Insert a medication into DB
    public boolean insertMedication(String name, String rxcui, boolean isCustom,
                                    String psn, String synonym, String tty, String language, String suppress) {
        return dbHelper.insertMedication(name, rxcui, isCustom, psn, synonym, tty, language, suppress);
    }


    // Delete medication by ID
    public void deleteMedication(int id) {
        dbHelper.deleteMedication(id);
    }

    // Count how many API-added drugs exist
    public int getApiDrugCount() {
        return dbHelper.getApiDrugCount();
    }

    // Count how many custom drugs exist
    public int getCustomDrugCount() {
        return dbHelper.getCustomDrugCount();
    }

    // Check if a medication with the same rxcui already exists
    public boolean medicationExists(String rxcui) {
        return dbHelper.medicationExists(rxcui);
    }

    public int getTotalMedicationCount() {
        return getApiDrugCount() + getCustomDrugCount();
    }

}
