package com.himanshuhc.drugtracker.presentation.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.himanshuhc.drugtracker.data.local.Medication;
import com.himanshuhc.drugtracker.domain.repository.MedicationRepository;

import java.util.List;

public class MyMedicationViewModel extends AndroidViewModel {

    private MedicationRepository repository;
    private MutableLiveData<List<Medication>> medicationsLiveData = new MutableLiveData<>();

    public MyMedicationViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicationRepository(application);
    }

    public LiveData<List<Medication>> getMedicationsLiveData() {
        return medicationsLiveData;
    }

    public void loadMedications() {
        List<Medication> meds = repository.getAllMedications();
        medicationsLiveData.setValue(meds);
    }

    public void deleteMedication(int id) {
        repository.deleteMedication(id);
        loadMedications(); // reload after delete
    }

//    public void insertMedication(String name, String rxcui, boolean isCustom) {
//        repository.insertMedication(name, rxcui, isCustom);
//        loadMedications(); // refresh list
//    }
//
//    // ✅ ADD THIS — check if medication exists
//    public boolean medicationExists(String rxcui) {
//        return repository.medicationExists(rxcui);
//    }
}
