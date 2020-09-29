package com.example.testmad;

public class hotelsModel {
    String name, rate, details, imageUrl;

    public hotelsModel() {
    }

    public hotelsModel(String name, String rate, String details, String imageUrl) {
        this.name = name;
        this.rate = rate;
        this.details = details;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

