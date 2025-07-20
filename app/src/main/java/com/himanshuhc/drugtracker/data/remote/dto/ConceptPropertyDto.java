package com.himanshuhc.drugtracker.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ConceptPropertyDto {

    @SerializedName("rxcui")
    public String rxcui;

    @SerializedName("name")
    public String name;

    @SerializedName("synonym")
    private String synonym;

    @SerializedName("psn")
    public String psn;
}
