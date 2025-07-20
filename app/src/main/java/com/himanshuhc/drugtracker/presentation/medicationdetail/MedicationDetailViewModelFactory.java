package com.himanshuhc.drugtracker.presentation.medicationdetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.himanshuhc.drugtracker.domain.repository.MedicationRepository;

public class MedicationDetailViewModelFactory implements ViewModelProvider.Factory {

    private final MedicationRepository repository;

    public MedicationDetailViewModelFactory(MedicationRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MedicationDetailViewModel.class)) {
            return (T) new MedicationDetailViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
