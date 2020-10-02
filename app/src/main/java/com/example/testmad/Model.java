package com.example.testmad;

public class Model {

    private String IDtravel;
    private String datefrom;
    private String dateto;
    private String destinationfrom;
    private String destinatioto;
    private String Ttravel;
    private String Htravel;
    private int nopeople;
    private double budget;

    public Model() {
    }

    public Model(String IDtravel, String datefrom, String dateto, String destinationfrom, String destinatioto, String ttravel, String htravel, int nopeople, double budget) {
        this.IDtravel = IDtravel;
        this.datefrom = datefrom;
        this.dateto = dateto;
        this.destinationfrom = destinationfrom;
        this.destinatioto = destinatioto;
        Ttravel = ttravel;
        Htravel = htravel;
        this.nopeople = nopeople;
        this.budget = budget;
    }

    public String getIDtravel() {
        return IDtravel;
    }

    public void setIDtravel(String IDtravel) {
        this.IDtravel = IDtravel;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public String getDestinationfrom() {
        return destinationfrom;
    }

    public void setDestinationfrom(String destinationfrom) {
        this.destinationfrom = destinationfrom;
    }

    public String getDestinatioto() {
        return destinatioto;
    }

    public void setDestinatioto(String destinatioto) {
        this.destinatioto = destinatioto;
    }

    public String getTtravel() {
        return Ttravel;
    }

    public void setTtravel(String ttravel) {
        Ttravel = ttravel;
    }

    public String getHtravel() {
        return Htravel;
    }

    public void setHtravel(String htravel) {
        Htravel = htravel;
    }

    public int getNopeople() {
        return nopeople;
    }

    public void setNopeople(int nopeople) {
        this.nopeople = nopeople;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
