package com.example.elenavlasceanu.kitesurfingapplication;

public class FilterCriteria {
    private int windProbability;
    private String coutry;

    public FilterCriteria(int windProbability, String coutry) {
        this.windProbability = windProbability;
        this.coutry = coutry;
    }

    public int getWindProbability() {
        return windProbability;
    }

    public void setWindProbability(int windProbability) {
        this.windProbability = windProbability;
    }

    public String getCoutry() {
        return coutry;
    }

    public void setCoutry(String coutry) {
        this.coutry = coutry;
    }
}
