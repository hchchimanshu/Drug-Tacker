package com.himanshuhc.drugtracker.domain.model;

public class Drug {
    private final String rxcui;
    private final String name;
    private final boolean isFromApi;

    public Drug(String rxcui, String name, boolean isFromApi) {
        this.rxcui = rxcui;
        this.name = name;
        this.isFromApi = isFromApi;
    }

    public String getRxcui() {
        return rxcui;
    }

    public String getName() {
        return name;
    }

    public boolean isFromApi() {
        return isFromApi;
    }
}
