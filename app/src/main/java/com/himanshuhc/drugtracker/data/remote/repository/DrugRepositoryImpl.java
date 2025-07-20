package com.himanshuhc.drugtracker.data.remote.repository;

import com.himanshuhc.drugtracker.data.remote.api.RxNormApi;
import com.himanshuhc.drugtracker.data.remote.dto.ConceptGroupDto;
import com.himanshuhc.drugtracker.data.remote.dto.ConceptPropertyDto;
import com.himanshuhc.drugtracker.data.remote.dto.RxDrugResponse;
import com.himanshuhc.drugtracker.domain.model.Drug;
import com.himanshuhc.drugtracker.domain.repository.DrugRepository;
import com.himanshuhc.drugtracker.utils.RxCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrugRepositoryImpl implements DrugRepository {

    private final RxNormApi api;

    public DrugRepositoryImpl(RxNormApi api) {
        this.api = api;
    }

    @Override
    public void fetchApiDrugs(String drugName, RxCallback<List<Drug>> callback) {
        api.searchDrugs(drugName, "psn").enqueue(new Callback<RxDrugResponse>() {
            @Override
            public void onResponse(Call<RxDrugResponse> call, Response<RxDrugResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Drug> drugs = new ArrayList<>();
                    List<ConceptGroupDto> groups = response.body().drugGroup.conceptGroup;

                    if (groups != null) {
                        for (ConceptGroupDto group : groups) {
                            if (!"SBD".equals(group.tty)) continue;
                            if (group.conceptProperties == null) continue;

                            for (ConceptPropertyDto property : group.conceptProperties) {
                                if (drugs.size() >= 10) break;
                                drugs.add(new Drug(
                                        property.rxcui,
                                        property.psn != null ? property.psn : property.name,
                                        true
                                ));
//                                drugs.add(new Drug(property.rxcui, property.psn, true));
                            }
                        }
                    }

                    callback.onSuccess(drugs);
                } else {
                    callback.onError("API error or empty response");
                }
            }

            @Override
            public void onFailure(Call<RxDrugResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}