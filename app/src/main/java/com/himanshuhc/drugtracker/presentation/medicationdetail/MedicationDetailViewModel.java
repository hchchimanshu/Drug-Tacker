package com.himanshuhc.drugtracker.presentation.medicationdetail;

import androidx.lifecycle.ViewModel;

import com.himanshuhc.drugtracker.domain.model.Drug;
import com.himanshuhc.drugtracker.domain.repository.MedicationRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicationDetailViewModel extends ViewModel {

    private final MedicationRepository medicationRepository;
    private final ExecutorService executorService;

    public MedicationDetailViewModel(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void addDrugToLocalList(String name, String rxcui, boolean isFromApi, Drug drug) {
        executorService.execute(() -> medicationRepository.insertMedication(name,rxcui,isFromApi, drug.getPsn(),
                drug.getSynonym(), drug.getTty(), drug.getLanguage(), drug.getSuppress()));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown(); // Clean up when ViewModel is destroyed
    }
}
