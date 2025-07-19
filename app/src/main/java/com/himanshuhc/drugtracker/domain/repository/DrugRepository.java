package com.himanshuhc.drugtracker.domain.repository;

import com.himanshuhc.drugtracker.domain.model.Drug;
import com.himanshuhc.drugtracker.utils.RxCallback;

import java.util.List;

public interface DrugRepository {

    void fetchApiDrugs(String drugName, RxCallback<List<Drug>> callback);

}
