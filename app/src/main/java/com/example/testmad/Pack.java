package com.example.testmad;

public class Pack {

    private String tName;
    private String tDate;
    private String tPrice;
    private String tPackage;
    private String imageUrl;
    private String key;

    //private String mEditTextFileName;

    public Pack() {

    }

    public Pack(String key) {
        this.key = key;
    }

    public Pack(String tName, String tDate, String tPrice, String tPackage, String imageUrl) {
        this.tName = tName;
        this.tDate = tDate;
        this.tPrice = tPrice;
        this.tPackage = tPackage;
        this.imageUrl = imageUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }

    public String gettPackage() {
        return tPackage;
    }

    public void settPackage(String tPackage) {
        this.tPackage = tPackage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
