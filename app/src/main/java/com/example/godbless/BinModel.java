package com.example.godbless;

public class BinModel {

    String bin_number, fill_level, location, surl;
    double lati, longi;


    BinModel()
    {

    }
    public BinModel(String bin_number, String fill_level, String location, String surl, double lati, double longi) {
        this.bin_number = bin_number;
        this.fill_level = fill_level;
        this.location = location;
        this.surl = surl;
        this.lati = lati;
        this.longi = longi;
    }

    public String getBin_number() {
        return bin_number;
    }

    public void setBin_number(String bin_number) {
        this.bin_number = bin_number;
    }

    public String getFill_level() {
        return fill_level;
    }

    public void setFill_level(String fill_level) {
        this.fill_level = fill_level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public double getlati() {
        return lati;
    }

    public void setLati(double lati) { this.lati = lati; }

    public double getlongi() { return longi; }

    public void setLongi(double longi) { this.longi = longi; }
}
