package com.himanshuhc.drugtracker.presentation.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.himanshuhc.drugtracker.domain.repository.DrugRepository;

public class SearchViewModelFactory implements ViewModelProvider.Factory {
    private final DrugRepository drugRepository;

    public SearchViewModelFactory(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(drugRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
