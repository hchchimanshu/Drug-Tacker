package com.himanshuhc.drugtracker.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrugGroupDto {

    @SerializedName("conceptGroup")
    public List<ConceptGroupDto> conceptGroup;
}
