package com.example.android.taifcity.models;

public class Mall {
    private int name;
    private int photograph;
    private int website;
    private int location;

    public Mall(int name, int photograph, int website, int location) {
        this.name = name;
        this.photograph = photograph;
        this.website = website;
        this.location = location;
    }

    public int getName() {
        return name;
    }

    public int getPhotograph() {
        return photograph;
    }

    public int getWebsite() {
        return website;
    }

    public int getLocation() {
        return location;
    }
}
