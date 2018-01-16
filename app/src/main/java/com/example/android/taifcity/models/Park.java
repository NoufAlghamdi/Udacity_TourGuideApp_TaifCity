package com.example.android.taifcity.models;

public class Park {
    private int name;

    private int photogragh;

    private int OpenHours;

    private int location;

    public Park(int name, int photogragh, int openHours, int location) {
        this.name = name;
        this.photogragh = photogragh;
        this.OpenHours = openHours;
        this.location = location;
    }

    public int getName() {
        return name;
    }

    public int getPhotograph() {
        return photogragh;
    }

    public int getOpenHours() {
        return OpenHours;
    }

    public int getLocation() {
        return location;
    }
}
