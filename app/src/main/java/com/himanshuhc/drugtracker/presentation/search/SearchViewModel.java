package com.himanshuhc.drugtracker.presentation.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.himanshuhc.drugtracker.data.remote.api.RetrofitClient;
import com.himanshuhc.drugtracker.data.remote.repository.DrugRepositoryImpl;
import com.himanshuhc.drugtracker.domain.model.Drug;
import com.himanshuhc.drugtracker.domain.repository.DrugRepository;
import com.himanshuhc.drugtracker.utils.RxCallback;

import java.util.List;


public class SearchViewModel extends ViewModel {
    private final DrugRepository repository ;
    private final MutableLiveData<List<Drug>> searchResults = new MutableLiveData<>();

    // ✅ Constructor accepting DrugRepository
    public SearchViewModel(DrugRepository repository) {
        this.repository = repository;
    }

    public void searchDrugs(String query) {
        repository.fetchApiDrugs(query, new RxCallback<List<Drug>>() {
            @Override
            public void onSuccess(List<Drug> result) {
                searchResults.postValue(result);
            }

            @Override
            public void onError(String errorMessage) {
                searchResults.postValue(null); // or post error to another LiveData
            }
        });
    }

    public LiveData<List<Drug>> getSearchResults() {
        return searchResults;
    }
}