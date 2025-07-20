package com.himanshuhc.drugtracker.domain.model;

import java.io.Serializable;

public class Drug implements Serializable {
    private final String rxcui;
    private final String psn;
    private final String name;
    private final boolean isFromApi;
    private final String synonym;
    private final String tty;
    private final String language;
    private final String suppress;

    public Drug(String rxcui, String psn, String name, boolean isFromApi, String synonym, String tty, String language, String suppress) {
        this.rxcui = rxcui;
        this.psn = psn;
        this.name = name;
        this.isFromApi = isFromApi;
        this.synonym = synonym;
        this.tty = tty;
        this.language = language;
        this.suppress = suppress;
    }

//    public Drug(String rxcui, String psn, boolean isFromApi) {
//        this.rxcui = rxcui;
//        this.psn = psn;
//        this.isFromApi = isFromApi;
//    }

    public String getRxcui() {
        return rxcui;
    }

    public String getPsn() {
        return psn;
    }

    public boolean isFromApi() {
        return isFromApi;
    }

    public String getName() {
        return name;
    }

    public String getSynonym() {
        return synonym;
    }

    public String getTty() {
        return tty;
    }

    public String getLanguage() {
        return language;
    }

    public String getSuppress() {
        return suppress;
    }
}
