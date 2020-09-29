package com.example.testmad;

public class Pack {
    String imageUrl, tDate, tName, tPackage, tPrice;

    public Pack() {
    }

    public Pack(String imageUrl, String tDate, String tName, String tPackage, String tPrice) {
        this.imageUrl = imageUrl;
        this.tDate = tDate;
        this.tName = tName;
        this.tPackage = tPackage;
        this.tPrice = tPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettPackage() {
        return tPackage;
    }

    public void settPackage(String tPackage) {
        this.tPackage = tPackage;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }
}
