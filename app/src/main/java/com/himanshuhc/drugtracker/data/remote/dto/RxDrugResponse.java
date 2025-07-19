package com.himanshuhc.drugtracker.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class RxDrugResponse {

    @SerializedName("drugGroup")
    public DrugGroupDto drugGroup;
}
