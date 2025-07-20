package com.himanshuhc.drugtracker.domain.model;

public class Drug {
    private final String rxcui;
    private final String psn;
    private final boolean isFromApi;

    public Drug(String rxcui, String psn, boolean isFromApi) {
        this.rxcui = rxcui;
        this.psn = psn;
        this.isFromApi = isFromApi;
    }

    public String getRxcui() {
        return rxcui;
    }

    public String getPsn() {
        return psn;
    }

    public boolean isFromApi() {
        return isFromApi;
    }
}
