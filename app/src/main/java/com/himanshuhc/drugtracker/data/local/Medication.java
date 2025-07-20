package com.himanshuhc.drugtracker.data.local;

public class Medication {

    private int id;
    private String name;
    private String rxcui;
    private boolean isCustom;
    private String psn;
    private String synonym;
    private String tty;
    private String language;
    private String suppress;

    public Medication(int id, String name, String rxcui, boolean isCustom, String psn, String synonym, String tty, String language, String suppress) {
        this.id = id;
        this.name = name;
        this.rxcui = rxcui;
        this.isCustom = isCustom;
        this.psn = psn;
        this.synonym = synonym;
        this.tty = tty;
        this.language = language;
        this.suppress = suppress;
    }

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
