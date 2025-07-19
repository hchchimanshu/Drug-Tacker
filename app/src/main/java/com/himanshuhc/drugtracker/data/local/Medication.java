package com.himanshuhc.drugtracker.data.local;

public class Medication {

    private int id;
    private String name;
    private String rxcui;
    private boolean isCustom;

    public Medication(int id, String name, String rxcui, boolean isCustom) {
        this.id = id;
        this.name = name;
        this.rxcui = rxcui;
        this.isCustom = isCustom;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRxcui() {
        return rxcui;
    }

    public boolean isCustom() {
        return isCustom;
    }
}
