package com.himanshuhc.drugtracker.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ConceptPropertyDto {

    @SerializedName("rxcui")
    public String rxcui;

    @SerializedName("name")
    public String name;
}
