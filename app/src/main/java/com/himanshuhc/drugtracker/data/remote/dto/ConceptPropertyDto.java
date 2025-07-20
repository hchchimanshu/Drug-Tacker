package com.himanshuhc.drugtracker.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ConceptPropertyDto {

    @SerializedName("rxcui")
    public String rxcui;

    @SerializedName("name")
    public String name;

    @SerializedName("synonym")
    public String synonym;

    @SerializedName("psn")
    public String psn;
    @SerializedName("tty")
    public String tty;
    @SerializedName("language")
    public String language;
    @SerializedName("suppress")
    public String suppress;
}
