package com.himanshuhc.drugtracker.data.remote.api;

import com.himanshuhc.drugtracker.data.remote.dto.RxDrugResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RxNormApi {

    @GET("drugs.json")
    Call<RxDrugResponse> searchDrugs(@Query("name") String name, @Query("expand") String expand);

}
