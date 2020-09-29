package com.example.testmad;

public class hotelsModel {
    String name, address, purl;

    public hotelsModel() {
    }

    public hotelsModel(String name, String address, String purl) {
        this.name = name;
        this.address = address;
        this.purl = purl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
