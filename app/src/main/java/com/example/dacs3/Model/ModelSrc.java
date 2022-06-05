package com.example.dacs3.Model;

public class ModelSrc {

    String epNumber;
    String src;

    public ModelSrc(String epNumber, String src) {
        this.epNumber = epNumber;
        this.src = src;
    }

    public String getEpNumber() {
        return epNumber;
    }

    public void setEpNumber(String epNumber) {
        this.epNumber = epNumber;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
