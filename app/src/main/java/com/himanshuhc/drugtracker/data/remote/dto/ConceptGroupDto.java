package com.himanshuhc.drugtracker.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConceptGroupDto {

    @SerializedName("tty")
    public String tty;

    @SerializedName("conceptProperties")
    public List<ConceptPropertyDto> conceptProperties;
}
