package com.company.catalog_data;

import java.io.Serializable;

public class Place implements Serializable {
    //region enum Phone

    public enum PhoneBrand {
        samsung("samsung"),
        honor("honor"),
        nokia("nokia"),
        huawei("huawei"),
        apple("apple");

        private final String value;

        PhoneBrand(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //endregion

    //region Fields in the directory

    private int id;
    private PhoneBrand phoneBrand;
    private String model;
    private int weight;
    private String owner;
    private double price;
    private int battery;
    private double releaseDate;

    //endregion

    public Place(int id, PhoneBrand phoneBrand, String model, int weight, String owner, double price,int battery,double releaseDate) {
        this.id = id;
        this.phoneBrand = phoneBrand;
        this.model = model;
        this.weight = weight;
        this.owner = owner;
        this.price = price;
        this.battery = battery;
        this.releaseDate = releaseDate;

    }

    //region Getters

    public int getId() {
        return id;
    }

    public PhoneBrand getPhoneBrand() {
        return phoneBrand;
    }

    public String getModel() {
        return model;
    }

    public int getWeight() {
        return weight;
    }

    public String getOwner() {
        return owner;
    }

    public double getPrice() {
        return price;
    }

    public double getBattery() {
        return battery;
    }

    public double getReleaseDate() {
        return releaseDate;
    }

    //endregion

    //region Setters

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void setReleaseDate(double releaseDate) {
        this.releaseDate = releaseDate;
    }

    //endregion
}
