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
    private PhoneBrand phoneBrand;
    private int length;
    private int width;
    private String owner;
    private double price;

    //endregion

    public Place(int id,PhoneBrand phoneBrand, int length, int width, String owner, double price) {
        this.id = id;
        this.phoneBrand = phoneBrand;
        this.length = length;
        this.width = width;
        this.owner = owner;
        this.price = price;

    }

    //region Getters

    public int getId() {
        return id;
    }

    public PhoneBrand getPhoneBrand() {
        return phoneBrand;
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

    //endregion

    //region Setters

    public void setPrice(double price) {
        this.price = price;
    }

    //endregion
}
