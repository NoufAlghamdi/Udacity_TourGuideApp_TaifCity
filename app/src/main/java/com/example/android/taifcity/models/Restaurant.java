package com.example.android.taifcity.models;

public class Restaurant {
    private int name;
    private int photograph;
    private int category;
    private int website;
    private int phoneNumber;
    private int location;

    public Restaurant(int name, int photograph, int category, int website,
                      int phoneNumber, int location) {
        this.name = name;
        this.photograph = photograph;
        this.category = category;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public int getName() {
        return name;
    }

    public int getPhotograph() {
        return photograph;
    }

    public int getCategory() {
        return category;
    }

    public int getWebsite() {
        return website;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getLocation() {
        return location;
    }
}
