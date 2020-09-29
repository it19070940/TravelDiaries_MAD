package com.example.testmad;

public class ProductsModel {
    private String Name;
    private String Email;
    private String mobile;
    private String purl;

    public ProductsModel() {
    }

    public ProductsModel(String name, String email, String mobile, String purl) {
        this.Name = name;
        this.Email = email;
        this.mobile = mobile;
        this.purl = purl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
