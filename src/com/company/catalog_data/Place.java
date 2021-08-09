package com.company.catalog_data;

import java.io.Serializable;

public class Place implements Serializable {
    //region enum Phone

    public enum PhoneBrand {
        samsung("Самсунг"),
        honor("Хонор"),
        apple("Яблоко");

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
    private int length;
    private int width;
    private String owner;
    private double price;
    private PhoneBrand phoneBrand;

    //endregion

    //region Constructor

    public Place(int id, int length, int width, String owner, double price, PhoneBrand phoneBrand) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.owner = owner;
        this.price = price;
        this.phoneBrand = phoneBrand;
    }

    //endregion

    //region Getters

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getOwner() {
        return owner;
    }

    public double getPrice() {
        return price;
    }

    public PhoneBrand getPlanet() {
        return phoneBrand;
    }

    //endregion

    //region Setters

    public void setPrice(double price) {
        this.price = price;
    }

    //endregion
}